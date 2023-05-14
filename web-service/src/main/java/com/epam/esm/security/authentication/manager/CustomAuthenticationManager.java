package com.epam.esm.security.authentication.manager;

import com.epam.esm.exception.CustomAuthenticationUnsupportedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private final List<AuthenticationProvider> authenticationProviders;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authenticationProviders.stream()
                .filter(authenticationProvider -> authenticationProvider.supports(authentication.getClass()))
                .findAny().orElseThrow(() -> new CustomAuthenticationUnsupportedException(authentication.getClass()))
                .authenticate(authentication);
    }
}
