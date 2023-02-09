package dev.srebootcamp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

  @GetMapping(value = "/", produces = "application/json")
  public String endPoint() throws Exception {
    return "Hello world";
  }
}
