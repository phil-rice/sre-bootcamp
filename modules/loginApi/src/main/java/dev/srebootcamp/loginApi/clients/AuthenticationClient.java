package dev.srebootcamp.loginApi.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srebootcamp.loginApi.domain.UsernamePassword;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@PropertySource("classpath:clients.properties")
@Service
public class AuthenticationClient {
    @Value("authentication.client.url")
    public String authenticationClientUrl;

    final private RestTemplate rest = new RestTemplate();
    final private HttpHeaders headers = new HttpHeaders();
    final private static ObjectMapper mapper = new ObjectMapper();

    public AuthenticationClient() {
        this.headers.add("Content-Type", "application/json");
    }

    public Boolean authenticate(UsernamePassword up) throws JsonProcessingException {
        String body = mapper.writeValueAsString(up);
        System.out.println("Sending request to " + authenticationClientUrl + " " + body);
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        try{
            ResponseEntity<String> responseEntity = rest.exchange(authenticationClientUrl+ "/authentication", HttpMethod.POST, requestEntity, String.class);
            return true;
        }catch (HttpClientErrorException e){
            return false;
        }
    }
}
