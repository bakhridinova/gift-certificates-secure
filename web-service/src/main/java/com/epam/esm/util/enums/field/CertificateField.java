package com.epam.esm.util.enums.field;

import com.epam.esm.entity.Certificate;

/**
 * Enum representing certificate fields
 *
 * @author bakhridinova
 */

public enum CertificateField implements EntityField<Certificate> {
    ID,
    NAME,
    DESCRIPTION,
    PRICE,
    DURATION,
    CREATED_AT,
    LAST_UPDATED_AT,
    USER_ID,
    TAGS;

    @Override
    public String getName() {
        return type().getSimpleName().toLowerCase() + " " + name().toLowerCase();
    }

    @Override
    public Class<Certificate> type() {
        return Certificate.class;
    }
}
