package com.epam.esm.controller;

import com.epam.esm.facade.OrderFacade;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(OrderController.class)
class OrderControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderFacade orderFacade;

    @Test
    void contextLoads() {
        assertNotNull(mockMvc);
        assertNotNull(orderFacade);
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void getAll_ShouldBeForbiddenForGuest() throws Exception {
        this.mockMvc.perform(get("/api/orders")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    void getAll_ShouldNotBeForbiddenForUser() throws Exception {
        this.mockMvc.perform(get("/api/orders")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAll_ShouldNotBeForbiddenForAdmin() throws Exception {
        this.mockMvc.perform(get("/api/orders")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void getById_ShouldBeForbiddenForGuest() throws Exception {
        this.mockMvc.perform(get("/api/orders/id")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    void getById_ShouldNotBeForbiddenForUser() throws Exception {
        this.mockMvc.perform(get("/api/orders/id")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getById_ShouldNotBeForbiddenForAdmin() throws Exception {
        this.mockMvc.perform(get("/api/orders/id")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void payById_ShouldBeForbiddenForGuest() throws Exception {
        this.mockMvc.perform(patch("/api/orders/id/pay")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    void payById_ShouldNotBeForbiddenForUser() throws Exception {
        this.mockMvc.perform(patch("/api/orders/id/pay")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void payById_ShouldNotBeForbiddenForAdmin() throws Exception {
        this.mockMvc.perform(patch("/api/orders/id/pay")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void cancelById_ShouldBeForbiddenForGuest() throws Exception {
        this.mockMvc.perform(patch("/api/orders/id/cancel")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    void cancelById_ShouldNotBeForbiddenForUser() throws Exception {
        this.mockMvc.perform(patch("/api/orders/id/cancel")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void cancelById_ShouldNotBeForbiddenForAdmin() throws Exception {
        this.mockMvc.perform(patch("/api/orders/id/cancel")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void create_ShouldBeForbiddenForGuest() throws Exception {
        this.mockMvc.perform(post("/api/orders/id")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    void create_ShouldNotBeForbiddenForUser() throws Exception {
        this.mockMvc.perform(post("/api/orders/id")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void create_ShouldNotBeForbiddenForAdmin() throws Exception {
        this.mockMvc.perform(post("/api/orders/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject().toString())).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void getByUserId_ShouldBeForbiddenForGuest() throws Exception {
        this.mockMvc.perform(get("/api/orders/search")).andDo(print())
                .andExpect(isForbidden(true));
    }

    @Test
    @WithMockUser(roles = "USER")
    void getByUserId_ShouldNotBeForbiddenForUser() throws Exception {
        this.mockMvc.perform(get("/api/orders/search")).andDo(print())
                .andExpect(isForbidden(false));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getByUserId_ShouldNotBeForbiddenForAdmin() throws Exception {
        this.mockMvc.perform(get("/api/orders/search")).andDo(print())
                .andExpect(isForbidden(false));
    }
}