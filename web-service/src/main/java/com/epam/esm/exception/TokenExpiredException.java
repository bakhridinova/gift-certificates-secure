package com.epam.esm.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Custom exception for "token expired" error
 *
 * @author bakhridinova
 */

public class TokenExpiredException extends AuthenticationException {
    public TokenExpiredException(String username) {
        super("access token for user " + username + " is expired");
    }
}
