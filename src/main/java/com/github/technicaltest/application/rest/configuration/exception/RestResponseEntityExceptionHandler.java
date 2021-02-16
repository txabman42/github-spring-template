package com.github.technicaltest.application.rest.configuration.exception;

import com.github.technicaltest.application.rest.configuration.exception.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ValidationException;
import java.util.Date;

@Slf4j
@ControllerAdvice(annotations = { RestController.class, Controller.class })
public class RestResponseEntityExceptionHandler extends BaseResponseEntityExceptionHandler {

    public void PriceRestResponseEntityExceptionHandler() {
        log.info("Enabled error handler");
    }

    private static final String DEFAULT_MESSAGE = "There was an error, please contact support";

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleDefault(Exception ex, WebRequest request) {

        ErrorResponse error;
        HttpStatus httpStatus;

        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            httpStatus = responseStatus.code();
            String errorMessage;
            responseStatus.reason();
            if (responseStatus.reason().length() > 0) {
                errorMessage = responseStatus.reason();
            } else {
                errorMessage = ex.getMessage();
            }
            error = ErrorResponse.builder()
                    .code(httpStatus.value())
                    .type(httpStatus.name())
                    .message(DEFAULT_MESSAGE)
                    .debug(errorMessage)
                    .timestamp(new Date())
                    .path(resourcePath(request))
                    .build();
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            error = ErrorResponse.builder()
                    .code(httpStatus.value())
                    .type(httpStatus.name())
                    .message(DEFAULT_MESSAGE)
                    .debug(ex.getMessage())
                    .timestamp(new Date())
                    .path(resourcePath(request))
                    .build();
        }

        log.error("Internal server error.", ex);
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    private ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        log.error("Illegal Argument error.", ex);
        return exception2ErrorResponse(ex, DEFAULT_MESSAGE, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { ValidationException.class })
    private ResponseEntity<ErrorResponse> handleIllegalArgument(ValidationException ex, WebRequest request) {
        log.error("Validation error.", ex);
        return exception2ErrorResponse(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = GithubNotFoundException.class)
    private ResponseEntity<ErrorResponse> handlePriceNotFound(GithubNotFoundException ex, WebRequest request) {
        log.error("Not found error.", ex);
        return exception2ErrorResponse(ex, HttpStatus.NOT_FOUND, request);
    }

}