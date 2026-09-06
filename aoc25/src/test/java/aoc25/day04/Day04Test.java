package aoc25.day04;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc25.day04.domain.CellGrid;

public class Day04Test {

    @Test
    void parseTheExampleInput() throws Exception {
        int expectedHeight = 10;
        int expectedWidth = 10;

        List<List<Integer>> expectedIndexes = Arrays.asList(
                Arrays.asList(2, 3, 5, 6, 7, 8),
                Arrays.asList(0, 1, 2, 4, 6, 8, 9),
                Arrays.asList(0, 1, 2, 3, 4, 6, 8, 9),
                Arrays.asList(0, 2, 3, 4, 5, 8),
                Arrays.asList(0, 1, 3, 4, 5, 6, 8, 9),
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9),
                Arrays.asList(1, 3, 5, 7, 8, 9),
                Arrays.asList(0, 2, 3, 4, 6, 7, 8, 9),
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8),
                Arrays.asList(0, 2, 4, 5, 6, 8)
        );

        CellGrid inputGrid = Day04.parse(Path.of(Day04.TEST_PATH));

        assertEquals(expectedHeight, inputGrid.height());
        assertEquals(expectedWidth, inputGrid.width);

        // TODO: can test be made more direct?
        for (int row = 0; row < expectedIndexes.size(); row++) {

            /* example
            expected indexes     expected boolean       actual boolean
            [2,3,5,6,7,8]        column 0 → false       grid.get(0,0) → false
            [2,3,5,6,7,8]        column 1 → false       grid.get(0,1) → false
            [2,3,5,6,7,8]        column 2 → true        grid.get(0,2) → true
            [2,3,5,6,7,8]        column 3 → true        grid.get(0,3) → true
            [2,3,5,6,7,8]        column 4 → false       grid.get(0,4) → false
            */
            for (int column = 0; column < inputGrid.width; column++) {
                assertEquals(
                        expectedIndexes.get(row).contains(column),
                        inputGrid.getCell(row, column),
                        "mismatch at (" + row + ", " + column + ")"
                );
            }

        }

    }

}
