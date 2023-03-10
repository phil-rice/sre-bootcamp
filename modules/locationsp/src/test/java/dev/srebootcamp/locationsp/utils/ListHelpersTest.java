package dev.srebootcamp.locationsp.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListHelpersTest {

    @Test
    public void testMap() throws Exception {
        assertEquals(List.of(2, 4, 6), ListHelpers.map(List.of(1, 2, 3), x -> x * 2));
    }

}