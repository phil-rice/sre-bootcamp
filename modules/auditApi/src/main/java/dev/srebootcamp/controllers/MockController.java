package dev.srebootcamp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

    @PostMapping(value = "/audit", produces = "application/json")
    public void auditEndpoint(@RequestBody String logMessage) {
        System.out.println(logMessage);
    }
}
