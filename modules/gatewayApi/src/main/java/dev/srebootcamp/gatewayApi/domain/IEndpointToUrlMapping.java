package dev.srebootcamp.gatewayApi.domain;

import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;


public interface IEndpointToUrlMapping {
    String getBaseUrlFor(String urlString);
}
