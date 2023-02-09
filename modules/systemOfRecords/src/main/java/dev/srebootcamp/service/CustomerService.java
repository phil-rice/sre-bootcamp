package dev.srebootcamp.service;

import dev.srebootcamp.domain.Customer;
import dev.srebootcamp.domain.Mandate;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    public Customer getCustomer(String id) {
        return new Customer(id, "Customer " + id);
    }
}
