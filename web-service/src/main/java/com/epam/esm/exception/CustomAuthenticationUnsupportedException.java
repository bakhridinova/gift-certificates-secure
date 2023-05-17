package com.epam.esm.exception;

import com.epam.esm.security.authentication.basic.CustomBasicAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * custom exception for unsupported authentication
 *
 * @see CustomBasicAuthenticationProvider#authenticate(Authentication)
 * @author bakhridinova
 */


public class CustomAuthenticationUnsupportedException extends AuthenticationException {
    public CustomAuthenticationUnsupportedException(Class<?> aclass) {
        super("authentication not supported for " + aclass);
    }
}
