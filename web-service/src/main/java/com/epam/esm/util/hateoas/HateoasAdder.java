package com.epam.esm.util.hateoas;

import com.epam.esm.dto.Mappable;
import org.springframework.data.domain.Page;

/**
 * interface providing methods for adding HATEOAS links to entity/entities
 *
 * @author bakhridinova
 */

public interface HateoasAdder<T extends Mappable> {
    void addLinksToEntity(T entity);

    default void addLinksToEntityPage(Page<T> entities) {
        entities.forEach(this::addLinksToEntity);
    }
}
