package com.epam.esm.security.authentication.bearer;

import com.epam.esm.entity.Token;
import com.epam.esm.exception.TokenExpiredException;
import com.epam.esm.exception.TokenNotFoundException;
import com.epam.esm.repository.TokenRepository;
import com.epam.esm.security.authentication.manager.CustomAuthenticationManager;
import com.epam.esm.security.authentication.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Custom authentication provider validating
 * bearer authentication
 *
 * @see CustomAuthenticationManager#authenticationProviders
 * @author bakhridinova
 */

@Component
@RequiredArgsConstructor
public class CustomBearerAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        CustomBearerAuthentication bearerAuthentication =
                (CustomBearerAuthentication) authentication;

        String token = bearerAuthentication.getToken();
        String username = jwtService.extractUsername(token);

        UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);

        Optional<Token> optionalToken = tokenRepository.findByAccessToken(token);
        if (optionalToken.isEmpty()) {
            throw new TokenNotFoundException(username);
        }

        Token tokenObject = optionalToken.get();
        if (tokenObject.isExpired()) {
            throw new TokenExpiredException(username);
        }

        bearerAuthentication.setAuthenticated(true);
        bearerAuthentication.setAuthorities(userDetails.getAuthorities());
        return bearerAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomBearerAuthentication.class);
    }
}
