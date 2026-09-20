package aoc25.day06;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Day06Test {

    static final String TEST_PATH = "src/test/inputs/day06test.txt";

    @Test
    void parseTheExampleInput() throws IOException {
        // parse inverts columns from the input to rows
        long[][] expectedNumbersGrid = new long[][] {
                {123L, 45L, 6L},
                {328L, 64L, 98L},
                {51L, 387L, 215L},
                {64L, 23L, 314L}
        };

        char[] expectedOperations = new char[] {
                '*', '+', '*', '+'
        };

        Day06.Calculations grid = Day06.parse(Path.of(TEST_PATH));

        assertArrayEquals(expectedNumbersGrid, grid.numbersGrid());
        assertArrayEquals(expectedOperations, grid.operations());
    }

}
