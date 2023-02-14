package dev.srebootcamp.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

}
