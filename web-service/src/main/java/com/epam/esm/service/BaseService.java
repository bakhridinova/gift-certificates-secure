package com.epam.esm.service;

import com.epam.esm.entity.AbstractEntity;
import org.springframework.data.domain.Page;

/**
 * Wrapper service for entities
 *
 * @author bakhridinova
 */

public interface BaseService<T extends AbstractEntity> {
    Page<T> findAllByPage(int page, int size);
    T findById(Long id);

    /**
     * Default create method implemented by some of the entity services
     *
     * @throws UnsupportedOperationException if method is called by service
     * which does not support entity creation
     * @param t entity to be created
     * @return created entity
     */
    default T create(T t) {
        throw new UnsupportedOperationException();
    }

    /**
     * Default delete method implemented by some of the entity services
     *
     * @throws UnsupportedOperationException if method is called by service
     * which does not support entity deletion
     * @param id of entity to be deleted
     */
    default void deleteById(Long id) {
        throw new UnsupportedOperationException();
    }
}
