package dev.srebootcamp.domain.payments;

import dev.srebootcamp.domain.CustomerAndAccount;
import dev.srebootcamp.domain.Mandate;
import dev.srebootcamp.domain.Money;
import dev.srebootcamp.domain.Payer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static dev.srebootcamp.domain.payments.PaymentsFixture.*;

public class PaymentsTest {


    @Test
    public void testNewPaymentRequestToNewPayment() {
        assertEquals(newPayment, newPaymentRequest.asNewPayment(paymentId));
    }

    @Test
    public void testNewPaymentToPayment() {
        assertEquals(payment, newPayment.asPayment(mandate));
    }

    @Test
    public void testPaymentToApproved() {
        assertEquals(approvedPayment, payment.approve());
    }

    @Test
    public void testPaymentToRejected() {
        assertEquals(rejectedPayment, payment.reject());
    }

}
