package dev.srebootcamp.location.domain;

import dev.srebootcamp.location.Constants;
import dev.srebootcamp.location.clients.ISmartphoneClient;
import dev.srebootcamp.location.clients.IVehicleDriverTruck;
import dev.srebootcamp.location.clients.IVehicleDriverTruckCustomer;
import dev.srebootcamp.location.utils.MapHelpers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static dev.srebootcamp.location.utils.IdHelper.idFn;
import static dev.srebootcamp.location.utils.ListHelpers.*;

@Component
public interface LocationDomain {

    static List<Map<String, Object>> loadLocations(ISmartphoneClient smartphoneClient, Supplier<List<Map<String,Object>>> vehicleDriverTruck) throws Exception {
        List<Map<String, Object>> driverAndVehicles = vehicleDriverTruck.get();
        Map<String, Map<String, Object>> trucksToTrucksAndPositions = toIdMap(smartphoneClient.retrieveTrucksAndPosition(), idFn(Constants.truckIdName));
        return enrichFromMap(
                driverAndVehicles,
                trucksToTrucksAndPositions,
                idFn(Constants.truckIdDbName),
                MapHelpers::append);
    }

}
