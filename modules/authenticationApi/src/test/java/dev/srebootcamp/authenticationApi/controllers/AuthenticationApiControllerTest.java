package dev.srebootcamp.authenticationApi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_authenticationApi_endpoint_when_username_equals_password() throws Exception {
        mockMvc.perform(post("/authentication").content("{\"username\":\"userpass\",\"password\":\"userpass\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_authenticationApi_endpoint_when_username_unequals_password() throws Exception {
        mockMvc.perform(post("/authentication").content("{\"username\":\"userpassx\",\"password\":\"userpassy\"}"))
                .andExpect(status().isUnauthorized());
    }
}
