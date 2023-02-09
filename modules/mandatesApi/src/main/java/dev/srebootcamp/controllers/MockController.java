package dev.srebootcamp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srebootcamp.client.MandateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

    @Autowired
    MandateClient mandateClient;
    @Autowired
    ObjectMapper mapper;

    @GetMapping(value = "/mandates", produces = "application/json")
    public String endPoint(@RequestParam String customer_id) throws Exception {
        return mapper.writeValueAsString(mandateClient.getMandateByCustomerId(customer_id));
    }
}
