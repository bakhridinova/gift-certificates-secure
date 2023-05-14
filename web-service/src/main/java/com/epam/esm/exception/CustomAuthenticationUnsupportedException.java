package com.epam.esm.exception;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * custom exception for unsupported authentication
 *
 * @see com.epam.esm.security.authentication.provider.CustomAuthenticationProvider#authenticate(Authentication)
 * @author bakhridinova
 */


public class CustomAuthenticationUnsupportedException extends AuthenticationException {
    public CustomAuthenticationUnsupportedException(Class<?> aclass) {
        super("authentication not supported for " + aclass);
    }
}
