package dev.srebootcamp.domain.payments;

import dev.srebootcamp.domain.CustomerAndAccount;
import dev.srebootcamp.domain.Money;

public record RejectedPayment(String id, CustomerAndAccount payer, CustomerAndAccount payee, Money amount) implements IPayment {
}
