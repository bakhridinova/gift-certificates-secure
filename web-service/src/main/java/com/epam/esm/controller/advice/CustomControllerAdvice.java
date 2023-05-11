package com.epam.esm.controller.advice;

import com.epam.esm.controller.response.CustomResponse;
import com.epam.esm.exception.CustomEntityAlreadyExistsException;
import com.epam.esm.exception.CustomEntityNotFoundException;
import com.epam.esm.exception.CustomValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

/**
 * controller advice handling exceptions
 *
 * @author bakhridinova
 */

@ControllerAdvice
@RequiredArgsConstructor
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            @NonNull NoHandlerFoundException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        String errorMessage = "no handler found for "
                + ex.getHttpMethod() + " " + ex.getRequestURL();
        return new ResponseEntity<>(new CustomResponse<>(
                HttpStatus.NOT_FOUND, errorMessage), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            @NonNull HttpRequestMethodNotSupportedException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        StringBuilder errorMessage = new StringBuilder(ex.getMethod());
        errorMessage.append(" method is not supported for this request. supported methods are: ");
        Objects.requireNonNull(ex.getSupportedHttpMethods())
                .forEach(method -> errorMessage.append(method).append(" "));
        return new ResponseEntity<>(new CustomResponse<>(
                HttpStatus.METHOD_NOT_ALLOWED, errorMessage.toString()), headers, status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CustomResponse<?>> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        String errorMessage = ex.getName() + " should be of type "
                + Objects.requireNonNull(ex.getRequiredType()).getName();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new CustomResponse<>(status, errorMessage), status);
    }

    @ExceptionHandler({
            CustomValidationException.class,
            CustomEntityNotFoundException.class,
            CustomEntityAlreadyExistsException.class})
    public ResponseEntity<CustomResponse<?>> handleException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "something went wrong :(";

        if (ex instanceof CustomValidationException
                || ex instanceof CustomEntityAlreadyExistsException) {
            status = HttpStatus.BAD_REQUEST;
            message = ex.getMessage();
        }

        if (ex instanceof CustomEntityNotFoundException){
            status = HttpStatus.NOT_FOUND;
            message = ex.getMessage();
        }

        return new ResponseEntity<>(new CustomResponse<>(status, message), status);
    }
}