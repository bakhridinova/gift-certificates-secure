package com.epam.esm.security.authentication.filter;

import com.epam.esm.security.authentication.CustomBearerAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * custom authentication filter holding
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

        if (authorizationHeader == null ||
                !authorizationHeader.startsWith(BASIC_AUTHORIZATION_HEADER_PREFIX) ||
                !authorizationHeader.startsWith(BEARER_AUTHORIZATION_HEADER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authentication = authenticationManager.authenticate(
                new CustomBearerAuthentication(false, authorizationHeader
                        .substring(authorizationHeader.length() + 1)));

        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
    }
}
