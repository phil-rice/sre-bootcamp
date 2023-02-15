package dev.srebootcamp.loginApi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srebootcamp.loginApi.clients.AuthenticationClient;
import dev.srebootcamp.loginApi.domain.UsernamePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginApiController {

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
