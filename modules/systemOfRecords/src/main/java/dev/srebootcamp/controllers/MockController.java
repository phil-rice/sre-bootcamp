package dev.srebootcamp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srebootcamp.domain.Customer;
import dev.srebootcamp.domain.Mandate;
import dev.srebootcamp.service.CustomerService;
import dev.srebootcamp.service.MandateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MockController {

    @Autowired
    CustomerService customerService;
    @Autowired
    MandateService mandateService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(value = "/systemofrecords/customer/{id}", produces = "application/json")
    public Customer customerEndPoint(@PathVariable String id) throws Exception {
        return customerService.getCustomer(id);
    }

    @GetMapping(value = "/systemofrecords/mandate/{id}", produces = "application/json")
    public List<Mandate> mandateEndPoint(@PathVariable String id) throws Exception {
        return mandateService.getMandatesFromCustomerId(id);
    }
}
