package com.epam.esm.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author bakhridinova
 */

public class CustomTokenNotFoundException extends AuthenticationException {
    public CustomTokenNotFoundException(String username) {
        super("failed to find access token for " + username);
    }
}
