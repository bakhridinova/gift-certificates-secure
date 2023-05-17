package com.epam.esm.controller;

import com.epam.esm.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author bakhridinova
 */

@WebMvcTest(TagController.class)
@ContextConfiguration(classes = AuthenticationManager.class)
public class TokenControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenService tokenService;

    @Test
    void contextLoads() {
        assertNotNull(tokenService);
    }
}
