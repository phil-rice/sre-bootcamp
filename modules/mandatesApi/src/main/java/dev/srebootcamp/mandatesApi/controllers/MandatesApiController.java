package dev.srebootcamp.mandatesApi.controllers;

import dev.srebootcamp.mandatesApi.client.MandateClient;
import dev.srebootcamp.mandatesApi.domain.Mandate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MandatesApiController {
  @Autowired
  MandateClient mandateClient;

  @GetMapping(value = "/mandates", produces = "application/json;charset=utf-8")
  public List<Mandate> endPoint(@RequestParam String customer_id) throws Exception {
    return mandateClient.getMandateByCustomerId(customer_id);
  }
}
