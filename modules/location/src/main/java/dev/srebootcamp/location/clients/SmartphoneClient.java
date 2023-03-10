package dev.srebootcamp.location.clients;

import dev.srebootcamp.location.utils.GenericGetClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SmartphoneClient extends GenericGetClient<List<Map<String, Object>>> implements ISmartphoneClient {

    public SmartphoneClient(@Value("client.smartphone.url") String clientUrl) {
        super(clientUrl, List.class);
    }
}
