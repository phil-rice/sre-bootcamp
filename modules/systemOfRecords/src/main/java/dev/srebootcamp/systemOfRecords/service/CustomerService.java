package dev.srebootcamp.systemOfRecords.service;

import dev.srebootcamp.systemOfRecords.domain.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    public Customer getCustomer(String id) {
        return new Customer(id, "Customer " + id);
    }
}
