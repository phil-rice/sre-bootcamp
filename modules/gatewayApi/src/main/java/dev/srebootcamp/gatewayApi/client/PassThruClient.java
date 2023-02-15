package dev.srebootcamp.gatewayApi.client;

import dev.srebootcamp.gatewayApi.domain.Request;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PassThruClient implements IPassThruClient {


    final private RestTemplate rest = new RestTemplate();

    public ResponseEntity<String> passThru(String baseUrl, Request request) {
        String url = baseUrl + request.url();
        try {
            return rest.exchange(url,
                    HttpMethod.valueOf(request.method()),
                    new HttpEntity<String>(request.body(), request.headers()), String.class);
        } catch (Exception e) {
            throw new GatewayException( url, e);
        }
    }
}
