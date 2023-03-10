package dev.srebootcamp.location.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

abstract public class GenericGetClient<T> {
    public String clientUrl;
    public final Class<? super T> clazz;
    final private RestTemplate rest = new RestTemplate();
    final private HttpHeaders headers = new HttpHeaders();

    public GenericGetClient(String clientUrl, Class<? super T> clazz) {
        this.clientUrl = clientUrl;
        this.clazz = clazz;
        this.headers.add("Content-Type", "application/json");
    }

    public T call()  {
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        return rest.<T>exchange(clientUrl, HttpMethod.GET, requestEntity, (Class<T>) clazz).getBody();
    }

}
