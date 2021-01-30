
package com.sample.credentials.common.config.rest.errors;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

/**
 * Class ApiError - Classe permettant de renvoyer les erreurs.
 */
public class ApiError
{

    /** status - Le status HTTP accompagnant l'erreur */
    private HttpStatus status;

    /** message - Le message générale de l'erreur */
    private String message;

    /** permet d'afficher le messge de l'erreur en local et en qualif */
    @JsonInclude(Include.NON_NULL)
    @ApiModelProperty(hidden = true)
    private String debugMessage;

    /** permet d'afficher la stack en local et en qualif */
    @JsonInclude(Include.NON_NULL)
    @ApiModelProperty(hidden = true)
    private String debugStack;

    /** horodatage - La date de l'erreur */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime horodatage;

    /** erreurs - La liste des erreurs */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ApiSubError> erreurs;

    /**
     * Instanciation de api error.
     */
    private ApiError()
    {
        horodatage = LocalDateTime.now();
    }

    /**
     * Instanciation de api error.
     *
     * @param status HttpStatus
     */
    public ApiError(HttpStatus status)
    {
        this();
        this.status = status;
    }

    /**
     * Accesseur de l attribut status.
     *
     * @return status
     */
    public HttpStatus getStatus()
    {
        return status;
    }

    /**
     * Modificateur de l attribut status.
     *
     * @param status le nouveau status
     */
    public void setStatus(HttpStatus status)
    {
        this.status = status;
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

    /**
     * Accesseur de l attribut horodatage.
     *
     * @return horodatage
     */
    public LocalDateTime getHorodatage()
    {
        return horodatage;
    }

    /**
     * Modificateur de l attribut horodatage.
     *
     * @param horodatage le nouveau horodatage
     */
    public void setHorodatage(LocalDateTime horodatage)
    {
        this.horodatage = horodatage;
    }

    /**
     * Accesseur de l attribut erreurs.
     *
     * @return erreurs
     */
    public List<ApiSubError> getErreurs()
    {
        return erreurs;
    }

    /**
     * Modificateur de l attribut erreurs.
     *
     * @param erreurs le nouveau erreurs
     */
    public void setErreurs(List<ApiSubError> erreurs)
    {
        this.erreurs = erreurs;
    }

    /**
     * Accesseur de l attribut debug stack.
     *
     * @return debug stack
     */
    public String getDebugStack()
    {
        return debugStack;
    }

    /**
     * Modificateur de l attribut debug stack.
     *
     * @param debugStack le nouveau debug stack
     */
    public void setDebugStack(String debugStack)
    {
        this.debugStack = debugStack;
    }

    /**
     * Gets the permet d'afficher le messge de l'erreur en local et en qualif.
     *
     * @return the permet d'afficher le messge de l'erreur en local et en qualif
     */
    public String getDebugMessage()
    {
        return debugMessage;
    }

    /**
     * Sets the permet d'afficher le messge de l'erreur en local et en qualif.
     *
     * @param debugMessage the new permet d'afficher le messge de l'erreur en local et en qualif
     */
    public void setDebugMessage(String debugMessage)
    {
        this.debugMessage = debugMessage;
    }

}
