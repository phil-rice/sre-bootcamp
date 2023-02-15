package dev.srebootcamp.approvalOrchestrationApi.domain.payments;

import dev.srebootcamp.approvalOrchestrationApi.domain.CustomerAndAccount;
import dev.srebootcamp.approvalOrchestrationApi.domain.Money;
import dev.srebootcamp.approvalOrchestrationApi.domain.Payer;

public record ApprovedPayment(String id, Payer payer, CustomerAndAccount payee, Money amount) implements IPayment {
}
