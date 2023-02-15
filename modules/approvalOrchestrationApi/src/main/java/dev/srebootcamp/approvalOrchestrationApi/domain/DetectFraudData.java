package dev.srebootcamp.approvalOrchestrationApi.domain;

public record DetectFraudData(String fromCustomerId, String toCustomerId, String amount) {
}
