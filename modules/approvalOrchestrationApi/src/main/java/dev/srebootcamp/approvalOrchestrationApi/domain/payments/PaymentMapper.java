package dev.srebootcamp.approvalOrchestrationApi.domain.payments;

import dev.srebootcamp.approvalOrchestrationApi.utils.FunctionWithError;

public record PaymentMapper(
        FunctionWithError<Payment, IPayment> mapPayment,
        FunctionWithError<NewPayment, IPayment> mapNewPayment

) {
    public IPayment map(IPayment payment) {
        try {
            if (payment instanceof Payment) return mapPayment.apply((Payment) payment);
            if (payment instanceof NewPayment) return mapNewPayment.apply((NewPayment) payment);
            throw new RuntimeException("Unknown payment type");
        } catch (Exception e) {
            if (e instanceof RuntimeException) throw (RuntimeException) e;
            throw new RuntimeException("Error mapping payment", e);
        }
    }

    public static <P extends IPayment> FunctionWithError<P, IPayment> error(String msg) {
        return p -> {
            throw new RuntimeException(msg);
        };
    }

    public static PaymentMapper onlyNewPayment(String errorMsg, FunctionWithError<NewPayment, IPayment> mapNewPayment) {
        return new PaymentMapper(error(errorMsg), mapNewPayment);
    }

    public static PaymentMapper onlyPayment(String errorMsg, FunctionWithError<Payment, IPayment> mapPayment) {
        return new PaymentMapper(mapPayment, error(errorMsg));
    }

}
