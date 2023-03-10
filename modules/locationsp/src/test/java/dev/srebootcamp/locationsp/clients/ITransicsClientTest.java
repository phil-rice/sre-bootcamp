package dev.srebootcamp.locationsp.clients;

import org.junit.jupiter.api.Test;

import static dev.srebootcamp.locationsp.LocationSpFixture.soapVehicles;
import static dev.srebootcamp.locationsp.LocationSpFixture.trucks;
import static org.junit.jupiter.api.Assertions.*;

class ITransicsClientTest {

    @Test
    public void testTrim() throws Exception {
        assertEquals(trucks, ITransicsClient.trim(soapVehicles));
    }
}