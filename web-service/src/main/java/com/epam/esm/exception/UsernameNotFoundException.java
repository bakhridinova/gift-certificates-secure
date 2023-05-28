package com.epam.esm.exception;

/**
 * Custom exception for not found username
 *
 * @author bakhridinova
 */

public class UsernameNotFoundException extends org.springframework.security.core.userdetails.UsernameNotFoundException {
    public UsernameNotFoundException(String username) {
        super("failed to find user with username " + username);
    }
}
