package com.epam.esm.controller;

import com.epam.esm.facade.TokenFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author bakhridinova
 */

@WebMvcTest(TagController.class)
public class TokenControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenFacade tokenFacade;

    @Test
    void contextLoads() {
        assertNotNull(tokenFacade);
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void getAll_ShouldBeForbiddenForGuest() throws Exception {
        this.mockMvc.perform(get("/api/tokens")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    void getAll_ShouldBeForbiddenForUser() throws Exception {
        this.mockMvc.perform(get("/api/tokens")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAll_ShouldNotBeForbiddenForAdmin() throws Exception {
        this.mockMvc.perform(get("/api/tokens")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void getById_ShouldBeForbiddenForGuest() throws Exception {
        this.mockMvc.perform(get("/api/tokens/id")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    void getById_ShouldBeForbiddenForUser() throws Exception {
        this.mockMvc.perform(get("/api/tokens/id")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getById_ShouldNotBeForbiddenForAdmin() throws Exception {
        this.mockMvc.perform(get("/api/tokens/id")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void getByUserId_ShouldBeForbiddenForGuest() throws Exception {
        this.mockMvc.perform(get("/api/tokens/search")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    void getByUserId_ShouldBeForbiddenForUser() throws Exception {
        this.mockMvc.perform(get("/api/tokens/search")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getByUserId_ShouldNotBeForbiddenForAdmin() throws Exception {
        this.mockMvc.perform(get("/api/tokens/search")).andDo(print())
                .andExpect(isForbidden(false));
    }
}
