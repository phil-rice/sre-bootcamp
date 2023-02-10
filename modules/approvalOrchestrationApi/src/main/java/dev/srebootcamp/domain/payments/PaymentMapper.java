package dev.srebootcamp.domain.payments;

import java.util.function.Function;

public record PaymentMapper(
        Function<Payment, IPayment> mapPayment,
        Function<NewPayment, IPayment> mapNewPayment

) {
    public IPayment map(IPayment payment) {
        if (payment instanceof Payment) return mapPayment.apply((Payment) payment);
        if (payment instanceof NewPayment) return mapNewPayment.apply((NewPayment) payment);
        throw new RuntimeException("Unknown payment type");
    }

    public static <P extends IPayment> Function<P, IPayment> error(String msg) {
        return p -> {
            throw new RuntimeException(msg);
        };
    }

    public static PaymentMapper onlyNewPayment(String errorMsg, Function<NewPayment, IPayment> mapNewPayment) {
        return new PaymentMapper(error(errorMsg), mapNewPayment);
    }

    public static PaymentMapper onlyPayment(String errorMsg, Function<Payment, IPayment> mapPayment) {
        return new PaymentMapper(mapPayment, error(errorMsg));
    }

}
