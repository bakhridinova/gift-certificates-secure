package com.epam.esm.security.authentication.provider;

import com.epam.esm.security.authentication.CustomAuthentication;
import com.epam.esm.exception.CustomAuthenticationFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        CustomAuthentication customAuthentication =
                (CustomAuthentication) authentication;

        String username = customAuthentication.getUsername();
        String rawPassword = customAuthentication.getPassword();
        String encodedPassword = userDetailsService.loadUserByUsername(username).getPassword();

        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new CustomAuthenticationFailedException(username);
        }

        return new CustomAuthentication(true, username, rawPassword);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthentication.class);
    }
}
