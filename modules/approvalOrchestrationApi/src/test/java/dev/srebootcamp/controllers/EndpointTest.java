package dev.srebootcamp.controllers;

import au.com.dius.pact.consumer.*;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.ForProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.model.MockProviderConfig;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import dev.srebootcamp.clients.AuditClient;
import dev.srebootcamp.clients.CustomerClient;
import dev.srebootcamp.clients.MandateClient;
import dev.srebootcamp.domain.payments.TestIdGenerator;
import dev.srebootcamp.domain.payments.TestIdGeneratorConfig;
import dev.srebootcamp.service.Basket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;

import static au.com.dius.pact.consumer.ConsumerPactRunnerKt.runConsumerTest;
import static dev.srebootcamp.domain.payments.PaymentsFixture.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@Import(TestIdGeneratorConfig.class)
@PactDirectory("../../pacts")
@ExtendWith(PactConsumerTestExt.class)
public class EndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TestIdGenerator idGenerator;

    @Autowired
    AuditClient auditClient;

    @Autowired
    MandateClient mandateClient;

    @Autowired
    CustomerClient customerClient;

    RequestResponsePact createAuditPact(PactDslWithProvider builder, int status, String sentBody) {
        return builder
                .given("test state")
                .uponReceiving(sentBody)
                .path("/audit")
                .method("POST")
                .body("{\"msg\":\"" + sentBody + "\"}")
                .willRespondWith()
                .status(status)
                .toPact();
    }

    void runPact(RequestResponsePact pact, Callable<Void> test) throws Exception {
        MockProviderConfig config = MockProviderConfig.createDefault();
        PactVerificationResult result = runConsumerTest(pact, config, new PactTestRun() {
            @Override
            public Object run(@NotNull MockServer mockServer, @Nullable PactTestExecutionContext pactTestExecutionContext) throws Throwable {
                auditClient.auditClientUrl = mockServer.getUrl();
                test.call();
                return null;
            }
        });
        if (result instanceof PactVerificationResult.Error) {
            throw new RuntimeException(((PactVerificationResult.Error) result).getError());
        }

        assertEquals(new PactVerificationResult.Ok(), result);
    }


    @Autowired
    Basket basket;

    @BeforeEach
    public void setup(@ForProvider("auditApi") MockServer auditServer,
                      @ForProvider("mandateApi") MockServer mandateServer,
                      @ForProvider("customerApi") MockServer customerServer) {
        idGenerator.reset();
        auditClient.auditClientUrl = auditServer.getUrl();
        mandateClient.mandateClientUrl = mandateServer.getUrl();
        customerClient.customerClientUrl = customerServer.getUrl();
    }

    @Pact(consumer = "approvalOrchestrationApi", provider = "auditApi")
    public RequestResponsePact pactForPaymentCreate(PactDslWithProvider builder) {
        return createAuditPact(builder, 200, "Payment request: somePaymentId0 for Money[amount=100.0] from fromCustomerId to toCustomerId");
    }

    @Test
    @PactTestFor(pactMethod = "pactForPaymentCreate")
    @ExtendWith(PactVerificationSpringProvider.class)
    public void test_payment_create(MockServer server) throws Exception {
        auditClient.auditClientUrl = server.getUrl();
        mockMvc.perform(post("/payment").content(newPaymentRequestJson).contentType("application/json; charset=utf-8"))
                .andExpect(content().string(newPaymentJson))
                .andExpect(status().isOk());
        assertEquals(Optional.of(newPayment), basket.getPayment(paymentId));
    }

    @Pact(consumer = "approvalOrchestrationApi", provider = "auditApi")
    public RequestResponsePact pactForPaymentAddMandate(PactDslWithProvider builder) {
        return createAuditPact(builder, 200, "Add mandate to somePaymentId0 succeeded");
    }

    @Test
    @PactTestFor(pactMethod = "pactForPaymentAddMandate")
    public void test_payment_add_mandate() throws Exception {
        basket.putForTests(paymentId, newPayment);
        mockMvc.perform(post("/payment/" + paymentId + "/mandate")
                        .content(mandateJson).contentType("application/json; charset=utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(paymentJson));
        assertEquals(Optional.of(payment), basket.getPayment(paymentId));
    }

    @Pact(consumer = "approvalOrchestrationApi", provider = "auditApi")
    public RequestResponsePact pactForPaymentReject(PactDslWithProvider builder) {
        return createAuditPact(builder, 200, "rejectid succeeded");
    }

    @Test
    @PactTestFor(pactMethod = "pactForPaymentReject")
    public void test_newPayment_reject() throws Exception {
        basket.putForTests(paymentId, newPayment);
        mockMvc.perform(post("/payment/" + paymentId + "/reject").content(mandateJson).contentType("application/json; charset=utf-8"))
                .andExpect(content().string(rejectedJson))
                .andExpect(status().isOk());
        assertEquals(Optional.of(rejectedPayment), basket.getPayment(paymentId));
    }


    @Test
    @PactTestFor(pactMethod = "pactForPaymentReject")
    public void test_payment__reject() throws Exception {
        basket.putForTests(paymentId, payment);
        mockMvc.perform(post("/payment/" + paymentId + "/reject").content(mandateJson).contentType("application/json; charset=utf-8"))
                .andExpect(content().string(rejectedJson))
                .andExpect(status().isOk());
        assertEquals(Optional.of(rejectedPayment), basket.getPayment(paymentId));
    }

    @Pact(consumer = "approvalOrchestrationApi", provider = "auditApi")
    public RequestResponsePact pactForPaymentAccept(PactDslWithProvider builder) {
        return createAuditPact(builder, 200, "approve somePaymentId0 succeeded");
    }

    @Pact(consumer = "approvalOrchestrationApi", provider = "mandateApi")
    public RequestResponsePact mandatePactForPaymentAccept(PactDslWithProvider builder) {
        return builder
                .given("test state")
                .uponReceiving("mandate request")
                .path("/mandates").query("customer_id=fromCustomerId")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(mandateJson)
                .headers(Map.of("Content-Type", "application/json; charset=utf-8"))
                .toPact();
    }

    @Pact(consumer = "approvalOrchestrationApi", provider = "customerApi")
    public RequestResponsePact customerPactForPaymentAccept(PactDslWithProvider builder) {
        return builder
                .given("test state")
                .uponReceiving("customer request")
                .path("/customer/" + payment.payer().customerId())
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(fromCustomerJson)
                .headers(Map.of("Content-Type", "application/json; charset=utf-8"))
                .toPact();
    }

    @PactTestFor(providerName = "approvalOrchestrationApi",
            pactMethods = {"pactForPaymentAccept", "mandatePactForPaymentAccept", "customerPactForPaymentAccept"})
    @Test
    public void test_payment_payment_accept() throws Exception {
        basket.putForTests(paymentId, payment);
        mockMvc.perform(post("/payment/" + paymentId + "/approve").contentType("application/json; charset=utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(approvedJson));
        assertEquals(Optional.of(approvedPayment), basket.getPayment(paymentId));
    }
}
