package dev.srebootcamp.location.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.srebootcamp.location.utils.IdHelper.idFn;
import static dev.srebootcamp.location.utils.ListHelpers.enrichFromMap;
import static org.junit.jupiter.api.Assertions.*;

class ListHelpersTest {


    final Map<String, Object> m11 = Map.of("id", 1, "data1", "data11");
    final Map<String, Object> m21 = Map.of("id", 1, "data2", "data21");
    final Map<String, Object> m12 = Map.of("id", 2, "data1", "data12");
    final Map<String, Object> m22 = Map.of("id", 2, "data2", "data22");

    @Test
    public void testMap() throws Exception {
        var list = List.of(1, 2, 3);
        FunctionWithException<Integer, Integer> time2 = x -> x * 2;
        var result = ListHelpers.map(list, time2);
        assertEquals(List.of(2, 4, 6), result);
    }

    @Test
    public void testToIdMap() throws Exception {
        List<Map<String, Object>> list = List.of(m11, m12);
        Map<String, Map<String, Object>> result = ListHelpers.toIdMap(list, idFn("id"));
        assertEquals(new HashMap<>(Map.of("1", m11, "2", m12)), result);
    }

    @Test
    public void testCollect() throws Exception {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        assertEquals(List.of(4, 8), ListHelpers.collect(list, x -> x % 2 == 0, y -> y * 2));
    }

    @Test
    public void testEnrichFromMap() throws Exception {
        var list = List.of(m11, m12);
        var enrich = Map.of("1", m21, "2", m22);
        var actual = enrichFromMap(list, enrich, idFn("id"), MapHelpers::append);
        assertEquals(List.of(
                MapHelpers.append(m11, m21),
                MapHelpers.append(m12, m22)
        ), actual);
    }
}