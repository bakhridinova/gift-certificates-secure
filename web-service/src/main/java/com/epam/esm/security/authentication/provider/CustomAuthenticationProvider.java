package com.epam.esm.security.authentication.provider;

import com.epam.esm.exception.CustomAuthenticationFailedException;
import com.epam.esm.security.authentication.CustomBasicAuthentication;
import com.epam.esm.security.authentication.CustomBearerAuthentication;
import com.epam.esm.security.authentication.manager.CustomAuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * custom authentication provider validating
 * authentications based on their types
 *
 * @see CustomAuthenticationManager#authenticationProviders
 * @author bakhridinova
 */

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        if (authentication instanceof CustomBasicAuthentication basicAuthentication) {
            String base64Credentials = basicAuthentication.getUsernameAndPassword();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            final String[] values = credentials.split(":", 2);
            String username = values[0];
            String password = values[1];

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(username);

            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new CustomAuthenticationFailedException(username);
            }

            basicAuthentication.setAuthenticated(true);
            basicAuthentication.setAuthorities(userDetails.getAuthorities());
            return basicAuthentication;
        }

        CustomBearerAuthentication bearerAuthentication =
                (CustomBearerAuthentication) authentication;

        String token = bearerAuthentication.getToken();
        String username = jwtService.extractUsername(token);

        UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);

        if (!jwtService.isTokenValid(token, userDetails)) {
            throw new CustomAuthenticationFailedException(username);
        }

        bearerAuthentication.setAuthenticated(true);
        bearerAuthentication.setAuthorities(userDetails.getAuthorities());
        return bearerAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomBearerAuthentication.class) ||
                authentication.equals(CustomBasicAuthentication.class);
    }
}
