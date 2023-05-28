package com.epam.esm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;

/**
 * @author bakhridinova
 */

@ContextConfiguration(classes = AuthenticationManager.class)
public class ControllerTest {
    protected ResultMatcher isForbidden(boolean forbidden) {
        if (forbidden) {
            return result -> assertEquals("Status",
                    HttpStatus.FORBIDDEN.value(), result.getResponse().getStatus());
        }
        return result -> assertNotEquals("Status",
                HttpStatus.FORBIDDEN.value(), result.getResponse().getStatus());
    }
}
