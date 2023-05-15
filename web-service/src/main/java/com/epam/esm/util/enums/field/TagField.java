package com.epam.esm.util.enums.field;

import com.epam.esm.entity.Tag;

/**
 * enum representing tag fields
 *
 * @author bakhridinova
 */

public enum TagField implements EntityField<Tag> {
    ID,
    NAME;

    @Override
    public String getName() {
        return type().getSimpleName().toLowerCase() + " " + name().toLowerCase();
    }

    @Override
    public Class<Tag> type() {
        return Tag.class;
    }
}
