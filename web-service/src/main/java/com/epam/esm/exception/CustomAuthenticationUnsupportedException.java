package com.epam.esm.exception;

import com.epam.esm.security.authentication.provider.CustomAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * custom exception for unsupported authentication
 *
 * @see CustomAuthenticationProvider#authenticate(Authentication)
 * @author bakhridinova
 */


public class CustomAuthenticationUnsupportedException extends AuthenticationException {
    public CustomAuthenticationUnsupportedException(Class<?> aclass) {
        super("authentication not supported for " + aclass);
    }
}
