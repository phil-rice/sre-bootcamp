package dev.srebootcamp.location;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srebootcamp.location.clients.ISmartphoneClient;
import dev.srebootcamp.location.clients.IVehicleDriverTruck;
import dev.srebootcamp.location.utils.MapHelpers;

import java.util.List;
import java.util.Map;

import static dev.srebootcamp.location.Constants.*;

public interface LocationFixture {
    Map<String, Object> truck1 = Map.of(truckIdName, "1", xPosName, 1.0, yPosName, 1.0);
    Map<String, Object> truck2 = Map.of(truckIdName, "2", xPosName, 2.0, yPosName, 2.0);
    Map<String, Object> truck3 = Map.of(truckIdName, "3", xPosName, 3.0, yPosName, 3.0);

    List<Map<String, Object>> trucks = List.of(truck1, truck2, truck3);

    static String trucksJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(trucks);
    }

    Map<String, Object> dv1 = Map.of(driveIdDbName, "1", vehicleIdDbName, "1", truckIdDbName, "1");
    Map<String, Object> dv2 = Map.of(driveIdDbName, "2", vehicleIdDbName, "2", truckIdDbName, "2");
    Map<String, Object> dv3 = Map.of(driveIdDbName, "3", vehicleIdDbName, "3", truckIdDbName, "3");
    List<Map<String, Object>> driverAndVehicles = List.of(dv1, dv2, dv3);

    Map<String, Object> dvt1 = MapHelpers.append(dv1, truck1);
    Map<String, Object> dvt2 = MapHelpers.append(dv2, truck2);
    Map<String, Object> dvt3 = MapHelpers.append(dv3, truck3);
    List<Map<String, Object>> driverAndVehiclesWithTrucks = List.of(dvt1, dvt2, dvt3);

    static String driverAndVehiclesJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(driverAndVehicles);
    }

    static ISmartphoneClient testSmartphoneClient() {
        return () -> trucks;
    }

    static IVehicleDriverTruck testVehicleDriverTruck() {
        return () -> driverAndVehicles;
    }
}
