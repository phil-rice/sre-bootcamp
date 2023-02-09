package dev.srebootcamp.service;

import dev.srebootcamp.domain.Mandate;
import org.springframework.stereotype.Service;

@Service
public class MandateService {
    public Mandate getMandate(String id) {
        return new Mandate(id, "10" + id, "100" + id, "all");
    }
}
