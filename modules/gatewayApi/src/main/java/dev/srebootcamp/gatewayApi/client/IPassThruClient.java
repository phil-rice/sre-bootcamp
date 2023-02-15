package dev.srebootcamp.gatewayApi.client;

import dev.srebootcamp.gatewayApi.domain.Request;
import org.springframework.http.ResponseEntity;

public interface IPassThruClient {
    ResponseEntity<String> passThru(String baseUrl, Request request);
}
