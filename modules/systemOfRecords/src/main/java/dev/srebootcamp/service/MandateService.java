package dev.srebootcamp.service;

import dev.srebootcamp.domain.Mandate;
import org.springframework.stereotype.Service;

@Service
public class MandateService {
    public Mandate getMandateFromCustomerId(String id) {
        return new Mandate(id + "_man", id, id + "_acc_id", "all");
    }
}
