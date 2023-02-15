package dev.srebootcamp.approvalOrchestrationApi.domain.payments;

import dev.srebootcamp.approvalOrchestrationApi.service.IdGenerator;

public class TestIdGenerator extends IdGenerator {
    int count = 0;

    public void reset() {
        count = 0;
    }

    @Override
    public String generate() {
        return "somePaymentId" + count++;
    }
}
