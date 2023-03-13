package dev.srebootcamp.entity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntityController {

  @GetMapping(value = "", produces = "application/json")
  public String endPoint() throws Exception {
    return "Hello world";
  }
}
