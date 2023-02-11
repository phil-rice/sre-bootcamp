package dev.srebootcamp.clients;

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
    public String mandateClientUrl;

    final private RestTemplate rest = new RestTemplate();
    final private HttpHeaders headers = new HttpHeaders();

    public MandateClient() {
        this.headers.add("Content-Type", "application/json");
    }

    public Mandate getMandateForCustomer(String customerId) {
        HttpEntity<Mandate> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Mandate> responseEntity = rest.exchange(mandateClientUrl + "/mandates?customer_id=" +customerId, HttpMethod.GET, requestEntity, Mandate.class);
        return responseEntity.getBody();
    }


}
