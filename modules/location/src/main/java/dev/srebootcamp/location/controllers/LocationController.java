package dev.srebootcamp.location.controllers;

import dev.srebootcamp.location.clients.ISmartphoneClient;
import dev.srebootcamp.location.clients.IVehicleDriverTruck;
import dev.srebootcamp.location.clients.IVehicleDriverTruckCustomer;
import dev.srebootcamp.location.domain.LocationDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static dev.srebootcamp.location.utils.Response.responseWhenOk;

@RestController
public class LocationController {

    @Autowired
    ISmartphoneClient smartphoneClient;

    @Autowired
    IVehicleDriverTruck vehicleDriverTruck;

    @Autowired
    IVehicleDriverTruckCustomer vehicleDriverTruckCustomer;

    @GetMapping(value = "/trucklocations", produces = "application/json")
    public Map<String, Object> endPoint() throws Exception {
        return responseWhenOk(LocationDomain.loadLocations(smartphoneClient, vehicleDriverTruck));
    }

    @GetMapping(value = "/trucklocations/{customerId}", produces = "application/json")
    public Map<String, Object> endPoint(@PathVariable String customerId) throws Exception {
        return responseWhenOk(LocationDomain.loadLocations(smartphoneClient, vehicleDriverTruckCustomer.forCustomer(customerId)));
    }
}
