package com.epam.esm.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author bakhridinova
 */

public class CustomTokenExpiredException extends AuthenticationException {
    public CustomTokenExpiredException(String username) {
        super("access token for user " + username + " is expired");
    }
}
