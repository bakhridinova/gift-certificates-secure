package com.epam.esm.controller;

import com.epam.esm.facade.CertificateFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(CertificateController.class)
class CertificateControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CertificateFacade certificateFacade;

    @Test
    void contextLoads() {
        assertNotNull(mockMvc);
        assertNotNull(certificateFacade);
    }

    @Test
    @WithMockUser
    void getAll_ShouldNotRequireAuthorization() throws Exception {
        this.mockMvc.perform(get("/api/certificates")).andDo(print())
                .andExpect(isForbidden(false));
    }


    @Test
    @WithMockUser
    void getById_ShouldNotRequireAuthorization() throws Exception {
        this.mockMvc.perform(get("/api/certificates/id")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void search_ShouldBeForbiddenForGuest() throws Exception {
        this.mockMvc.perform(post("/api/certificates/search")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    void search_ShouldNotBeForbiddenForUser() throws Exception {
        this.mockMvc.perform(post("/api/certificates/search")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void search_ShouldNotBeForbiddenForAdmin() throws Exception {
        this.mockMvc.perform(post("/api/certificates/search")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void create_ShouldBeForbiddenForGuest() throws Exception {
        this.mockMvc.perform(post("/api/certificates")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    void create_ShouldBeForbiddenForUser() throws Exception {
        this.mockMvc.perform(post("/api/certificates")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void create_ShouldNotBeForbiddenForAdmin() throws Exception {
        this.mockMvc.perform(post("/api/certificates")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void updatePrice_ShouldBeForbiddenForGuest() throws Exception {
        this.mockMvc.perform(patch("/api/certificates/id")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    void updatePrice_ShouldBeForbiddenForUser() throws Exception {
        this.mockMvc.perform(patch("/api/certificates/id")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updatePrice_ShouldNotBeForbiddenForAdmin() throws Exception {
        this.mockMvc.perform(patch("/api/certificates/id")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void deleteById_ShouldBeForbiddenForGuest() throws Exception {
        this.mockMvc.perform(delete("/api/certificates/id")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    void deleteById_ShouldBeForbiddenForUser() throws Exception {
        this.mockMvc.perform(delete("/api/certificates/id")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteById_ShouldNotBeForbiddenForAdmin() throws Exception {
        this.mockMvc.perform(delete("/api/certificates/id")).andDo(print())
                .andExpect(isForbidden(false));
    }
}