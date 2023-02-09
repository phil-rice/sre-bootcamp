package dev.srebootcamp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srebootcamp.clients.AuthenticationClient;
import dev.srebootcamp.domain.UsernamePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

    @Autowired
    private AuthenticationClient authenticationClient;

    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity endPoint(@RequestBody UsernamePassword up) throws Exception {
        return authenticationClient.authenticate(up) ?
                ResponseEntity.ok("Login successful") :
                ResponseEntity.status(401).body("Login failed");
    }
}
