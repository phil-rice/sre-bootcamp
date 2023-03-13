package dev.srebootcamp.locationsp.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static dev.srebootcamp.locationsp.LocationSpFixture.testTransicsClient;
import static dev.srebootcamp.locationsp.LocationSpFixture.trucksJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationspControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    LocationspController locationspController;

    @Test
    public void test_locationsp_endpoint() throws Exception {
        locationspController.transicsClient = testTransicsClient();
        mockMvc.perform(get("/trucklocations").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(trucksJson()));
    }
}