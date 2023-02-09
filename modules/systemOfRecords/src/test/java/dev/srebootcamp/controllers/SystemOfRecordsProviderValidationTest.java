package dev.srebootcamp.controllers;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.MockMvcTestTarget;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@Provider("systemofrecords")
@PactFolder("../../pacts")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SystemOfRecordsProviderValidationTest {

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext provider) {
        provider.verifyInteraction();
    }

    @Value("${server.port}")
    int systemOfRecordsPort;

    @BeforeEach
    void before(PactVerificationContext context) {
        System.out.println("System of records port: " + systemOfRecordsPort);
        context.setTarget(new HttpTestTarget("localhost", systemOfRecordsPort));
    }

    @State({"test state"})
    public void toDefaultState() {
        //doesnt do anything as the state is already set up
    }

}
