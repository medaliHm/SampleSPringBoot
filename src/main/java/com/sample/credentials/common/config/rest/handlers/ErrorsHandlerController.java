
package com.sample.credentials.common.config.rest.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.sample.credentials.common.config.exception.ControleMetierException;
import com.sample.credentials.common.config.exception.ControlesException;
import com.sample.credentials.common.config.exception.ExceptionMetier;
import com.sample.credentials.common.config.logger.NormalLogger;
import com.sample.credentials.common.config.rest.errors.ApiBusinessError;
import com.sample.credentials.common.config.rest.errors.ApiError;
import com.sample.credentials.common.config.rest.errors.ApiSubError;
import com.sample.credentials.common.config.rest.errors.ApiTechnicalError;
import com.sample.credentials.common.config.rest.errors.ApiValidationError;


/**
 * Class ErrorsHandlerController - Handler permettant de catcher les erreurs - Premier Handler executé.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorsHandlerController
{

    /** Constant : ERREUR_TYPAGE. */
    private static final String ERREUR_TYPAGE = "Erreurs de typage";

    /** Constant : ERREUR_VALIDATION. */
    private static final String ERREUR_VALIDATION = "Erreurs de validation";

    /** Constant : ERREUR_APPEL. */
    private static final String ERREUR_APPEL = "Erreurs d'appel";

    /**
     * methode handleGenericException : Méthode permettant de catcher les erreurs de type ExceptionMetier (erreur lors de
     * l'execution ).
     *
     * @param ctrlEx ExceptionMetier
     * @return response entity
     */
    @ExceptionHandler(ExceptionMetier.class)
    public ResponseEntity<Object> handleGenericException(ControlesException ctrlEx)
    {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ctrlEx.getMessage());
        addSubErrors(apiError, ctrlEx);
        return buildResponseEntity(apiError);
    }

    /**
     * methode handleTypeException : Méthode permettant de catcher les erreurs de type MethodArgumentTypeMismatchException
     * (erreur de typage dans la requête)
     *
     * @param mismatchEx MethodArgumentTypeMismatchException
     * @return response entity
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeException(MethodArgumentTypeMismatchException mismatchEx)
    {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ERREUR_TYPAGE);
        addSubErrors(apiError, mismatchEx);
        return buildResponseEntity(apiError);
    }

    /**
     * methode handleTypeException : Méthode permettant de catcher les erreurs de type ConstraintViolationException
     * (validation de champ dans la requete)
     *
     * @param constraintEx MethodArgumentTypeMismatchException
     * @return response entity
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleTypeException(ConstraintViolationException constraintEx)
    {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ERREUR_VALIDATION);
        addSubErrors(apiError, constraintEx);
        return buildResponseEntity(apiError);
    }

    /**
     * methode handleTypeException : Méthode permettant de catcher les erreurs de type HttpMessageNotReadableException
     * (erreur de typage dans le JSON).
     *
     * @param httpEx HttpMessageNotReadableException
     * @return response entity
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleTypeException(HttpMessageNotReadableException httpEx)
    {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ERREUR_TYPAGE);
        addSubErrors(apiError, httpEx);
        return buildResponseEntity(apiError);
    }

    /**
     * methode handleValidationException : Méthode permettant de catcher les erreurs de type MethodArgumentNotValidException
     * (validation sur le DTO d'entrée)
     *
     * @param argumentEx MethodArgumentNotValidException
     * @return response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException argumentEx)
    {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ERREUR_VALIDATION);
        addSubErrors(apiError, argumentEx);
        return buildResponseEntity(apiError);
    }

    /**
     * methode handleValidationException : Méthode permettant de catcher les erreurs de type
     * HttpRequestMethodNotSupportedException (mauvais verbe HTTP lors de l'appel).
     *
     * @param httpEx HttpRequestMethodNotSupportedException
     * @return response entity
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleValidationException(HttpRequestMethodNotSupportedException httpEx)
    {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ERREUR_APPEL);
        addSubErrors(apiError, httpEx);
        return buildResponseEntity(apiError);
    }

    /**
     * methode buildApiBusinessError : Méthode permettant de construire la liste de ApiSubError pour les erreurs de type
     * ControleMetierException et de convertir les erreurs en ApiValidationError
     *
     * @param exceptionList List<ControleMetierException>
     * @return list
     */
    private List<ApiSubError> buildApiBusinessError(List<ControleMetierException> exceptionList)
    {
        return exceptionList.stream().map(ex -> {
            NormalLogger.BUSINESS_LOG.log(ex.getBusinessCode() + "|" + ex.getMessage());
            return new ApiBusinessError(ex.getBusinessCode(), ex.getMessage());
        }).collect(Collectors.toList());
    }

    /**
     * methode buildApiValidationRules : Méthode permettant de construire la liste de ApiSubError pour les erreurs de type
     * ConstraintViolation et de convertir les erreurs en ApiValidationError.
     *
     * @param validationViolations Set<ConstraintViolation<Object>>
     * @return list
     */
    private List<ApiSubError> buildApiValidationRules(Set<ConstraintViolation<Object>> validationViolations)
    {
        return validationViolations.stream().map(violation -> {
            String value = String.valueOf(violation.getInvalidValue());
            NormalLogger.BUSINESS_LOG.log(ERREUR_VALIDATION + "|" + violation.getMessage());
            return new ApiValidationError(violation.getPropertyPath().toString(), value, violation.getMessage());
        }).collect(Collectors.toList());
    }

    /**
     * methode addSubErrors : Ajoute les erreurs à l'ApiError qui sera retournée dans le cas de ControlesException.
     *
     * @param apiError ApiError
     * @param ctrlEx   ControlesException
     */
    private void addSubErrors(ApiError apiError, ControlesException ctrlEx)
    {
        if (ctrlEx.getValidatioExceptions() != null)
        {
            apiError.setErreurs(buildApiValidationRules(ctrlEx.getValidatioExceptions().getViolations()));
        }

        if (apiError.getErreurs() == null)
        {
            apiError.setErreurs(buildApiBusinessError(ctrlEx.getExceptionList()));
        }
    }

    /**
     * methode addSubErrors : Ajoute les erreurs à l'ApiError qui sera retournée dans le cas de ControlesException.
     *
     * @param apiError     ApiError
     * @param constraintEx ControlesException
     */
    private void addSubErrors(ApiError apiError, ConstraintViolationException constraintEx)
    {
        apiError.setErreurs(constraintEx.getConstraintViolations().stream().map(violation -> {
            NormalLogger.BUSINESS_LOG.log(ERREUR_VALIDATION + "|" + violation.getMessage());

            return new ApiBusinessError(EnumError.E001.getCode(), violation.getMessage());
        }).collect(Collectors.toList()));
    }

    /**
     * methode addSubErrors : Ajoute les erreurs à l'ApiError qui sera retournée dans le cas de
     * MethodArgumentNotValidException et convertie l'erreur en ApiValidationError.
     *
     * @param apiError   ApiError
     * @param argumentEx MethodArgumentNotValidException
     */
    private void addSubErrors(ApiError apiError, MethodArgumentNotValidException argumentEx)
    {
        apiError.setErreurs(argumentEx.getBindingResult().getFieldErrors().stream().map(violation -> {
            NormalLogger.BUSINESS_LOG.log(ERREUR_VALIDATION + "|" + violation.getDefaultMessage());
            String code = EnumError.E002.getCode();

            return new ApiBusinessError(code, violation.getDefaultMessage());
        }).collect(Collectors.toList()));
    }

    /**
     * methode addSubErrors : Ajoute les erreurs à l'ApiError qui sera retournée dans le cas de
     * HttpMessageNotReadableException et convertie l'erreur en ApiValidationError.
     *
     * @param apiError ApiError
     * @param httpEx   HttpMessageNotReadableException
     */
    private void addSubErrors(ApiError apiError, HttpMessageNotReadableException httpEx)
    {
        if (httpEx.getCause() instanceof InvalidFormatException)
        {
            InvalidFormatException exception = (InvalidFormatException) httpEx.getCause();
            String messageErreur = genererMessage(exception);
            NormalLogger.BUSINESS_LOG.log(ERREUR_VALIDATION + "|" + messageErreur);

            apiError.setErreurs(new ArrayList<>());
            apiError.getErreurs()
                .add(new ApiBusinessError(EnumError.E002.getCode(), messageErreur));
        }
        if (httpEx.getCause() instanceof UnrecognizedPropertyException)
        {
            UnrecognizedPropertyException exception = (UnrecognizedPropertyException) httpEx.getCause();
            String messageErreur = genererMessageAttributInconnu(exception);
            NormalLogger.BUSINESS_LOG.log(ERREUR_VALIDATION + "|" + messageErreur);
            apiError.setErreurs(new ArrayList<>());
            apiError.getErreurs()
                .add(new ApiBusinessError(EnumError.E002.getCode(), messageErreur));
        }
        if (httpEx.getCause() instanceof JsonMappingException)
        {
            JsonMappingException exception = (JsonMappingException) httpEx.getCause();
            String messageErreur = genererMessageTypage(exception);
            NormalLogger.BUSINESS_LOG.log(ERREUR_VALIDATION + "|" + messageErreur);
            apiError.setErreurs(new ArrayList<>());
            apiError.getErreurs()
                .add(new ApiBusinessError(EnumError.E002.getCode(), messageErreur));
        }

    }

    /**
     * methode addSubErrors : Ajoute les erreurs à l'ApiError qui sera retournée dans le cas de
     * HttpRequestMethodNotSupportedException et convertie l'erreur en ApiValidationError.
     *
     * @param apiError ApiError
     * @param httpEx   HttpRequestMethodNotSupportedException
     */
    private void addSubErrors(ApiError apiError, HttpRequestMethodNotSupportedException httpEx)
    {
        String messageErreur = genererMessage(httpEx);
        NormalLogger.BUSINESS_LOG.log(ERREUR_APPEL + "|" + messageErreur);

        apiError.setErreurs(new ArrayList<>());
        apiError.getErreurs()
            .add(new ApiTechnicalError(EnumError.E000.getCode(), messageErreur));

    }

    /**
     * methode addSubErrors : Ajoute les erreurs à l'ApiError qui sera retournée dans le cas de
          * MethodArgumentTypeMismatchException et convertie l'erreur en ApiValidationError.
     *
     * @param apiError
     * @param mismatchEx
     */
    private void addSubErrors(ApiError apiError, MethodArgumentTypeMismatchException mismatchEx)
    {
        String messageErreur = genererMessage(mismatchEx);
        NormalLogger.BUSINESS_LOG.log(ERREUR_VALIDATION + "|" + messageErreur);

        apiError.setErreurs(new ArrayList<>());
        apiError.getErreurs()
            .add(new ApiBusinessError(EnumError.E002.getCode(), messageErreur));

    }

    /**
     * methode genererMessage : Génère le message d'erreur dans le cas d'une erreur de type InvalidFormatException (champ du
     * mauvais type dans le JSON).
     *
     * @param formatEx InvalidFormatExceptionInvalidFormatException
     * @return string
     */
    private String genererMessage(InvalidFormatException formatEx)
    {
        return String.format("Le champ %s (valeur : %s) n'est pas du bon type", formatEx.getPath().get(0).getFieldName(),
            formatEx.getValue());
    }

    /**
     * Return le message d'erreur dans le cas d'un attribut non reconnu.
     *
     * @param ex UnrecognizedPropertyException
     * @return
     */
    private String genererMessageTypage(JsonMappingException ex)
    {
        String value = null;
        String message = ex.getMessage();
        if (message.contains("ACCEPT_FLOAT_AS_INT"))
        {
            return String.format("Format de %s n'est pas valide", ex.getPath().get(0).getFieldName());
        }
        else if (message.contains("Unrecognized field"))
        {
            return String.format("Le champ %s n'est pas un attribut attendu", ex.getPath().get(0).getFieldName());
        }
        else if (message.contains("'"))
        {
            value = message.split("'")[1];
        }
        else if (message.contains("\""))
        {
            value = message.split("\"")[1];
        }
        return String.format("Format de %s (valeur : %s) n'est pas du bon type", ex.getPath().get(0).getFieldName(),
            value);

    }

    /**
     * Return le message d'erreur dans le cas d'un attribut non reconnu.
     * @param propertyEx
     * @return
     */
    private String genererMessageAttributInconnu(UnrecognizedPropertyException propertyEx)
    {
        return "Le champ " + propertyEx.getPropertyName() + " n'est pas un attribut attendu";
    }

    /**
       * methode genererMessage : Génère le message d'erreur dans le cas d'une erreur de type
          * HttpRequestMethodNotSupportedException (mauvais verbe HTTP lors de l'appel de la requête)
     * @param httpEx
     * @return
     */
    private String genererMessage(HttpRequestMethodNotSupportedException httpEx)
    {
        return String.format("Le verbe %s n'est pas supporté pour cette ressource", httpEx.getMethod());
    }

    /**
     * methode genererMessage : Génère le message d'erreur dans le cas d'une erreur de type
     * MethodArgumentTypeMismatchException (mauvais type dans paramètre d'une requête).
     *
     * @param mismatchEx MethodArgumentTypeMismatchException
     * @return string
     */
    private String genererMessage(MethodArgumentTypeMismatchException mismatchEx)
    {
        return String.format("Le champ %s (valeur : %s) de la requête n'est pas du bon type", mismatchEx.getName(), mismatchEx.getValue());
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
