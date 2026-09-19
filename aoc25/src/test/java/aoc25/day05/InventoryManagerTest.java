package aoc25.day05;

import aoc25.common.OrderedPair;
import aoc25.day05.domain.InventoryManager;

import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryManagerTest {

    public static Set<Long> VALUES = Set.of(
            1L, 5L, 8L, 11L, 17L, 32L
    );

    public static Set<OrderedPair> RANGES = Set.of(
            new OrderedPair(3L, 5L),
            new OrderedPair(10L, 14L),
            new OrderedPair(16L, 20L),
            new OrderedPair(12L, 18L)
    );

    @Test
    public void inventoryForExampleInput() {
        InventoryManager manager = new InventoryManager(VALUES, RANGES);

        Set<Long> expectedIdsInRanges = Set.of(
                5L, 11L, 17L
        );

        Set<OrderedPair> expectedIdRanges = Set.of(
                new OrderedPair(3L, 5L),
                new OrderedPair(10L, 20L)
        );

        assertEquals(expectedIdsInRanges, manager.idsInRanges());
        assertEquals(expectedIdRanges, manager.idRanges());
    }

}
