package aoc25.day04;

import java.io.IOException;
import java.nio.file.Path;
import java.util.BitSet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc25.day04.domain.CellGrid;

public class Day04Test {

    @Test
    void parseTheExampleInput() throws Exception {
        Path path = Path.of(Day04.TEST_PATH);
        CellGrid input = Day04.parse(path);

        // BitSet cannot be conveniently constructed from String
        BitSet bitsFirst = new BitSet();
        bitsFirst.set(2);
        bitsFirst.set(3);
        bitsFirst.set(5);
        bitsFirst.set(6);
        bitsFirst.set(7);
        bitsFirst.set(8); // 0011011110

        BitSet bitsLast = new BitSet();
        bitsLast.set(0);
        bitsLast.set(2);
        bitsLast.set(4);
        bitsLast.set(5);
        bitsLast.set(6);
        bitsLast.set(8); // 1010111010

        assertEquals(bitsFirst, input.getFirst());
        assertEquals(bitsLast, input.getLast());
    }

    @Test
    public void getAccessibleItemsForExampleInput() throws IOException {
        // unfortunately this relies on parse, due to the manual construction of BitSets
        // TODO: consider adding helper function
        Path path = Path.of(Day04.TEST_PATH);
        CellGrid input = Day04.parse(path);

        int expected = 13;
        int returned = Day04.numberOfAccessibleItems(input);

        assertEquals(expected, returned);
    }

}
