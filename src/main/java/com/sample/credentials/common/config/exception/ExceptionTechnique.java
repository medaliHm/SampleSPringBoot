package com.sample.credentials.common.config.exception;

/**
 * Class ExceptionTechnique.
 */
public class ExceptionTechnique extends Exception {

    /**
     * Constant : serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * code technique.
     */
    private final String codeTechnique;

    /**
     * Instanciation de exception technique.
     *
     * @param msg           message de l'exception
     * @param codeTechnique code technique de l'exception
     */
    public ExceptionTechnique(String msg, String codeTechnique) {
        super(msg);
        this.codeTechnique = codeTechnique;
    }

    /**
     * Accesseur de l attribut code technique.
     *
     * @return code technique
     */
    public String getCodeTechnique() {
        return codeTechnique;
    }

}