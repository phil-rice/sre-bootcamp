package dev.srebootcamp.approvalOrchestrationApi.domain.payments;

import dev.srebootcamp.approvalOrchestrationApi.domain.CustomerAndAccount;
import dev.srebootcamp.approvalOrchestrationApi.domain.Money;
import dev.srebootcamp.approvalOrchestrationApi.domain.Payer;

public record Payment(String id, Payer payer, CustomerAndAccount payee,
                      Money amount) implements IPayment, IRejectablePayment {

    public ApprovedPayment approve() {
        return new ApprovedPayment(id, payer, payee, amount);
    }

    public RejectedPayment reject() {
        return new RejectedPayment(id, payer.customerAndAccount(), payee, amount);
    }

}