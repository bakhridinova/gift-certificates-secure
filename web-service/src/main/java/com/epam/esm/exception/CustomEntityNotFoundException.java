package com.epam.esm.exception;

import com.epam.esm.entity.AbstractEntity;

/**
 * custom exception for "not found" error
 *
 * @author bakhridinova
 */

public class CustomEntityNotFoundException extends RuntimeException {
    public <T extends AbstractEntity> CustomEntityNotFoundException(Class<T> tClass, Long id) {
        super(String.format("failed to find %s by id %d", tClass.getName(), id));
    }

    public CustomEntityNotFoundException(String message) {
        super(message);
    }
}
