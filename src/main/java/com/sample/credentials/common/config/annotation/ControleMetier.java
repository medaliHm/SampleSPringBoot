package com.sample.credentials.common.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Interface ControleMetier Annotation permettant de rattachée des contrôles de règles de gestion à des services métiers
 * unitaires.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ControleMetier
{

    /**
     * Value : accepte un tableau de classes de règles de gestion (étendant IControleMetier)
     *
     * @return the class<? extends I controle metier>[]
     */
    Class<? extends IControleMetier>[] value();
}
