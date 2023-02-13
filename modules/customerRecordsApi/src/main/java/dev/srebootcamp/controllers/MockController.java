package dev.srebootcamp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srebootcamp.clients.CustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

    @Autowired
    CustomerClient customerClient;
    @Autowired
    ObjectMapper mapper;

    @GetMapping(value = "/customer/{id}", produces = "application/json;charset=utf-8")
    public String endPoint(@PathVariable String id) throws Exception {
        return mapper.writeValueAsString(customerClient.getCustomer(id));
    }
}
