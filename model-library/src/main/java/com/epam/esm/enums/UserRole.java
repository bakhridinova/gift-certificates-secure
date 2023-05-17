package com.epam.esm.enums;

import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * enum representing user roles
 *
 * @author bakhridinova
 */

@RequiredArgsConstructor
public enum UserRole {
    GUEST,
    USER,
    ADMIN;

    public List<String> getAuthorities() {
        return List.of("ROLE_" + this.name());
    }
}