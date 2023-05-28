package com.epam.esm.util.enums.field;

import org.springframework.data.domain.Sort;

/**
 * Enum representing sort fields
 *
 * @author bakhridinova
 */

public enum SortField implements EntityField<Sort> {
    PROPERTY,
    ORDER;

    @Override
    public String getName() {
        return type().getSimpleName().toLowerCase() + " " + name().toLowerCase();
    }

    @Override
    public Class<Sort> type() {
        return Sort.class;
    }
}
