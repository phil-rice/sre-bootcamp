package dev.srebootcamp.approvalOrchestrationApi.domain.payments;

import dev.srebootcamp.approvalOrchestrationApi.domain.CustomerAndAccount;
import dev.srebootcamp.approvalOrchestrationApi.domain.Money;

public record NewPaymentRequest(CustomerAndAccount payer, CustomerAndAccount payee, Money amount) {
    public NewPayment asNewPayment(String id) {
        return new NewPayment(id, payer, payee, amount);
    }

}
