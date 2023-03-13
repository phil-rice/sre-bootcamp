package dev.srebootcamp.location.clients;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static dev.srebootcamp.location.LocationFixture.trucks;
import static dev.srebootcamp.location.LocationFixture.trucksJson;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "edriverapp")
@PactDirectory("../../pacts/new")
public class SmartphoneClientContractTest {

    @Pact(provider = "edriverapp", consumer = "leo")
    public RequestResponsePact createPactHappyPath(PactDslWithProvider builder) throws JsonProcessingException {
        return builder
                .given("test state")
                .uponReceiving("a request for the truck/vehicle locations")
                .path("/trucklocations")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(trucksJson())
                .headers(Map.of("Content-Type", "application/json"))
                .toPact();
    }


    @Test
    @ExtendWith(PactVerificationSpringProvider.class)
    public void test_mandates_api(MockServer server) {
        SmartphoneClient client = new SmartphoneClient(server.getUrl() + "/trucklocations");
        assertEquals(trucks, client.retrieveTrucksAndPosition());
    }
}