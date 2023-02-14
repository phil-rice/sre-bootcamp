package dev.srebootcamp.domain;

import dev.srebootcamp.domain.payments.Payment;

public record Mandate(String mandateId, String customerId, String accountId, String permissions) {
    public boolean matches(Payment payment) {
        return customerId.equals(payment.payer().customerId()) && accountId.equals(payment.payer().accountId());
    }
}
