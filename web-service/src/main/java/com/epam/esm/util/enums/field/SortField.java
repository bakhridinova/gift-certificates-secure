package com.epam.esm.util.enums.field;

import com.epam.esm.util.enums.EntityField;
import org.springframework.data.domain.Sort;

/**
 * enum representing sort fields
 *
 * @author bakhridinova
 */

public enum SortField implements EntityField<Sort> {
    TYPE,
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
