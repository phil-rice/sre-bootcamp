package dev.srebootcamp.location.controllers;

import dev.srebootcamp.location.utils.JsonHelper;
import dev.srebootcamp.location.utils.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static dev.srebootcamp.location.LocationFixture.*;
import static dev.srebootcamp.location.utils.Response.responseWhenOk;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    LocationController locationController;

    @Test
    public void test_location_endpoint() throws Exception {
        locationController.smartphoneClient = testSmartphoneClient();
        locationController.vehicleDriverTruck = testVehicleDriverTruck();
        String expected = JsonHelper.asString(responseWhenOk(driverAndVehicles));
        mockMvc.perform(get("/trucklocations").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(expected));
    }
    @Test
    public void test_location_forcustomer1_endpoint() throws Exception {
        locationController.smartphoneClient = testSmartphoneClient();
        locationController.vehicleDriverTruckCustomer = testVehicleDriverTruckCustomer();
        String expected = JsonHelper.asString(responseWhenOk(driverAndVehiclesAndCustomers1));
        mockMvc.perform(get("/trucklocations/1").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(expected));
    }
    @Test
    public void test_location_forcustomer2_endpoint() throws Exception {
        locationController.smartphoneClient = testSmartphoneClient();
        locationController.vehicleDriverTruckCustomer = testVehicleDriverTruckCustomer();
        String expected = JsonHelper.asString(responseWhenOk(driverAndVehiclesAndCustomers2));
        mockMvc.perform(get("/trucklocations/2").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(expected));
    }
}