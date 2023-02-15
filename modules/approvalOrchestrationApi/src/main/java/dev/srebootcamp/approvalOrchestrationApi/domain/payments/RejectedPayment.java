package dev.srebootcamp.approvalOrchestrationApi.domain.payments;

import dev.srebootcamp.approvalOrchestrationApi.domain.CustomerAndAccount;
import dev.srebootcamp.approvalOrchestrationApi.domain.Money;

public record RejectedPayment(String id, CustomerAndAccount payer, CustomerAndAccount payee, Money amount) implements IPayment {
}
