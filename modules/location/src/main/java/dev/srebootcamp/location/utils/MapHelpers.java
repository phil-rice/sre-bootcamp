package dev.srebootcamp.location.utils;

import java.util.HashMap;
import java.util.Map;

public interface MapHelpers {
    static <From, To> Map<From, To> append(Map<From, To> map1, Map<From, To> map2) {
        var result = new HashMap<From, To>(map1.size(), map2.size());
        result.putAll(map1);
        result.putAll(map2);
        return result;
    }

}
