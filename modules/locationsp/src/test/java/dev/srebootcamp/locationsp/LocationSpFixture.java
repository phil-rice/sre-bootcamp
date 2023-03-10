package dev.srebootcamp.locationsp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srebootcamp.locationsp.clients.ITransicsClient;
import dev.srebootcamp.locationsp.domain.SoapVehicle;
import dev.srebootcamp.locationsp.domain.SoapVehicles;

import java.util.List;
import java.util.Map;

import static dev.srebootcamp.locationsp.Constants.*;

public interface LocationSpFixture {

    SoapVehicle st1 = new SoapVehicle("1", 1.0, 1.0);
    SoapVehicle st2 = new SoapVehicle("2", 2.0, 2.0);
    SoapVehicle st3 = new SoapVehicle("3", 3.0, 3.0);
    SoapVehicles soapVehicles = new SoapVehicles(List.of(st1, st2, st3));
    Map<String, Object> truck1 = Map.of(truckIdName, "1", xPosName, 1.0, yPosName, 1.0);
    Map<String, Object> truck2 = Map.of(truckIdName, "2", xPosName, 2.0, yPosName, 2.0);
    Map<String, Object> truck3 = Map.of(truckIdName, "3", xPosName, 3.0, yPosName, 3.0);

    List<Map<String, Object>> trucks = List.of(truck1, truck2, truck3);

    static ITransicsClient testTransicsClient() {
        return () -> soapVehicles;
    }

    static String trucksJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(trucks);
    }

}
