package dev.srebootcamp.approvalOrchestrationApi.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.srebootcamp.approvalOrchestrationApi.domain.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuditClient {
    @Value("audit.client.url")
    public String auditClientUrl;

    final private RestTemplate rest = new RestTemplate();
    final private HttpHeaders headers = new HttpHeaders();

    public AuditClient() {
        this.headers.add("Content-Type", "application/json");
    }

    public void audit(String msg) throws JsonProcessingException {
        HttpEntity<String> requestEntity = new HttpEntity<>("{\"msg\":\"" + msg + "\"}", headers);
        rest.exchange(auditClientUrl + "/audit", HttpMethod.POST, requestEntity, Customer.class);
    }


}
