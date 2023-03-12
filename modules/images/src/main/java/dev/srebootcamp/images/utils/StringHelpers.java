package dev.srebootcamp.images.utils;

import java.util.Arrays;
import java.util.List;

public interface StringHelpers {
    static String repeat(String s, int n, String sep) {
        return String.join(sep, ListHelpers.fill(s, n));
    }

    static List<String> split(String s, String sep) {
        return Arrays.stream(s.split(sep)).filter((part) -> part.length() > 0).toList();
    }
}
