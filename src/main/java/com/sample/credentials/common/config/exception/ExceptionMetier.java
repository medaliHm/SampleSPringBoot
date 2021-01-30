package com.sample.credentials.common.config.exception;

/**
 * Class ExceptionMetier.
 */
public class ExceptionMetier extends Exception {

    /**
     * Constant : serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instanciation de exception metier.
     *
     * @param msg message de l'exception
     */
    public ExceptionMetier(String msg) {
        super(msg);
    }

}