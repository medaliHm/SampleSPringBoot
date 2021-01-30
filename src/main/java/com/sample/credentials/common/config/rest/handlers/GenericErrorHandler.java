package com.sample.credentials.common.config.rest.handlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sample.credentials.common.config.logger.NormalLogger;
import com.sample.credentials.common.config.rest.errors.ApiError;

/**
 * Class GenericHandlerController - Handler exÃ©cuter aprÃ¨s ErrorsHandlerController afin de catcher les erreurs non
 * prises en compte.
 */
@ControllerAdvice
@Order(2)
public class GenericErrorHandler
{

    /** afficher stack. */
    @Value("${erreur.stack}")
    private boolean afficherStack;

    /** Constant : Message d'erreur Générique. */
    private static final String ERREUR_GENERIQUE_MESSAGE = "Une erreur technique est survenue";

    /**
     * Constructeur de la classe GenericHandlerController.java
     */
    public GenericErrorHandler()
    {
        super();

    }

    /**
     * methode handleGenericException : Méthode permettant de catcher les erreurs de type Exception et de formatter
     * l'erreur.
     *
     * @param handlerEx DOCUMENTEZ_MOI
     * @return response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception handlerEx)
    {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage(ERREUR_GENERIQUE_MESSAGE);
        if (afficherStack)
        {
            apiError.setDebugMessage(handlerEx.getMessage());
            StringBuilder stack = new StringBuilder();
            for (StackTraceElement element : handlerEx.getStackTrace())
            {
                stack.append(element.toString() + "|");
            }

            apiError.setDebugStack(stack.toString());
        }
        NormalLogger.EXCEPTIONS_LOG.log(handlerEx.getMessage());
        NormalLogger.EXCEPTIONS_LOG.log(handlerEx);
        return buildResponseEntity(apiError);
    }

    /**
     * methode buildResponseEntity : Contruit le responseEntity pour le renvoie des erreurs.
     *
     * @param apiError DOCUMENTEZ_MOI
     * @return response entity
     */
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError)
    {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
