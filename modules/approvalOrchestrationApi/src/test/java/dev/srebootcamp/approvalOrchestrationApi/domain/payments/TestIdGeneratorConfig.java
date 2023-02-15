package dev.srebootcamp.approvalOrchestrationApi.domain.payments;

import dev.srebootcamp.approvalOrchestrationApi.service.IdGenerator;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestIdGeneratorConfig {
    @Bean
    @Primary
    IdGenerator testIdGenerator(){return new TestIdGenerator();}
}