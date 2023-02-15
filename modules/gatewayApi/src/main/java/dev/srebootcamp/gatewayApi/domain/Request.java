package dev.srebootcamp.gatewayApi.domain;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Enumeration;

public record Request(String method, String url, String body, MultiValueMap<String, String> headers) {
    public static Request from(HttpServletRequest req) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        for (Enumeration<String> headernames = req.getHeaderNames(); headernames.hasMoreElements(); ) {
            String headerName = headernames.nextElement();
            for (Enumeration<String> headerValues = req.getHeaders(headerName); headerValues.hasMoreElements(); )
                headers.add(headerName, headerValues.nextElement());
        }
        String body = req.getReader().lines().reduce("", (a, b) -> a + b + "\n");
        return new Request(req.getMethod(), req.getRequestURI(), body, headers);
    }
}
