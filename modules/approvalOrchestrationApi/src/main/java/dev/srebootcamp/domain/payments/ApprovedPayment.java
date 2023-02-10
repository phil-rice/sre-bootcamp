package dev.srebootcamp.domain.payments;

import dev.srebootcamp.domain.CustomerAndAccount;
import dev.srebootcamp.domain.Money;
import dev.srebootcamp.domain.Payer;

public record ApprovedPayment(String id, Payer payer, CustomerAndAccount payee, Money amount) implements IPayment {
}
