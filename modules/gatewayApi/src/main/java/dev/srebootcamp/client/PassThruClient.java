package dev.srebootcamp.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.srebootcamp.Request;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

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
