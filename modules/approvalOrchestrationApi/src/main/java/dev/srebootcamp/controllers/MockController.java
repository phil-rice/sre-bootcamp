package dev.srebootcamp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

  @PostMapping(value = "/approvalOrchestration", produces = "application/json")
  public String endPoint() throws Exception {
    return "Hello world";
  }
}
