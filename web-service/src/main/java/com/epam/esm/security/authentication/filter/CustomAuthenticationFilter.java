package com.epam.esm.security.authentication.filter;

import com.epam.esm.security.authentication.basic.CustomBasicAuthentication;
import com.epam.esm.security.authentication.bearer.CustomBearerAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Custom authentication filter holding
 * basic and bearer authorization logic
 *
 * @author bakhridinova
 */

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BASIC_AUTHORIZATION_HEADER_PREFIX = "Basic";
    private static final String BEARER_AUTHORIZATION_HEADER_PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (authorizationHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authorizationHeader.startsWith(BASIC_AUTHORIZATION_HEADER_PREFIX)) {
            SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(
                    new CustomBasicAuthentication(false, authorizationHeader
                            .substring(BASIC_AUTHORIZATION_HEADER_PREFIX.length() + 1))));
            filterChain.doFilter(request, response);
            return;
        }

        if (authorizationHeader.startsWith(BEARER_AUTHORIZATION_HEADER_PREFIX)) {
            SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(
                    new CustomBearerAuthentication(false, authorizationHeader
                            .substring(BEARER_AUTHORIZATION_HEADER_PREFIX.length() + 1))));
            filterChain.doFilter(request, response);
        }
    }
}
