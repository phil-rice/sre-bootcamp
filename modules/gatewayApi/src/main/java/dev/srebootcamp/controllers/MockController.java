package dev.srebootcamp.controllers;

import dev.srebootcamp.Request;
import dev.srebootcamp.client.GatewayException;
import dev.srebootcamp.client.IPassThruClient;
import dev.srebootcamp.client.PassThruClient;
import dev.srebootcamp.domain.IEndpointToUrlMapping;
import dev.srebootcamp.domain.NoGatewayException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RestController
public class MockController {

    final IPassThruClient client;
    @Autowired
    IEndpointToUrlMapping endpointToUrlMapping;

    MockController(@Autowired IPassThruClient client) {
        this.client = client;
    }

    @RequestMapping(value = "/**")
    public ResponseEntity<String> endPoint(HttpServletRequest req) throws Exception {
        Request request = Request.from(req);
        String baseUrl = endpointToUrlMapping.getBaseUrlFor(req.getRequestURI());
        return client.passThru(baseUrl, request);
    }

    @ExceptionHandler(NoGatewayException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoGateway(NoGatewayException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(GatewayException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleGateway(GatewayException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }


}
