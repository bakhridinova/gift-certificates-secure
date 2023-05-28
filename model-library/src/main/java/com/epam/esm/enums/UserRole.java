package com.epam.esm.enums;

import java.util.List;

/**
 * Enum representing user roles
 *
 * @author bakhridinova
 */

public enum UserRole {
    GUEST,
    USER,
    ADMIN;

    public List<String> getAuthorities() {
        return List.of("ROLE_" + this.name());
    }
}