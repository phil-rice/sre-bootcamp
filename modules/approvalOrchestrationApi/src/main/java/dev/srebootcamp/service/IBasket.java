package dev.srebootcamp.service;

import dev.srebootcamp.domain.payments.*;

import java.util.Optional;

public interface IBasket {

    NewPayment newPayment(NewPaymentRequest payment);

    Optional<IPayment> getPayment(String id);

    Optional<IPayment> map(String id, PaymentMapper paymentMapper);


}
