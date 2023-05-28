package com.epam.esm.exception;

import com.epam.esm.entity.AbstractEntity;

/**
 * Custom exception for "not found" error
 *
 * @author bakhridinova
 */

public class EntityNotFoundException extends RuntimeException {
    public <T extends AbstractEntity> EntityNotFoundException(Class<T> tClass, Long id) {
        super(String.format("failed to find %s by id %d", tClass.getSimpleName(), id));
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
