package dev.srebootcamp.locationsp.clients;

import dev.srebootcamp.locationsp.domain.SoapVehicles;
import dev.srebootcamp.locationsp.utils.ListHelpers;

import java.util.List;
import java.util.Map;

import static dev.srebootcamp.locationsp.Constants.truckIdName;

public interface ITransicsClient {

    SoapVehicles callTransics();

    static List<Map<String, Object>> trim(SoapVehicles soapVehicles) throws Exception {
        return ListHelpers.map(soapVehicles.vehicles(),
                v -> Map.of(truckIdName, v.truckLicensePlate(),
                        "xPos", v.xPos(),
                        "yPos", v.yPos()));
    }
}
