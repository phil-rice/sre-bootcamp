package dev.srebootcamp.service;

import dev.srebootcamp.domain.payments.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class Basket implements IBasket {

    public Basket(@Autowired IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public void putForTests(String id, IPayment payment) {
        database.put(id, payment);
    }

    IdGenerator idGenerator;

    private Map<String, IPayment> database = Collections.synchronizedMap(new HashMap<>());

    @Override
    public Optional<IPayment> getPayment(String id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public NewPayment newPayment(NewPaymentRequest payment) {
        var id = idGenerator.generate();
        var result = payment.asNewPayment(id);
        database.put(id, result);
        return result;
    }

    @Override
    public Optional<IPayment> map(String id, PaymentMapper paymentMapper) {
        Optional<IPayment> initialPayment = getPayment(id);
        Optional<IPayment> result = initialPayment.map(paymentMapper::map);
        result.ifPresent(payment -> database.put(id, payment));
        return result;
    }
}
