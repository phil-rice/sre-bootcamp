package dev.srebootcamp.fraudDetectionApi.domain;

public record DetectFraudData(String fromCustomerId, String toCustomerId, String amount) {
}
