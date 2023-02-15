package dev.srebootcamp.gatewayApi.domain;

import dev.srebootcamp.gatewayApi.domain.EndpointToUrlMapping;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EndpointToUrlMappingTest {

    @Test
    void testGetBaseUrlFor() throws MalformedURLException {
        EndpointToUrlMapping endpointToUrlMapping = new EndpointToUrlMapping("orchUrl", "loginUrl");
        assertEquals("orchUrl", endpointToUrlMapping.getBaseUrlFor("/payment/one/two"));
        assertEquals("loginUrl", endpointToUrlMapping.getBaseUrlFor("/login/one/two"));
    }

}
