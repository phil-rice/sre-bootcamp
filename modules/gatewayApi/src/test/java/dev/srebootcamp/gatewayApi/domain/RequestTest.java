package dev.srebootcamp.gatewayApi.domain;

import dev.srebootcamp.gatewayApi.domain.Request;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {
    @Test
    public void testFrom() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "http://something:999/one/two");
        request.addHeader("Content-Type", "application/json");
        request.setContent("someContent".getBytes(StandardCharsets.UTF_8));
        Request req = Request.from(request);
        MultiValueMap<String, String> expectedHeaders = new HttpHeaders();
        expectedHeaders.add("Content-Type", "application/json");
        assertEquals(new Request("POST", "http://something:999/one/two", "someContent\n", expectedHeaders), req);


    }

}