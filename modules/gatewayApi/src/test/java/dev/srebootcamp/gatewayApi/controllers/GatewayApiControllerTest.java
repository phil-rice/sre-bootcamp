package dev.srebootcamp.gatewayApi.controllers;

import dev.srebootcamp.gatewayApi.domain.MockEndpointToUrlMapping;
import dev.srebootcamp.gatewayApi.domain.MockPassThruClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = {GatewayApiController.class, MockEndpointToUrlMapping.class})
@AutoConfigureMockMvc
@Import(MockPassThruClient.class)
public class GatewayApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_gatewayApi_get_endpoint() throws Exception {
        mockMvc.perform(get("/test1/two"))
                .andExpect(content().string("http://localhost:8080/Request[method=GET, url=/test1/two, body=, headers=[]]"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_gatewayApi_post_endpoint() throws Exception {
        mockMvc.perform(post("/test2/two"))
                .andExpect(content().string("http://localhost:8081/Request[method=POST, url=/test2/two, body=, headers=[]]"))
                .andExpect(status().isOk());
    }
}