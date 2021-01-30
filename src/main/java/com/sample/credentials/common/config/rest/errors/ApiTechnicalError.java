package com.sample.credentials.common.config.rest.errors;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiTechnicalError implements ApiSubError
{

    /** le code de l'erreur. */
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String erreurTechnique;


    /** le message de l'erreur. */
    private String message;



    /**
     * Instanciation de api business error.
     * @param erreurTechnique
     * @param message
     */
    public ApiTechnicalError(String erreurTechnique, String message)
    {
        super();
        this.erreurTechnique = erreurTechnique;
        this.message = message;
    }


    /**
     * Accesseur de l attribut erreur fonctionnelle.
     *
     * @return erreur fonctionnelle
     */
    public String geterreurTechnique()
    {
        return erreurTechnique;
    }

    /**
     * Modificateur de l attribut erreur fonctionnelle.
     *
     * @param erreurTechnique le nouveau erreur fonctionnelle
     */
    public void seterreurTechnique(String erreurTechnique)
    {
        this.erreurTechnique = erreurTechnique;
    }

    /**
     * Accesseur de l attribut message.
     *
     * @return message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Modificateur de l attribut message.
     *
     * @param message le nouveau message
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

}

