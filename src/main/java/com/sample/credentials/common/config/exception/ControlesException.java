
package com.sample.credentials.common.config.exception;

import java.util.ArrayList;
import java.util.List;


/**
 * Class ControlesException
 */
public class ControlesException extends ExceptionMetier {

    /**
     * Constant : serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * exception list.
     */
    private final List<ControleMetierException> exceptionList = new ArrayList<>();

    /**
     * validatio exceptions.
     */
    private ControleUnitaireException validatioExceptions;

    /**
     * Instanciation de controles exception.
     *
     * @param msg DOCUMENTEZ_MOI
     */
    public ControlesException(String msg) {
        super(msg);
    }

    /**
     * Accesseur de l attribut exception list.
     *
     * @return exception list
     */
    public List<ControleMetierException> getExceptionList() {
        return exceptionList;
    }

    /**
     * Accesseur de l attribut validatio exceptions.
     *
     * @return validatio exceptions
     */
    public ControleUnitaireException getValidatioExceptions() {
        return validatioExceptions;
    }

    /**
     * Modificateur de l attribut validatio exceptions.
     *
     * @param validatioExceptions le nouveau validatio exceptions
     */
    public void setValidatioExceptions(ControleUnitaireException validatioExceptions) {
        this.validatioExceptions = validatioExceptions;
    }

}
