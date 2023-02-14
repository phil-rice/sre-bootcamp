package dev.srebootcamp;

import dev.srebootcamp.domain.IEndpointToUrlMapping;
import dev.srebootcamp.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
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
