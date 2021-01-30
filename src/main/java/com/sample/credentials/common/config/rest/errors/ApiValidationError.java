
package com.sample.credentials.common.config.rest.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Class ApiValidationError - Classe gérant les erreurs de validation.
 */
public class ApiValidationError implements ApiSubError
{

    /** l'attribut faisant l'objet d'une erreur. */
    @JsonInclude(Include.NON_NULL)
    private String attribut;

    /** la valeur rejetée */
    @JsonInclude(Include.NON_NULL)
    private Object valeurRejetee;

    /** le message expliquant le rejet */
    @JsonInclude(Include.NON_NULL)
    private String message;

    /**
     * Instanciation de api validation error.
     *
     * @param attribut type String
     * @param valeurRejetee Object
     * @param message type String
     */
    public ApiValidationError(String attribut, Object valeurRejetee, String message)
    {
        super();
        this.attribut = attribut;
        this.valeurRejetee = valeurRejetee;
        this.message = message;
    }

    /**
     * Accesseur de l attribut attribut.
     *
     * @return attribut
     */
    public String getAttribut()
    {
        return attribut;
    }

    /**
     * Modificateur de l attribut attribut.
     *
     * @param attribut le nouveau attribut
     */
    public void setAttribut(String attribut)
    {
        this.attribut = attribut;
    }

    /**
     * Accesseur de l attribut valeur rejetee.
     *
     * @return valeur rejetee
     */
    public Object getValeurRejetee()
    {
        return valeurRejetee;
    }

    /**
     * Modificateur de l attribut valeur rejetee.
     *
     * @param valeurRejetee le nouveau valeur rejetee
     */
    public void setValeurRejetee(Object valeurRejetee)
    {
        this.valeurRejetee = valeurRejetee;
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
