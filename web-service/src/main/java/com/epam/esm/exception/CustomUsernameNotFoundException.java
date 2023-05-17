package com.epam.esm.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * custom exception for not found username
 *
 * @author bakhridinova
 */

public class CustomUsernameNotFoundException extends UsernameNotFoundException {
    public CustomUsernameNotFoundException(String username) {
        super("failed to find user with username " + username);
    }
}
