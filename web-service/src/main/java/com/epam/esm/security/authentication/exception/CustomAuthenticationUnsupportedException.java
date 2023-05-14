package com.epam.esm.security.authentication.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author bakhridinova
 */

public class CustomAuthenticationUnsupportedException extends AuthenticationException {
    public CustomAuthenticationUnsupportedException(Class<?> aclass) {
        super("authentication not supported for " + aclass);
    }
}
