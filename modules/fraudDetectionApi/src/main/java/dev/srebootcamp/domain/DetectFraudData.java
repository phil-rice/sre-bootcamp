package dev.srebootcamp.domain;

public record DetectFraudData(String fromCustomerId, String toCustomerId, String amount) {
}
