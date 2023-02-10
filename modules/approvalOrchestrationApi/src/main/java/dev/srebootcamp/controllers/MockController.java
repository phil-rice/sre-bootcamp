package dev.srebootcamp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srebootcamp.clients.AuditClient;
import dev.srebootcamp.clients.CustomerClient;
import dev.srebootcamp.clients.MandateClient;
import dev.srebootcamp.domain.Mandate;
import dev.srebootcamp.domain.payments.*;
import dev.srebootcamp.service.IBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.Callable;

@RestController
public class MockController {

    @Autowired
    CustomerClient customerClient;
    @Autowired
    MandateClient mandateClient;
    @Autowired
    IBasket basket;

    @Autowired
    AuditClient audit;
    @Autowired
    ObjectMapper mapper;

    <P extends IPayment> ResponseEntity<P> result(Optional<P> result) {
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/payment", produces = "application/json")
    public NewPayment createPaymentEndpoint(@RequestBody NewPaymentRequest payment) throws Exception {
        NewPayment result = basket.newPayment(payment);
        audit.audit("Payment request: " + result.id() + " for " + result.amount() + " from " + result.payer().customerId() + " to " + result.payee().customerId());
        return result;
    }

    <T> T audit(String msg, Callable<T> action) throws Exception {
        try {
            T result = action.call();
            audit.audit(msg + " succeeded");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            audit.audit(msg + " failed");
            throw e;
        }
    }

    @PostMapping(value = "/payment/{id}/mandate", produces = "application/json")
    public ResponseEntity<IPayment> addMandateEndpoint(@PathVariable String id, @RequestBody Mandate mandate) throws Exception {
        return result(audit("Add mandate to " + id, () -> basket.map(id, PaymentMapper.onlyNewPayment("Can only add a mandate if there isn't one already", p -> p.asPayment(mandate)))));
    }


    @PostMapping(value = "/payment/{id}/approve", produces = "application/json")
    public ResponseEntity<IPayment> approvePaymentEndpoint(@PathVariable String id) throws Exception {
        return result(audit("approve " + id, () -> basket.map(id, PaymentMapper.onlyPayment("Can only approve a payment that has been given a mandate", payment -> {
            var mandate = mandateClient.getMandateForCustomer(payment.payer().customerId());
            var customer = customerClient.getCustomer(payment.payer().customerId());
            if (customer == null || customer.id() == null) throw new RuntimeException("Customer does not exist");
            if (customer.id().equals(payment.payer().customerId())) //really just checking the customer exists...
                if (mandate.mandateId().equals(payment.payer().mandateId()))
                    if (mandate.accountId().equals(payment.payer().accountId()))
                        return payment.approve();
            throw new RuntimeException("Mandate does not match payment");
        }))));
    }

    @PostMapping(value = "/payment/{id}/reject", produces = "application/json")
    public ResponseEntity<IPayment> rejectPaymentEndpoint(@PathVariable String id) throws Exception {
        return result(audit("reject" + "id", () -> basket.map(id, new PaymentMapper(Payment::reject, NewPayment::reject))));
    }

}
