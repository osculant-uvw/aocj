package aoc25.day05;

import aoc25.common.OrderedPair;

import java.nio.file.Path;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {

    static final String TEST_PATH = "src/test/inputs/day05test.txt";

    @Test
    void parseTheExampleInput() throws Exception{
        Set<Long> expectedValues = Set.of(
                1L, 5L, 8L, 11L, 17L, 32L
        );

        Set<OrderedPair> expectedRanges = Set.of(
                new OrderedPair(3L, 5L),
                new OrderedPair(10L, 14L),
                new OrderedPair(16L, 20L),
                new OrderedPair(12L, 18L)
        );

        Path path = Path.of(TEST_PATH);
        Day05.Inventory state = Day05.parse(path);

        assertEquals(expectedValues, state.ids());
        assertEquals(expectedRanges, state.idRanges());
    }

}
