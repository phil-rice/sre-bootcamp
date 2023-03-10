package dev.srebootcamp.locationsp.clients;

import dev.srebootcamp.locationsp.domain.SoapVehicles;
import org.springframework.stereotype.Component;

@Component
public class TransicsClient implements ITransicsClient {
    @Override
    public SoapVehicles callTransics() {
        throw new RuntimeException("This is a stub method.");
    }
}
