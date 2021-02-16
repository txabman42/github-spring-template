package com.github.technicaltest.application.rest.configuration.exception;

import com.github.technicaltest.application.rest.configuration.exception.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

public class BaseResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String DEFAULT_MESSAGE = "There was an error, please contact support";

    protected ResponseEntity<ErrorResponse> exception2ErrorResponse(Exception ex, String userMessage, HttpStatus status, WebRequest request) {
        userMessage = userMessage != null ? userMessage : DEFAULT_MESSAGE;
        ErrorResponse error = ErrorResponse.builder()
                .code(status.value())
                .type(status.name())
                .message(userMessage)
                .debug(ex.getMessage())
                .timestamp(new Date())
                .path(resourcePath(request))
                .build();
        return new ResponseEntity<>(error, status);
    }

    protected ResponseEntity<ErrorResponse> exception2ErrorResponse(Exception ex, HttpStatus status, WebRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .code(status.value())
                .type(status.name())
                .debug(ex.getMessage())
                .timestamp(new Date())
                .path(resourcePath(request))
                .build();
        return new ResponseEntity<>(error, status);
    }

    protected String resourcePath(WebRequest webRequest) {
        if (webRequest instanceof ServletWebRequest) {
            ServletWebRequest request = (ServletWebRequest) webRequest;
            return String.format("[%s] %s", request.getHttpMethod(), request.getRequest().getRequestURI());
        }
        return "";
    }
}
