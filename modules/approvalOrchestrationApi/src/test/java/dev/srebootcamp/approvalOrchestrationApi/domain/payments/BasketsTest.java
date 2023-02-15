package dev.srebootcamp.approvalOrchestrationApi.domain.payments;

import dev.srebootcamp.approvalOrchestrationApi.service.Basket;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static dev.srebootcamp.approvalOrchestrationApi.domain.payments.PaymentsFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class BasketsTest {

    @Test
    public void testCanAddPaymentToBasketAndGetItAgain() {
        Basket basket = new Basket(idGenerator());
        NewPayment payment1 = basket.newPayment(newPaymentRequest);
        NewPayment payment2 = basket.newPayment(newPaymentRequest);
        assertNotEquals(payment, payment1);
        assertEquals("somePaymentId1", payment2.id());

        assertEquals(Optional.of(payment1), basket.getPayment(payment1.id()));
        assertEquals(Optional.of(payment2), basket.getPayment(payment2.id()));
        assertEquals(Optional.empty(), basket.getPayment("otherId"));
    }

    @Test
    public void testCanMapNewPayments() {
        Basket basket = new Basket(idGenerator());
        NewPayment payment1 = basket.newPayment(newPaymentRequest);
        var rejected = basket.map(payment1.id(), PaymentMapper.onlyNewPayment("error", NewPayment::reject));
        assertEquals(Optional.of(rejectedPayment), rejected);
        assertEquals(Optional.of(rejectedPayment), basket.getPayment(payment1.id()));
    }

    @Test
    public void testCanMapPayments() {
        Basket basket = new Basket(idGenerator());
        NewPayment payment1 = basket.newPayment(newPaymentRequest);
        basket.map(payment1.id(), PaymentMapper.onlyNewPayment("error", p -> p.asPayment(mandate)));
        var approved = basket.map(payment1.id(), PaymentMapper.onlyPayment("error", Payment::approve));
        assertEquals(Optional.of(approvedPayment), approved);
        assertEquals(Optional.of(approvedPayment), basket.getPayment(payment1.id()));
    }

}
