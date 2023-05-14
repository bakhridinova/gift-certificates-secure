package com.epam.esm.security.authentication.filter;

import com.epam.esm.security.authentication.CustomAuthentication;
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

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;

    private static final String AUTHENTICATION_USERNAME_PARAMETER = "username";
    private static final String AUTHENTICATION_PASSWORD_PARAMETER = "password";

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = authenticationManager.authenticate(
                CustomAuthentication.builder()
                        .authenticated(false)
                        .username(request.getParameter(AUTHENTICATION_USERNAME_PARAMETER))
                        .password(request.getParameter(AUTHENTICATION_PASSWORD_PARAMETER))
                        .build());

        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
    }
}
