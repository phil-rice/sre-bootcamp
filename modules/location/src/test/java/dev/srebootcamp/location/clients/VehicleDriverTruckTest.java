package dev.srebootcamp.location.clients;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import static dev.srebootcamp.location.LocationFixture.driverAndVehiclesWithTrucks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class VehicleDriverTruckTest {

    @Test
    public void testVehicleDriverTruckGetsDataFromDatabase() {
        var jdbc = Mockito.mock(JdbcTemplate.class);
        var vdt = new VehicleDriverTruck();
        vdt.jdbcTemplate = jdbc;
        when(jdbc.queryForList(anyString())).thenReturn(driverAndVehiclesWithTrucks);
        assertEquals(driverAndVehiclesWithTrucks, vdt.call());
    }
}
