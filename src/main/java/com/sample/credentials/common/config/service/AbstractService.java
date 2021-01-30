package com.sample.credentials.common.config.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.sample.credentials.common.config.annotation.ControleMetier;
import com.sample.credentials.common.config.annotation.IControleMetier;
import com.sample.credentials.common.config.exception.ControleMetierException;
import com.sample.credentials.common.config.exception.ControleUnitaireException;
import com.sample.credentials.common.config.exception.ControlesException;
import com.sample.credentials.common.config.exception.ExceptionMetier;



/**
 * Class AbstractService Classe abstraite dont hérite tous les services métier unitaires, implémentant les mécanismes
 * d'une méthode d'exécution unique précédée des contrôles de toutes les classes de règles de gestion (ajoutées par
 * annotation), de validation JSR303 et la gestion des exceptions métiers associées
 *
 * @param <E> Classe du domaine métier en entrée du service
 * @param <S> Classe du domaine métier en sortie du service
 */
public abstract class AbstractService<E, S> implements IService<E, S>, InitializingBean {

    /**
     * Constant : LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);


    /**
     * rules.
     */
    List<IControleMetier> rules;

    /**
     * application context.
     */
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public S execute(E object) throws ExceptionMetier {

        ControlesException genericBusinessException = null;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        // lancer la validation des entités métiers annotés (JSR303)
        Set<ConstraintViolation<Object>> violations = validator.validate(object);

        // si problèmes de controle, alors on throw une exception métier
        if (!violations.isEmpty()) {
            genericBusinessException = new ControlesException("Validation Exception");
            genericBusinessException.setValidatioExceptions(new ControleUnitaireException(violations));
            throw genericBusinessException;
        }

        // validation successive de toutes les règles métier associés au service
        for (IControleMetier rule : rules) {
            try {
                rule.validate(object);
            }
            // si règle non passante :
            catch (ControleMetierException exceptionMetier) {
                if (genericBusinessException == null) {
                    genericBusinessException = new ControlesException("Business Exceptions");
                }

                // si l'exception contient une liste d'exception alors on ajoute toutes les exceptions de la liste, sinon on ajoute
                // l'exception à l'exception globale
                if (CollectionUtils.isNotEmpty(exceptionMetier.getListMetierException())) {
                    genericBusinessException.getExceptionList().addAll(exceptionMetier.getListMetierException());
                } else {
                    genericBusinessException.getExceptionList().add(exceptionMetier);
                }

                // si la règle en cours est bloquante, on throw et on arrête
                if (rule.isBloquant()) {
                    throw genericBusinessException;
                }
            }
        }

        if (genericBusinessException != null) {
            throw genericBusinessException;
        }

        // après les contrôles, on exécute le code business du service
        return doExecute(object);

    }

    /**
     * methode Do execute.
     *
     * @param object
     * @return t
     */
    public abstract S doExecute(E object);

    /**
     * (methode de remplacement) {@inheritDoc}
     *
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet() this is another solution for rules list
     * population because the BeanPostProcessor not getting called by spring
     */
    @Override
    public void afterPropertiesSet() {
        Class<? extends Object> clazz = getClass();
        ControleMetier[] array = clazz.getAnnotationsByType(ControleMetier.class);

        List<IControleMetier> rulesList = new ArrayList<>();

        if (array.length > 0) {
            Class<? extends IControleMetier>[] rulesClasses = array[0].value();
            try {
                for (Class<? extends IControleMetier> ruleClass : rulesClasses) {

                    rulesList.add(ruleClass.newInstance());

                }

            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error("Error creating rule instance : {} ", e.getMessage());
            }
        }

        this.setRules(rulesList);
    }

    /**
     * Accesseur de l attribut rules.
     *
     * @return rules
     */
    public List<IControleMetier> getRules() {
        return rules;
    }

    /**
     * Modificateur de l attribut rules.
     *
     * @param rules le nouveau rules
     */
    public void setRules(List<IControleMetier> rules) {
        this.rules = rules;
    }
}