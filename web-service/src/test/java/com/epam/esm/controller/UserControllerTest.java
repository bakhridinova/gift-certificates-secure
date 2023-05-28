package com.epam.esm.controller;

import com.epam.esm.facade.UserFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(UserController.class)
class UserControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFacade userFacade;

    @Test
    void contextLoads() {
        assertNotNull(userFacade);
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void getAll_ShouldBeForbiddenForGuest() throws Exception {
        this.mockMvc.perform(get("/api/users")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    void getAll_ShouldNotBeForbiddenForUser() throws Exception {
        this.mockMvc.perform(get("/api/users")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAll_ShouldNotBeForbiddenForAdmin() throws Exception {
        this.mockMvc.perform(get("/api/users")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void getById_ShouldBeForbiddenForGuest() throws Exception {
        this.mockMvc.perform(get("/api/users")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    void getById_ShouldNotBeForbiddenForUser() throws Exception {
        this.mockMvc.perform(get("/api/users")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getById_ShouldNotBeForbiddenForAdmin() throws Exception {
        this.mockMvc.perform(get("/api/users")).andDo(print())
                .andExpect(isForbidden(false));
    }


    @Test
    @WithMockUser
    void signUp_ShouldNotRequireAuthorization() throws Exception {
        this.mockMvc.perform(get("/api/users/register")).andDo(print())
                .andExpect(isForbidden(false));
    }


    @Test
    @WithMockUser
    void sigIn_ShouldNotRequireAuthorization() throws Exception {
        this.mockMvc.perform(get("/api/users/authenticate")).andDo(print())
                .andExpect(isForbidden(false));
    }
}