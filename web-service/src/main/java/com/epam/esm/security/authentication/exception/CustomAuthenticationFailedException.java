package com.epam.esm.security.authentication.exception;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationFailedException extends AuthenticationException {
    public CustomAuthenticationFailedException(String username) {
        super("authentication failed for " + username);
    }
}
