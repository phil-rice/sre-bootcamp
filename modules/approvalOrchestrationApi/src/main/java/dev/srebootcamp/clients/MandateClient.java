package dev.srebootcamp.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.srebootcamp.domain.Customer;
import dev.srebootcamp.domain.Mandate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MandateClient {
    @Value("mandate.client.url")
    public String customerClientUrl;

    final private RestTemplate rest = new RestTemplate();
    final private HttpHeaders headers = new HttpHeaders();

    public MandateClient() {
        this.headers.add("Content-Type", "application/json");
    }

    public Mandate getMandateForCustomer(String customerId) throws JsonProcessingException {
        HttpEntity<Mandate> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Mandate> responseEntity = rest.exchange(customerClientUrl + "/mandate?customer_id=" +customerId, HttpMethod.GET, requestEntity, Mandate.class);
        return responseEntity.getBody();
    }


}
