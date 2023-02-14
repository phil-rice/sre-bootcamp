package dev.srebootcamp.client;

import dev.srebootcamp.Request;
import org.springframework.http.ResponseEntity;

public interface IPassThruClient {
    ResponseEntity<String> passThru(String baseUrl, Request request);
}
