package dev.srebootcamp.controllers;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import dev.srebootcamp.clients.AuthenticationClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "authenticationApi")
@AutoConfigureMockMvc
@PactDirectory("../../pacts")
public class EndpointTest {
    @Autowired
    private MockMvc mockMvc;

    @Pact(provider = "authenticationApi", consumer = "loginApi")
    public RequestResponsePact createPactHappyPath(PactDslWithProvider builder) {
        return builder
                .given("test state")
                .uponReceiving("EndpointTest happy path")
                .path("/authentication")
                .method("POST")
                .body("{\"username\":\"admin\",\"password\":\"admin\"}")
                .willRespondWith()
                .status(200)
                .toPact();
    }
    @Pact(provider = "authenticationApi", consumer = "loginApi")
    public RequestResponsePact createPactUnhappyPath(PactDslWithProvider builder) {
        return builder
                .given("test state")
                .uponReceiving("EndpointTest unhappy path")
                .path("/authentication")
                .method("POST")
                .body("{\"username\":\"admin\",\"password\":\"notadmin\"}")
                .willRespondWith()
                .status(401)
                .toPact();
    }

    @Autowired
    AuthenticationClient client;

    @Test
    @PactTestFor(pactMethod = "createPactHappyPath", pactVersion = PactSpecVersion.V3)
    @ExtendWith(PactVerificationSpringProvider.class)
    public void test_loginApi_endpoint_happy_path(MockServer server) throws Exception {
        System.out.println("Url: " + server.getUrl());
        System.out.println("Url: " + server.getPort());
        client.authenticationClientUrl = server.getUrl();
        mockMvc.perform(post("/login")
                        .contentType("application/json")
                        .content("{\"username\":\"admin\",\"password\":\"admin\"}"))
                .andExpect(status().isOk());
    }
    @ExtendWith(PactVerificationSpringProvider.class)
    @Test
    @PactTestFor(pactMethod = "createPactUnhappyPath", pactVersion = PactSpecVersion.V3)
    public void test_loginApi_endpoint_unhappy_path(MockServer server) throws Exception {
        System.out.println("XUrl: " + server.getUrl());
        System.out.println("XUrl: " + server.getPort());
        client.authenticationClientUrl = server.getUrl();
        mockMvc.perform(post("/login")
                        .contentType("application/json")
                        .content("{\"username\":\"admin\",\"password\":\"notadmin\"}"))
                .andExpect(status().isUnauthorized());
    }
}