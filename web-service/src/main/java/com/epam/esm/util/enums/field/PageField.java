package com.epam.esm.util.enums.field;

import org.springframework.data.domain.Page;

/**
 * enum representing pagination fields
 *
 * @author bakhridinova
 */

public enum PageField implements EntityField<Page> {
    NUMBER,
    SIZE;

    @Override
    public String getName() {
        return type().getSimpleName().toLowerCase() + " " + name().toLowerCase();
    }

    @Override
    public Class<Page> type() {
        return Page.class;
    }
}
