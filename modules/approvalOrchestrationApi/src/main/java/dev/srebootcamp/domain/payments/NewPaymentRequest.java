package dev.srebootcamp.domain.payments;

import dev.srebootcamp.domain.CustomerAndAccount;
import dev.srebootcamp.domain.Money;

public record NewPaymentRequest(CustomerAndAccount payer, CustomerAndAccount payee, Money amount) {
    public NewPayment asNewPayment(String id) {
        return new NewPayment(id, payer, payee, amount);
    }

}
