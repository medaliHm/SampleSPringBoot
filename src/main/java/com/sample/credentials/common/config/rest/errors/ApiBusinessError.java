
package com.sample.credentials.common.config.rest.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Class ApiBusinessError - Classe gérant les erreurs de type Fonctionnelle.
 */
public class ApiBusinessError implements ApiSubError
{

    /** le code de l'erreur. */
    @JsonInclude(value = Include.NON_NULL)
    private String erreurFonctionnelle;


    /** le message de l'erreur. */
    private String message;



    /**
     * Instanciation de api business error.
     * @param erreurFonctionnelle
     * @param message
     */
    public ApiBusinessError(String erreurFonctionnelle, String message)
    {
        super();
        this.erreurFonctionnelle = erreurFonctionnelle;
        this.message = message;
    }


    /**
     * Accesseur de l attribut erreur fonctionnelle.
     *
     * @return erreur fonctionnelle
     */
    public String getErreurFonctionnelle()
    {
        return erreurFonctionnelle;
    }

    /**
     * Modificateur de l attribut erreur fonctionnelle.
     *
     * @param erreurFonctionnelle le nouveau erreur fonctionnelle
     */
    public void setErreurFonctionnelle(String erreurFonctionnelle)
    {
        this.erreurFonctionnelle = erreurFonctionnelle;
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
