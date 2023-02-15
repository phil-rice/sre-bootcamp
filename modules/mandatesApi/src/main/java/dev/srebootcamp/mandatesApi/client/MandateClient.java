package dev.srebootcamp.mandatesApi.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srebootcamp.mandatesApi.domain.Mandate;
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
    @Value("systemofrecords.mandate.client.url")
    public String mandateClientUrl;
    final private RestTemplate rest = new RestTemplate();
    final private HttpHeaders headers = new HttpHeaders();
    final private static ObjectMapper mapper = new ObjectMapper();

    public MandateClient() {
        this.headers.add("Content-Type", "application/json");
    }

    public List<Mandate> getMandateByCustomerId(String id) throws JsonProcessingException {
        HttpEntity<Mandate> requestEntity = new HttpEntity<>(headers);
        String url = mandateClientUrl + "/systemofrecords/mandate/" + id;
        ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String body = responseEntity.getBody();
        System.out.println("Recevied from" + url + ":" + body);
        List<Mandate> result = mapper.readValue(body, new TypeReference<List<Mandate>>() {
        });
        System.out.println("   which from" + url + " is " + result);
        return result;
    }


}
