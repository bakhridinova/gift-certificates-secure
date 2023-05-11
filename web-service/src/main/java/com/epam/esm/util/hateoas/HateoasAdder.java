package com.epam.esm.util.hateoas;

import org.springframework.data.domain.Page;

/**
 * interface providing methods for adding HATEOAS links to entity/entities
 *
 * @author bakhridinova
 */

public interface HateoasAdder<T> {
    void addLinksToEntity(T entity);

    default void addLinksToEntityPage(Page<T> entities) {
        entities.forEach(this::addLinksToEntity);
    }
}
