package dev.srebootcamp.controllers;

import dev.srebootcamp.ApiApplication;
import dev.srebootcamp.service.ErrorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {MockController.class, MockErrorService.class})
@AutoConfigureMockMvc
public class EndpointTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MockErrorService errors;


    @BeforeEach
    public void setup() {
        errors.clear();
    }

    @Test
    public void test_errorQueue_endpoint() throws Exception {
        mockMvc.perform(post("/errors").content("someErrorMsg"))
                .andExpect(status().isOk());
        List<String> msgs = errors.getMsgs();
        assertEquals(1, msgs.size());
        assertEquals("someErrorMsg", msgs.get(0));
    }
}