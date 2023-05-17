package com.epam.esm.service;

import com.epam.esm.dto.Mappable;
import org.springframework.data.domain.Page;

/**
 * wrapper service for entities
 *
 * @author bakhridinova
 */

public interface BaseService<T extends Mappable> {
    Page<T> findAllByPage(int page, int size);
    T findById(Long id);
    default T create(T t) {
        throw new UnsupportedOperationException();
    }
    default String deleteById(Long id) {
        throw new UnsupportedOperationException();
    }
}
