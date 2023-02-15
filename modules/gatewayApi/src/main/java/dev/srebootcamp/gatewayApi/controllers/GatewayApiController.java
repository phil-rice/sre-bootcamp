package dev.srebootcamp.gatewayApi.controllers;

import dev.srebootcamp.gatewayApi.domain.Request;
import dev.srebootcamp.gatewayApi.client.GatewayException;
import dev.srebootcamp.gatewayApi.client.IPassThruClient;
import dev.srebootcamp.gatewayApi.domain.IEndpointToUrlMapping;
import dev.srebootcamp.gatewayApi.domain.NoGatewayException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GatewayApiController {


  final IPassThruClient client;
  @Autowired
  IEndpointToUrlMapping endpointToUrlMapping;

  GatewayApiController(@Autowired IPassThruClient client) {
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
