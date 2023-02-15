package dev.srebootcamp.gatewayApi.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NoGatewayException extends RuntimeException {
    public NoGatewayException(String message) {
        super(message);
    }

}
