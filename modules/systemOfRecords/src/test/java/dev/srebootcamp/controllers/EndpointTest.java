package dev.srebootcamp.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EndpointTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_systemOfRecords_customer_endpoint() throws Exception {
        mockMvc.perform(get("/systemofrecords/customer/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":\"1\",\"name\":\"Customer 1\"}"));
    }
   @Test
    public void test_systemOfRecords_mandate_endpoint() throws Exception {
        mockMvc.perform(get("/systemofrecords/mandate/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"mandateId\":\"1\",\"customerId\":\"101\",\"accountId\":\"1001\",\"permissions\":\"all\"}"));
    }
}