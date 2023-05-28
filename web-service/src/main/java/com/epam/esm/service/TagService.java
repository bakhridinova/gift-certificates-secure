package com.epam.esm.service;


import com.epam.esm.entity.Tag;

/**
 * Interface holding business logic for tags
 *
 * @author bakhridinova
 */

public interface TagService extends BaseService<Tag> {
    /**
     * Finds the most widely used tag of a user
     * with the highest cost of all orders
     *
     * @return specified tag
     */
    Tag findSpecial();
}
