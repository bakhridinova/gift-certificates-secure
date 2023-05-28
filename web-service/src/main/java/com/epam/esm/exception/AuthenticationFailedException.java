package com.epam.esm.exception;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Custom exception for authentication failure
 *
 * @see com.epam.esm.security.authentication.manager.CustomAuthenticationManager#authenticate(Authentication)
 * @author bakhridinova
 */

public class AuthenticationFailedException extends AuthenticationException {
    public AuthenticationFailedException(String username) {
        super("authentication failed for " + username);
    }
}
