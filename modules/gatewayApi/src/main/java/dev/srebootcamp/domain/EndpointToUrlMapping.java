package dev.srebootcamp.domain;

import dev.srebootcamp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@PropertySource("classpath:gateway.properties")
public class EndpointToUrlMapping implements IEndpointToUrlMapping {


    Map<String, String> endpointToUrlMapping;

    EndpointToUrlMapping(@Value("${endpoint.approval.ochestration.url}") String orchestration,
                         @Value("${endpoint.login.url}") String loginUrl) {
        if (orchestration == null) throw new RuntimeException("endpoint.approval.ochestration.url is not defined");
        if (loginUrl == null) throw new RuntimeException("endpoint.login.url is not defined");
        endpointToUrlMapping = Map.of(
                "payment", orchestration,
                "login", loginUrl);
    }


    @Override
    public String getBaseUrlFor(String urlString) {
        String key = StringUtils.firstSegment(urlString);
        String url = endpointToUrlMapping.get(key);
        if (url == null)
            throw new RuntimeException("No mapping found for " + urlString + " key is [" + key + "]. Legal values are " + endpointToUrlMapping.keySet());
        return url;
    }
}
