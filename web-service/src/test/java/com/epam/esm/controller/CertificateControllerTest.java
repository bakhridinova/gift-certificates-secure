package com.epam.esm.controller;

import com.epam.esm.service.CertificateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(CertificateController.class)
@ContextConfiguration(classes = AuthenticationManager.class)
class CertificateControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CertificateService certificateService;

    @Test
    void contextLoads() {
        assertNotNull(certificateService);
    }
}