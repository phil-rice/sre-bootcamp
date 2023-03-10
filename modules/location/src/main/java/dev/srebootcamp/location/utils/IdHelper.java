package dev.srebootcamp.location.utils;

import java.util.Map;

public interface IdHelper {
    static FunctionWithException<Map<String, Object>, String> idFn(String idKey) {
        return map -> {
            Object id = map.get(idKey);
            if (id == null) throw new RuntimeException("id " + idKey + " is null in " + map);
            return id.toString();
        };
    }
}
