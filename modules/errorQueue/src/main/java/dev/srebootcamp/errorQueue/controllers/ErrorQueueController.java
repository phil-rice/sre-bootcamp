package dev.srebootcamp.errorQueue.controllers;

import dev.srebootcamp.errorQueue.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorQueueController {

  @Autowired
  ErrorService error;

  @PostMapping(value = "/errors", produces = "application/json")
  public void endPoint(@RequestBody String body) throws Exception {
    error.error(body);
  }
}
