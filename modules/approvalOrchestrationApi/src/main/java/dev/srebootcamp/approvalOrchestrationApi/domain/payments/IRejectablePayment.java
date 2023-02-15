package dev.srebootcamp.approvalOrchestrationApi.domain.payments;

public interface IRejectablePayment {
    RejectedPayment reject();
}
