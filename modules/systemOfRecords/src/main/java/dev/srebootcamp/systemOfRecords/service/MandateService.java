package dev.srebootcamp.systemOfRecords.service;

import dev.srebootcamp.systemOfRecords.domain.Mandate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MandateService {
    public List<Mandate> getMandatesFromCustomerId(String id) {
        return Arrays.asList(new Mandate(id + "_man", id, id + "_acc_id", "all"));
    }
}
