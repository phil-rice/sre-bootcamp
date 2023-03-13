package dev.srebootcamp.locationsp.controllers;

import dev.srebootcamp.locationsp.clients.ITransicsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class LocationspController {

    @Autowired
    ITransicsClient transicsClient;

    @GetMapping(value = "/trucklocations", produces = "application/json")
    public List<Map<String, Object>> endPoint() throws Exception {
        return ITransicsClient.trim(transicsClient.callTransics());
    }
}
