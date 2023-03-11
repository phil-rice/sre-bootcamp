package dev.srebootcamp.location.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface JsonHelper {
    static ObjectMapper objectMapper = new ObjectMapper();

    static String asString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
