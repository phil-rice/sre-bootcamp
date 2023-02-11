package dev.srebootcamp.controllers;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import dev.srebootcamp.client.MandateClient;
import dev.srebootcamp.pactSubber.PactStubber;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@Provider("mandateApi")
//@PactBroker(url = "https://validoc.pactflow.io")
@PactFolder("../../pacts")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MandatesApiProviderValidationTest {


    private static int providersPort = 8070;

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext provider) {
        provider.verifyInteraction();
    }

    @BeforeAll
    static void beforeAll() throws IOException {
        System.out.println("Starting pacts");
        PactStubber.startPacts(providersPort);
        System.out.println("Pacts started. Port: " + providersPort);
    }

    @AfterAll
    static void afterAll() throws InterruptedException {
        PactStubber.shutdownPacts(providersPort);
    }

    @Value("${server.port}")
    int serverPort;

    @Autowired
    MandateClient mandateClient;

    @BeforeEach
    void before(PactVerificationContext context) {
        System.out.println("Port: " + serverPort);
        context.setTarget(new HttpTestTarget("localhost", serverPort));
        mandateClient.mandateClientUrl = "http://localhost:" + providersPort;
    }

    @State({"test state"})
    public void toDefaultState() {
        //doesnt do anything as the state is already set up
    }

}
