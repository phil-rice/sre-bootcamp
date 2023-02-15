package dev.srebootcamp.approvalOrchestrationApi.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srebootcamp.approvalOrchestrationApi.domain.Mandate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MandateClient {
    @Value("mandate.client.url")
    public String mandateClientUrl;

    final private RestTemplate rest = new RestTemplate();
    final private HttpHeaders headers = new HttpHeaders();

    public MandateClient() {
        this.headers.add("Content-Type", "application/json");
    }

    @Autowired
    ObjectMapper mapper;

    public List<Mandate> getMandateForCustomer(String customerId) throws JsonProcessingException {
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = rest.exchange(mandateClientUrl + "/mandates?customer_id=" + customerId,
                HttpMethod.GET,
                requestEntity, String.class);
        return mapper.readValue(responseEntity.getBody(), new TypeReference<List<Mandate>>() {
        });
    }


}
