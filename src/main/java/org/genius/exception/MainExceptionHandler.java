package org.genius.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@SuppressWarnings({"PMD.TooManyMethods"})
public class MainExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String MESSAGE_FIELD = "message";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String TYPE_FIELD = "type";
    private static final String PROVIDER_FIELD = "provider";
    private static final String PROVIDER_CODE_FIELD = "provider_code";

    /**
     * Handles other exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(
            Exception exception
    ) {
        AppErrorType appErrorType = AppErrorType.OTHER;
        Map<String, Object> response = new HashMap<>();
        response.put(TYPE_FIELD, appErrorType.getDescription());
        response.put(PROVIDER_FIELD, appErrorType.getProvider());
        response.put(MESSAGE_FIELD, exception.getMessage());
        response.put(DESCRIPTION_FIELD, ExceptionUtils.getRootCause(exception).getMessage());

        log.error(exception.getMessage(), exception);
        return new ResponseEntity<Map<String, Object>>(response, HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles CustomResponseStatusException exceptions.
     */
    @ExceptionHandler(CustomResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleCustomResponseStatusException(
            CustomResponseStatusException exception
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, exception.getReason());

        log.error(exception.getMessage(), exception);
        return new ResponseEntity<Map<String, Object>>(response, exception.getHeaders(), exception.getStatus());
    }

    /**
     * Handles AppErrorException exceptions.
     */
    @ExceptionHandler(AppErrorException.class)
    public ResponseEntity<Map<String, Object>> handleAppErrorException(
            AppErrorException exception
    ) {
        AppError appError = exception.getAppError();
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, appError.getMessage());
        response.put(DESCRIPTION_FIELD, appError.getDescription());
        response.put(TYPE_FIELD, appError.getType());
        response.put(PROVIDER_FIELD, appError.getType().getProvider());

        log.error(appError.getMessage(), exception);
        return new ResponseEntity<Map<String, Object>>(response, exception.getHeaders(), exception.getStatus());
    }

    /**
     * Handles IllegalArgumentException exceptions.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(
            IllegalArgumentException exception
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<Map<String, Object>>(response, HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles exceptions thrown by controllers when the request method for an url is not valid.
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, headers, status);
    }

    /**
     * Handles exceptions thrown by controllers when the request body is not in the correct format.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, headers, status);
    }

    /**
     * Handles exceptions thrown by controllers when the media type that the client requires is not accepted.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
            HttpMediaTypeNotAcceptableException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, headers, status);
    }

    /**
     * Handles exceptions thrown by controllers when there is a missing path variable.
     */
    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
            MissingPathVariableException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, headers, status);
    }

    /**
     * Handles exceptions thrown by controllers when a required request parameter is missing.
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException exception, HttpHeaders headers, HttpStatus status,
            WebRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, headers, status);
    }

    /**
     * Handles exceptions thrown by controllers when a required header is missing.
     */
    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
            ServletRequestBindingException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, headers, status);
    }

    /**
     * Handles exceptions thrown when no suitable editor or converter can be found for a bean property.
     */
    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(
            ConversionNotSupportedException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, headers, status);
    }

    /**
     * Handles exceptions thrown on a type mismatch when trying to set a bean property.
     */
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, headers, status);
    }

    /**
     * Handles exceptions thrown by controllers when the request body is missing.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, "Required request body is missing");
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, headers, status);
    }

    /**
     * Handles exceptions thrown by HttpMessageConverter implementations when the HttpMessageConverter.write method
     * fails.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            HttpMessageNotWritableException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, "Required request body is missing");
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, headers, status);
    }

    /**
     * Handles exceptions thrown when validation on an argument annotated with @Valid fails.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, headers, status);
    }

    /**
     * Handles exceptions thrown when the part of a "multipart/form-data" request identified by its name cannot be
     * found.
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            MissingServletRequestPartException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, headers, status);
    }

    /**
     * Handles exceptions thrown when binding errors are considered fatal.
     */
    @Override
    protected ResponseEntity<Object> handleBindException(
            BindException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, headers, status);
    }

    /**
     * Handles exception thrown when controller not found for an url.
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, headers, status);
    }

    /**
     * Handles exceptions thrown to be thrown when an async request times out.
     */
    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
            AsyncRequestTimeoutException exception, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE_FIELD, exception.getMessage());
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(response, headers, status);
    }

}
