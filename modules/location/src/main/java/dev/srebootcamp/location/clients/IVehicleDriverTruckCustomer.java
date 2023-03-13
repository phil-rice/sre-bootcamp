package dev.srebootcamp.location.clients;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public interface IVehicleDriverTruckCustomer {

    Supplier<List<Map<String,Object>>> forCustomer(String customer);

}
