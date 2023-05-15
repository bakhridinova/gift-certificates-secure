package com.epam.esm.util.enums.field;

import com.epam.esm.entity.Order;

/**
 * enum representing order fields
 *
 * @author bakhridinova
 */

public enum OrderField implements EntityField<Order> {
    ID,
    PRICE,
    STATUS,
    CREATED_AT,
    USER,
    CERTIFICATE;

    @Override
    public String getName() {
        return type().getSimpleName().toLowerCase() + " " + name().toLowerCase();
    }

    @Override
    public Class<Order> type() {
        return Order.class;
    }
}
