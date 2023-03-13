package dev.srebootcamp.location.clients;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class VehicleDriverTruckCustomer implements IVehicleDriverTruckCustomer {
    public JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public Supplier<List<Map<String, Object>>> forCustomer(String customer) {
        return () -> {
            String sql = "SELECT * FROM driver_and_vehicles";
            return jdbcTemplate.queryForList(sql);

        };
    }
}
