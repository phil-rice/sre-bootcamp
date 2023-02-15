package dev.srebootcamp.approvalOrchestrationApi.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.srebootcamp.approvalOrchestrationApi.domain.DetectFraudData;
import dev.srebootcamp.approvalOrchestrationApi.domain.DetectFraudResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FraudClient {
    @Value("fraud.client.url")
    public String fraudClientUrl;

    final private RestTemplate rest = new RestTemplate();
    final private HttpHeaders headers = new HttpHeaders();

    public FraudClient() {
        this.headers.add("Content-Type", "application/json");
    }

    public Boolean detectFraud(DetectFraudData data) throws JsonProcessingException {
        HttpEntity<DetectFraudData> requestEntity = new HttpEntity<>(data, headers);
        ResponseEntity<DetectFraudResponse> response = rest.exchange(fraudClientUrl + "/fraud", HttpMethod.POST, requestEntity, DetectFraudResponse.class);
        return response.getBody().fraud();
    }
}
