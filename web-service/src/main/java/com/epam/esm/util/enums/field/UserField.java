package com.epam.esm.util.enums.field;

import com.epam.esm.entity.User;

/**
 * enum representing user fields
 *
 * @author bakhridinova
 */

public enum UserField implements EntityField<User> {
    ID,
    ROLE,
    USERNAME,
    PASSWORD,
    FIRSTNAME,
    LASTNAME,
    EMAIL_ADDRESS,
    BIRTH_DATE;

    @Override
    public String getName() {
        return type().getSimpleName().toLowerCase() + " " + name().toLowerCase();
    }

    @Override
    public Class<User> type() {
        return User.class;
    }
}
