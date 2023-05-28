package com.epam.esm.util.enums.field;

import com.epam.esm.entity.Token;

/**
 * Enum representing token fields
 *
 * @author bakhridinova
 */

public enum TokenField implements EntityField<Token> {
    ID,
    ACCESS_TOKEN,
    TYPE,
    CREATED_AT,
    EXPIRED,
    USER;

    @Override
    public String getName() {
        return type().getSimpleName().toLowerCase() + " " + name().toLowerCase();
    }

    @Override
    public Class<Token> type() {
        return Token.class;
    }
}
