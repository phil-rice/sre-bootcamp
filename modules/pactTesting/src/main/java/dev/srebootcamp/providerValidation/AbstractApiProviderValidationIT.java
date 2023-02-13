package dev.srebootcamp.providerValidation;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import dev.srebootcamp.pactStubber.PactStubber;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;


abstract public class AbstractApiProviderValidationIT {

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext provider) {
        provider.verifyInteraction();
    }

    boolean pactStubberStarted = false;

    protected boolean isPactStubberStarted() {
        return pactStubberStarted;
    }

    @BeforeEach  // Why not @BeforeAll? Because we need to set the port before the stubber starts
    public void startPacts() throws IOException {
        if (isPactStubberStarted()) return;
        if (providersPort == 0) throw new RuntimeException("Providers port not set");
        System.out.println("Starting pacts from " + pactDirectory + " for " + providerName + " on port: " + providersPort);
        PactStubber.startPacts(pactDirectory, providerName, providersPort);
        System.out.println("Pacts started from " + pactDirectory + " for " + providerName + " on port: " + providersPort);
    }

    @AfterAll
    static void shutdownPacts() throws InterruptedException {
        if (testCount == 0) throw new RuntimeException("No validation tests run");
        if (providersPort == 0) throw new RuntimeException("Providers port not set");
        PactStubber.shutdownPacts(providersPort);
    }

    //Why static? Because we need to shutdown the stubber after all tests are run
    private static int testCount = 0;
    private static int providersPort = 0;


    public static int getProvidersPort() {
        return providersPort;
    }

    @Value("${server.providerport}")
    public void setProvidersPort(int providersPort) {
        AbstractApiProviderValidationIT.providersPort = providersPort;
        System.out.println("Setting providers port to " + providersPort);
    }

    private static String providerName = null;

    @Value("${server.providername}")
    public void setProviderName(String providerName) {
        AbstractApiProviderValidationIT.providerName = providerName;
        System.out.println("Setting provider name to " + providerName);
    }

    private static String pactDirectory = null;

    @Value("${server.pactdirectory}")
    public void setPactDirectory(String pactDirectory) {
        AbstractApiProviderValidationIT.pactDirectory = pactDirectory;
        System.out.println("Setting pact directory to " + pactDirectory);
    }

    @Value("${server.port}")
    int serverPort;

    @BeforeEach
    public void setUpTarget(PactVerificationContext context) {
        System.out.println("Target Port: " + serverPort);
        context.setTarget(new HttpTestTarget("localhost", serverPort));
        testCount++;
    }

    @State({"test state"})
    public void toDefaultState() {
        //doesnt do anything as the state is already set up
    }

}
