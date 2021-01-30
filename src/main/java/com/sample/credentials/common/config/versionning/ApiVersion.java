package com.sample.credentials.common.config.versionning;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interface ApiVersion annotation permettant de gérer le versioning de l'API au sein des URL
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion
{

    /**
     * methode Value.
     *
     * @return string
     */
    String value() default "";
}