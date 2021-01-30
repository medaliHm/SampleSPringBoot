
package com.sample.credentials.common.config.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

/**
 * Class ControleUnitaireException exception métier aggrégeant tous les contrôles de format non passants
 */
public class ControleUnitaireException extends Exception {

    /**
     * Constant : serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * violations.
     */
    private final Set<ConstraintViolation<Object>> violations;

    /**
     * Instanciation de controle unitaire exception.
     *
     * @param violations liste des controles non passants
     */
    public ControleUnitaireException(final Set<ConstraintViolation<Object>> violations) {
        super();
        this.violations = violations;
    }

    /**
     * Accesseur de l attribut violations.
     *
     * @return violations
     */
    public Set<ConstraintViolation<Object>> getViolations() {
        return violations;
    }

}
