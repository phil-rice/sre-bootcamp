package dev.srebootcamp.images.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListHelpersTest {

    @Test
    public void testMap() throws Exception {
        assertEquals(List.of(2, 4, 6), ListHelpers.map(List.of(1, 2, 3), x -> x * 2));
    }

    @Test
    public void testFill() {
        assertEquals(List.of(1, 1, 1), ListHelpers.fill(1, 3));
    }

    @Test
    public void testCombine() {
        assertEquals(List.of(1, 2, 3, 4, 5), ListHelpers.combine(List.of(1, 2, 3), List.of(4, 5)));
    }
    @Test
    public void testForEachWithIndex() throws Exception {
        List<Integer> list = List.of(2, 4, 6);
        List<Integer> rememberedItems = new ArrayList<>();
        List<Integer> rememberedIndexes = new ArrayList<>();
        ListHelpers.forEachWithIndex(list, (x, i) -> {
            rememberedItems.add(x);
            rememberedIndexes.add(i);
        });
        assertEquals(list, rememberedItems);
        assertEquals(List.of(0, 1, 2), rememberedIndexes);
    }

}