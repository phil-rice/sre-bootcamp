package dev.srebootcamp.approvalOrchestrationApi.service;

import dev.srebootcamp.approvalOrchestrationApi.domain.payments.IPayment;
import dev.srebootcamp.approvalOrchestrationApi.domain.payments.NewPayment;
import dev.srebootcamp.approvalOrchestrationApi.domain.payments.NewPaymentRequest;
import dev.srebootcamp.approvalOrchestrationApi.domain.payments.PaymentMapper;

import java.util.Optional;

public interface IBasket {

    NewPayment newPayment(NewPaymentRequest payment);

    Optional<IPayment> getPayment(String id);

    Optional<IPayment> map(String id, PaymentMapper paymentMapper);


}
