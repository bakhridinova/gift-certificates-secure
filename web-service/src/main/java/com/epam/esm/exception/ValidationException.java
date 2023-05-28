package com.epam.esm.exception;

/**
 * Custom exception for "validation failed" error
 *
 * @see com.epam.esm.util.validator
 * @author bakhridinova
 */

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
