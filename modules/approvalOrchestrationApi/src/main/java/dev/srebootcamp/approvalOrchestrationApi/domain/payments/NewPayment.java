package dev.srebootcamp.approvalOrchestrationApi.domain.payments;

import dev.srebootcamp.approvalOrchestrationApi.domain.CustomerAndAccount;
import dev.srebootcamp.approvalOrchestrationApi.domain.Mandate;
import dev.srebootcamp.approvalOrchestrationApi.domain.Money;
import dev.srebootcamp.approvalOrchestrationApi.domain.Payer;

public record NewPayment(String id, CustomerAndAccount payer, CustomerAndAccount payee,
                         Money amount) implements IPayment, IRejectablePayment {
    public Payment asPayment(Mandate mandate) {
        if (!mandate.accountId().equals(payer.accountId()))
            throw new IllegalArgumentException("Mandate is for a different account");
        if (!mandate.customerId().equals(payer.customerId()))
            throw new IllegalArgumentException("Mandate is for a different customer");
        return new Payment(id, new Payer(payer.customerId(), payer.accountId(), mandate.mandateId(), mandate.permissions()), payee, amount);
    }

    public RejectedPayment reject() {
        return new RejectedPayment(id, payer, payee, amount);
    }
}
