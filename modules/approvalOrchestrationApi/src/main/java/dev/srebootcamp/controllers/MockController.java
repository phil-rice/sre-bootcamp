package dev.srebootcamp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srebootcamp.clients.CustomerClient;
import dev.srebootcamp.clients.MandateClient;
import dev.srebootcamp.domain.Mandate;
import dev.srebootcamp.domain.payments.*;
import dev.srebootcamp.service.IBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MockController {

    @Autowired
    CustomerClient customer;
    @Autowired
    MandateClient mandate;
    @Autowired
    IBasket basket;

    @Autowired
    ObjectMapper mapper;
    <P extends IPayment> ResponseEntity<P> result(Optional<P> result) {
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/payment", produces = "application/json")
    public NewPayment createPaymentEndpoint(@RequestBody NewPaymentRequest payment) throws Exception {
        System.out.println("Payment request: " +payment);
        return basket.newPayment(payment);
    }

    @PostMapping(value = "/payment/{id}/mandate", produces = "application/json")
    public ResponseEntity<IPayment> addMandateEndpoint(@PathVariable String id, @RequestBody Mandate mandate) throws Exception {
        return result(basket.map(id, PaymentMapper.onlyNewPayment("Can only add a mandate if there isn't one already", p -> p.asPayment(mandate))));
    }


    @PostMapping(value = "/payment/{id}/approve", produces = "application/json")
    public ResponseEntity<IPayment> approvePaymentEndpoint(@PathVariable String id) throws Exception {
        return result(basket.map(id, PaymentMapper.onlyPayment("Can only approve a payment that has been given a mandate", Payment::approve)));
    }

    @PostMapping(value = "/payment/{id}/reject", produces = "application/json")
    public ResponseEntity<IPayment> rejectPaymentEndpoint(@PathVariable String id) {
        return result(basket.map(id, new PaymentMapper(Payment::reject, NewPayment::reject)));
    }

}
