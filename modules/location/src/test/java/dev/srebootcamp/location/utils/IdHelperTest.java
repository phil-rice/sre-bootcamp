package dev.srebootcamp.location.utils;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IdHelperTest {
    @Test
    public void testIdFnReturnsAFunctionThatGetsTheFieldFromTheMap() throws Exception {
        Map<String, Object> map = Map.of("id", 123);
        var idFn = IdHelper.idFn("id");
        assertEquals("123", idFn.apply(map));
    }
}