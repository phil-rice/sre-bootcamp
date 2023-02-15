package dev.srebootcamp.gatewayApi.domain;

import dev.srebootcamp.gatewayApi.domain.IEndpointToUrlMapping;
import dev.srebootcamp.gatewayApi.utils.StringUtils;
import org.springframework.boot.test.context.TestConfiguration;

import java.util.Map;

@TestConfiguration
public class MockEndpointToUrlMapping implements IEndpointToUrlMapping {

    Map<String, String> endpointToUrlMapping = Map.of(
            "test1", "http://localhost:8080",
            "test2", "http://localhost:8081"
    );

    @Override
    public String getBaseUrlFor(String urlString) {
        String key = StringUtils.firstSegment(urlString);
        String url = endpointToUrlMapping.get(key);
        if (url == null)
            throw new RuntimeException("No mapping found for " + urlString + " key is [" + key + "]. Legal values are " + endpointToUrlMapping.keySet());
        return url;
    }
}
