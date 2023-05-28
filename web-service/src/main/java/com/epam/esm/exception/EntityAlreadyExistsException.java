package com.epam.esm.exception;

/**
 * Custom exception for "unique constraint" error
 *
 * @author bakhridinova
 */

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
