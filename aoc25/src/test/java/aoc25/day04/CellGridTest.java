package aoc25.day04;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc25.day04.domain.CellGrid;

public class CellGridTest {

    @Test
    public void getAccessibleItemsForExampleInput() throws IOException {
        // unfortunately this relies on parse, due to the manual construction of BitSets
        // TODO: consider adding helper function
        Path path = Path.of(Day04.TEST_PATH);
        CellGrid grid = Day04.parse(path);

        int expected = 13;
        int removed = grid.update();

        assertEquals(expected, removed);
    }

}
