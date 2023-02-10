package dev.srebootcamp.domain;

public record Payer(String customerId, String accountId, String mandateId, String mandatePermissions){
    public CustomerAndAccount customerAndAccount() {
        return new CustomerAndAccount(customerId, accountId);
    }
}
