package dev.srebootcamp.domain.payments;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srebootcamp.domain.*;
import dev.srebootcamp.service.IdGenerator;

public interface PaymentsFixture {
    CustomerAndAccount mrFrom = new CustomerAndAccount("fromCustomerId", "fromAccountId");

    Customer fromCustomer = new Customer(mrFrom.customerId(), "fromCustomerName");
    CustomerAndAccount mrsTo = new CustomerAndAccount("toCustomerId", "toAccountId");
    Money amount = new Money(100);

    String paymentId = "somePaymentId0";
    Mandate mandate = new Mandate("someMandateId", mrFrom.customerId(), mrFrom.accountId(), "somePermissions");
    Payer mrFromAsFullPayer = new Payer(mrFrom.customerId(), mrFrom.accountId(), mandate.mandateId(), mandate.permissions());
    NewPaymentRequest newPaymentRequest = new NewPaymentRequest(mrFrom, mrsTo, amount);
    NewPayment newPayment = new NewPayment(paymentId, mrFrom, mrsTo, amount);
    Payment payment = new Payment(paymentId, mrFromAsFullPayer, mrsTo, amount);
    ApprovedPayment approvedPayment = new ApprovedPayment(paymentId, mrFromAsFullPayer, mrsTo, amount);
    RejectedPayment rejectedPayment = new RejectedPayment(paymentId, mrFrom, mrsTo, amount);

    static IdGenerator idGenerator() {
        return new TestIdGenerator();
    }

    ObjectMapper objectMapper = new ObjectMapper();

    static String json(Object t) {
        try {
            return objectMapper.writeValueAsString(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    String newPaymentRequestJson = json(newPaymentRequest);
    String newPaymentJson = json(newPayment);
    String paymentJson = json(payment);
    String rejectedJson = json(rejectedPayment);
    String approvedJson = json(approvedPayment);

    String mandateJson = json(mandate);
    String fromCustomerJson = json(fromCustomer);

}
