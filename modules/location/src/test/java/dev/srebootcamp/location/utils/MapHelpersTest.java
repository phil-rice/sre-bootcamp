package dev.srebootcamp.location.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapHelpersTest {

    @Test
    public void testAppendReturnsAMapWithAllTheEntriesFromTheTwoMapsOverwritingTheFirst() throws Exception {
        Map<String, String> map1 = new HashMap<>(Map.of("key1", "value1"));
        Map<String, String> map2 = Map.of("key2", "value2");
        Map<String, String> map3 = Map.of("key1", "value1", "key2", "value2");
        Map<String, String> result = MapHelpers.append(map1, map2);
        assertEquals(map3, result);
    }
}