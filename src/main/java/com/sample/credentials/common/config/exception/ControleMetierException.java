
package com.sample.credentials.common.config.exception;

import java.util.List;

/**
 * Class ControleMetierException exception métier générée par les contrôles de règle de gestion non passants.
 */
public class ControleMetierException extends Exception
{

    /**
     * serialVersionUID - long
     */
    private static final long serialVersionUID = -4615240058143438786L;

    /** business code. */
    private final String businessCode;

    /** list metier exception. */
    private final List<ControleMetierException> listMetierException;


    /**
     * Instanciation de controle metier exception.
     *
     * @param msg          message de l'erreur
     * @param businessCode code de l'erreur
     */
    public ControleMetierException(String msg, String businessCode)
    {
        super(msg);
        this.businessCode = businessCode;
        this.listMetierException = null;
    }



    /**
     * Instanciation de controle metier exception.
     *
     * @param listMetierException
     */
    public ControleMetierException(List<ControleMetierException> listMetierException)
    {
        super("");
        this.businessCode = null;
        this.listMetierException = listMetierException;

    }


    /**
     * Accesseur de l attribut business code.
     *
     * @return business code
     */
    public String getBusinessCode()
    {
        return businessCode;
    }

    /**
     * Accesseur de l attribut list metier exception.
     *
     * @return list metier exception
     */
    public List<ControleMetierException> getListMetierException()
    {
        return listMetierException;
    }



}
