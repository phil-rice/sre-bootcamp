package dev.srebootcamp.location.domain;

import org.junit.jupiter.api.Test;

import static dev.srebootcamp.location.LocationFixture.*;
import static org.junit.jupiter.api.Assertions.*;

class LocationDomainTest {

    @Test
    public void testLocationDomain() throws Exception {
        var actual = LocationDomain.loadLocations(testSmartphoneClient(), testVehicleDriverTruck());
        assertEquals(driverAndVehiclesWithTrucks, actual);
    }

}