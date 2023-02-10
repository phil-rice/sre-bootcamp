package dev.srebootcamp.domain.payments;

import dev.srebootcamp.domain.CustomerAndAccount;
import dev.srebootcamp.domain.Money;
import dev.srebootcamp.domain.Payer;

public record Payment(String id, Payer payer, CustomerAndAccount payee,
                      Money amount) implements IPayment, IRejectablePayment {

    public ApprovedPayment approve() {
        return new ApprovedPayment(id, payer, payee, amount);
    }

    public RejectedPayment reject() {
        return new RejectedPayment(id, payer.customerAndAccount(), payee, amount);
    }

}