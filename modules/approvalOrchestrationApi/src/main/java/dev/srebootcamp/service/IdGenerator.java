package dev.srebootcamp.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGenerator {
    public String generate() {
        return UUID.randomUUID().toString();
    }

}
