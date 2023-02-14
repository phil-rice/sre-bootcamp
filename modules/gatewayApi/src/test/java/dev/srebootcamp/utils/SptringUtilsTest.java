package dev.srebootcamp.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SptringUtilsTest {

    @Test
    public void testFirstSegment() {

        assertEquals("test", StringUtils.firstSegment("/test/one/two"));
        assertEquals("test", StringUtils.firstSegment("//test//one/two"));
        assertEquals("", StringUtils.firstSegment("/"));
        assertEquals("", StringUtils.firstSegment(""));
    }
}
