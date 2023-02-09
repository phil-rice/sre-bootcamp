package dev.srebootcamp.domain;

public record UsernamePassword(String username, String password) {
    public boolean validate() {
        return username.equals(password);
    }
}
