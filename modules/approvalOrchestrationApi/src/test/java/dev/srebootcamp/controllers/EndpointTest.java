package dev.srebootcamp.controllers;

import dev.srebootcamp.domain.payments.TestIdGenerator;
import dev.srebootcamp.domain.payments.TestIdGeneratorConfig;
import dev.srebootcamp.service.Basket;
import dev.srebootcamp.service.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static dev.srebootcamp.domain.payments.PaymentsFixture.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@Import(TestIdGeneratorConfig.class)
public class EndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TestIdGenerator idGenerator;

    @Autowired
    Basket basket;

    @BeforeEach
    public void setup() {
        idGenerator.reset();
    }

    @Test
    public void test_payment_create() throws Exception {
        mockMvc.perform(post("/payment").content(newPaymentRequestJson).contentType("application/json; charset=utf-8"))
                .andExpect(content().string(newPaymentJson))
                .andExpect(status().isOk());
        assertEquals(Optional.of(newPayment), basket.getPayment(paymentId));
    }

    @Test
    public void test_payment_add_mandate() throws Exception {
        mockMvc.perform(post("/payment").content(newPaymentRequestJson).contentType("application/json; charset=utf-8"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/payment/" + paymentId + "/mandate").content(mandateJson).contentType("application/json; charset=utf-8"))
                .andExpect(content().string(paymentJson))
                .andExpect(status().isOk());
        assertEquals(Optional.of(payment), basket.getPayment(paymentId));
    }

    @Test
    public void test_payment_reject() throws Exception {
        mockMvc.perform(post("/payment").content(newPaymentRequestJson).contentType("application/json; charset=utf-8"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/payment/" + paymentId + "/reject").content(mandateJson).contentType("application/json; charset=utf-8"))
                .andExpect(content().string(rejectedJson))
                .andExpect(status().isOk());
        assertEquals(Optional.of(rejectedPayment), basket.getPayment(paymentId));


    }

    @Test
    public void test_payment_addMandate_reject() throws Exception {
        mockMvc.perform(post("/payment").content(newPaymentRequestJson).contentType("application/json; charset=utf-8"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/payment/" + paymentId + "/mandate").content(mandateJson).contentType("application/json; charset=utf-8"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/payment/" + paymentId + "/reject").content(mandateJson).contentType("application/json; charset=utf-8"))
                .andExpect(content().string(rejectedJson))
                .andExpect(status().isOk());
        assertEquals(Optional.of(rejectedPayment), basket.getPayment(paymentId));
    }

    @Test
    public void test_payment_addMandate_accept() throws Exception {
        mockMvc.perform(post("/payment").content(newPaymentRequestJson).contentType("application/json; charset=utf-8"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/payment/" + paymentId + "/mandate").content(mandateJson).contentType("application/json; charset=utf-8"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/payment/" + paymentId + "/approve").contentType("application/json; charset=utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(approvedJson));
        assertEquals(Optional.of(approvedPayment), basket.getPayment(paymentId));
    }
}