package dev.srebootcamp.location.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class VehicleDriverTruck implements IVehicleDriverTruck {

    public JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public List<Map<String, Object>> get() {
        String sql = "SELECT * FROM driver_and_vehicles";
        return jdbcTemplate.queryForList(sql);
    }

}
