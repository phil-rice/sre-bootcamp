package dev.srebootcamp.controllers;

import dev.srebootcamp.domain.DetectFraudData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

    /**
     * Why a post mapping, when this is rest and it is clearly a get? Answer: because browsers cannot send a body in a get request.
     */
    @PostMapping(value = "/fraud", produces = "application/json;charset=utf-8")
    public String endPoint(@RequestBody DetectFraudData data) {
        return "{\"fraud\":false}";
    }
}
