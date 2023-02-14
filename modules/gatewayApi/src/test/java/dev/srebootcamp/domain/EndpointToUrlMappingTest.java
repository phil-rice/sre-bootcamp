package dev.srebootcamp.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EndpointToUrlMappingTest {

    @Test
    void testGetBaseUrlFor() throws MalformedURLException {
        EndpointToUrlMapping endpointToUrlMapping = new EndpointToUrlMapping("orchUrl", "loginUrl");
        assertEquals("orchUrl", endpointToUrlMapping.getBaseUrlFor("/payment/one/two"));
        assertEquals("loginUrl", endpointToUrlMapping.getBaseUrlFor("/login/one/two"));
    }

    @Autowired
    IEndpointToUrlMapping mapping;

//    @Test
    void testGetBaseUrlFor2() throws MalformedURLException {
        EndpointToUrlMapping mapping2 = (EndpointToUrlMapping) mapping;
        assertEquals(Map.of(), mapping2.endpointToUrlMapping);

    }
}
