package com.epam.esm.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Custom exception for "token not found" error
 *
 * @author bakhridinova
 */

public class TokenNotFoundException extends AuthenticationException {
    public TokenNotFoundException(String username) {
        super("failed to find access token for " + username);
    }
}
