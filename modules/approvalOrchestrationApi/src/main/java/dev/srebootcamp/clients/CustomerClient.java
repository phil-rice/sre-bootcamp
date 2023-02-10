package dev.srebootcamp.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.srebootcamp.domain.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerClient {
    @Value("customer.client.url")
    public String customerClientUrl;

    final private RestTemplate rest = new RestTemplate();
    final private HttpHeaders headers = new HttpHeaders();

    public CustomerClient() {
        this.headers.add("Content-Type", "application/json");
    }

    public Customer getCustomer(String id) throws JsonProcessingException {
        HttpEntity<Customer> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Customer> responseEntity = rest.exchange(customerClientUrl + "/customer/" + id, HttpMethod.GET, requestEntity, Customer.class);
        return responseEntity.getBody();
    }


}
