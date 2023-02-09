package dev.srebootcamp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srebootcamp.domain.UsernamePassword;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

    private static ObjectMapper mapper = new ObjectMapper();

    @PostMapping(value = "/authentication", produces = "application/json")
    public ResponseEntity validate(@RequestBody String body) throws Exception {
        UsernamePassword userNamePassword = mapper.readValue(body, UsernamePassword.class);
        if (userNamePassword.validate())
            return ResponseEntity.ok("\"User is valid\"");
        else return ResponseEntity.status(401).body("\"User is invalid\"");
    }
}
