package dev.srebootcamp.gatewayApi.utils;

import java.util.Arrays;

public interface StringUtils {

    static String firstSegment(String url) {
        String[] split = Arrays.stream(url.split("/")).filter(s -> !s.isEmpty()).toArray(String[]::new);
        return split.length == 0 ? "" : split[0];
    }
}
