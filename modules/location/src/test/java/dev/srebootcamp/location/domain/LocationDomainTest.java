package dev.srebootcamp.location.domain;

import org.junit.jupiter.api.Test;

import static dev.srebootcamp.location.LocationFixture.*;
import static dev.srebootcamp.location.utils.SchemaHelper.validateJsonArrayAgainstSchema;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationDomainTest {

    @Test
    public void testLocationDomain() throws Exception {
        var actual = LocationDomain.loadLocations(testSmartphoneClient(), testVehicleDriverTruck());
        assertEquals(driverAndVehiclesWithTrucks, actual);
    }

    @Test
    public void testLocationMeetsJsonSchema() throws Exception {
        validateJsonArrayAgainstSchema("/transport.schema.json", driverAndVehiclesWithTrucksJson());
    }

}