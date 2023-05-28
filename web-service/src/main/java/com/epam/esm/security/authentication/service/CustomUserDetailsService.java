package com.epam.esm.security.authentication.service;

import com.epam.esm.exception.UsernameNotFoundException;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.security.authentication.basic.CustomBasicAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Custom user details service loading user data from database
 *
 * @see CustomBasicAuthenticationProvider#userDetailsService
 * @author bakhridinova
 */

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws org.springframework.security.core.userdetails.UsernameNotFoundException {
        return new CustomUserDetails(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username)));
    }
}
