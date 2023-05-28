package com.epam.esm.security.authentication.manager;

import com.epam.esm.exception.AuthenticationUnsupportedException;
import com.epam.esm.security.authentication.filter.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Custom authentication manager filtering
 * authentication providers based on authentication types
 *
 * @see CustomAuthenticationFilter#authenticationManager
 * @author bakhridinova
 */

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private final List<AuthenticationProvider> authenticationProviders;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authenticationProviders.stream()
                .filter(authenticationProvider -> authenticationProvider.supports(authentication.getClass()))
                .findAny().orElseThrow(() -> new AuthenticationUnsupportedException(authentication.getClass()))
                .authenticate(authentication);
    }
}
