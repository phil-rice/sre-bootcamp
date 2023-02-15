package dev.srebootcamp.mandatesApi.controllers;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import dev.srebootcamp.mandatesApi.client.MandateClient;
import dev.srebootcamp.providerValidation.AbstractApiProviderValidationIT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "systemofrecords")
@AutoConfigureMockMvc
@PactDirectory("../../pacts/new")
public class MandatesApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    String expectedBody = "[{\"mandateId\":\"fromCustomerId_man\",\"customerId\":\"fromCustomerId\",\"accountId\":\"fromCustomerId_acc_id\",\"permissions\":\"all\"}]";

    @Pact(provider = "systemofrecords", consumer = "mandatesApi")
    public RequestResponsePact createPactHappyPath(PactDslWithProvider builder) {
        return builder
                .given("test state")
                .uponReceiving("EndpointTest happy path")
                .path("/systemofrecords/mandate/fromCustomerId")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(expectedBody)
                .headers(Map.of("Content-Type", "application/json"))
                .toPact();
    }


    @Autowired
    MandateClient client;

    @Test

    @ExtendWith(PactVerificationSpringProvider.class)
    public void test_mandates_api(MockServer server) throws Exception {
        client.mandateClientUrl = server.getUrl();
        mockMvc.perform(get("/mandates?customer_id=fromCustomerId")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(expectedBody))
                .andExpect(status().isOk());
    }

}