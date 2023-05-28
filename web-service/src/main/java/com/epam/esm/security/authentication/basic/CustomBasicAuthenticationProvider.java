package com.epam.esm.security.authentication.basic;

import com.epam.esm.exception.AuthenticationFailedException;
import com.epam.esm.security.authentication.manager.CustomAuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Custom authentication provider validating
 * basic authentication
 *
 * @see CustomAuthenticationManager#authenticationProviders
 * @author bakhridinova
 */

@Component
@RequiredArgsConstructor
public class CustomBasicAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        CustomBasicAuthentication basicAuthentication =
                (CustomBasicAuthentication) authentication;
            UserDetails userDetails = userDetailsService
                    .loadUserByUsername(basicAuthentication.getUsername());

            if (!passwordEncoder.matches(basicAuthentication.getPassword(), userDetails.getPassword())) {
                throw new AuthenticationFailedException(basicAuthentication.getUsername());
            }

            basicAuthentication.setAuthenticated(true);
            basicAuthentication.setAuthorities(userDetails.getAuthorities());
            return basicAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomBasicAuthentication.class);
    }
}
