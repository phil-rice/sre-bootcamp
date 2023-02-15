package dev.srebootcamp.gatewayApi.domain;

import dev.srebootcamp.gatewayApi.client.IPassThruClient;
import dev.srebootcamp.gatewayApi.domain.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MockPassThruClient implements IPassThruClient{

        @Override
        public ResponseEntity<String> passThru(String baseUrl, Request request) {
            return ResponseEntity.ok(baseUrl + "/" + request);

        }

}