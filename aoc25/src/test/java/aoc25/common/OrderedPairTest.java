package aoc25.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderedPairTest {

    static final OrderedPair REFERENCE = new OrderedPair(10L, 20L);

    @Test
    void overlapsOnTheRight() {
        OrderedPair candidate = new OrderedPair(15L, 30L);
        assertTrue(REFERENCE.overlaps(candidate));
    }

    @Test
    void overlapsOnTheRightBoundary() {
        OrderedPair candidate = new OrderedPair(20L, 30L);

        OrderedPair expectedMerge = new OrderedPair(10L, 30L);
        OrderedPair merge = REFERENCE.merge(candidate);

        assertTrue(REFERENCE.overlaps(candidate));
        assertEquals(expectedMerge, merge);
    }

    @Test
    void overlapsOnTheLeft() {
        OrderedPair candidate = new OrderedPair(0L, 15L);
        assertTrue(REFERENCE.overlaps(candidate));
    }

    @Test
    void overlapsOnTheLeftBoundary() {
        OrderedPair candidate = new OrderedPair(0L, 10L);
        assertTrue(REFERENCE.overlaps(candidate));
    }

    @Test
    void overlapsFully() {
        OrderedPair candidate = new OrderedPair(12L, 15L);
        assertTrue(REFERENCE.overlaps(candidate));
    }

}
