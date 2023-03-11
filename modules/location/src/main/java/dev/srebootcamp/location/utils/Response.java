package dev.srebootcamp.location.utils;

import java.util.HashMap;
import java.util.Map;

public interface Response {

    static Map<String, Object> responseWhenOk(Object data) {
        return response(0, "OK", data);
    }

    static Map<String, Object> response(int code, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("response", response(code, message));
        response.put("data", data);
        return response;
    }

    static Map<String, Object> response(int code, String message) {
        return Map.of("code", code, "message", message);
    }

}
