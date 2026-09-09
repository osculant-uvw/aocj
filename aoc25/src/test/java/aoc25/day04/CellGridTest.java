package aoc25.day04;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc25.day04.domain.CellGrid;

public class CellGridTest {

    public static List<String> START =  List.of(
            "..@@.@@@@.",
            "@@@.@.@.@@",
            "@@@@@.@.@@",
            "@.@@@@..@.",
            "@@.@@@@.@@",
            ".@@@@@@@.@",
            ".@.@.@.@@@",
            "@.@@@.@@@@",
            ".@@@@@@@@.",
            "@.@.@@@.@."
    );

    public static int MAX_NEIGHBOURS = 4;

    @Test
    public void onFirstGridUpdateForExampleInput() {
        int expectedCellsRemoved = 13;
        List<String> expectedCellGridOnFirstUpdate = List.of(
                ".......@..",
                ".@@.@.@.@@",
                "@@@@@...@@",
                "@.@@@@..@.",
                ".@.@@@@.@.",
                ".@@@@@@@.@",
                ".@.@.@.@@@",
                "..@@@.@@@@",
                ".@@@@@@@@.",
                "....@@@..."
        );

        CellGrid grid = CellGrid.fromRows(START);

        assertEquals(expectedCellsRemoved, grid.update(MAX_NEIGHBOURS));
        assertEquals(expectedCellGridOnFirstUpdate, grid.toRows());
    }

    @Test
    public void toFinalGridUpdateForExampleInput() {
        List<Integer> expectedCellsRemoved = List.of(
                13, 12, 7, 5, 2, 1, 1, 1, 1, 0
        );
        List<String> expectedCellGridOnFinalUpdate = List.of(
                "..........",
                "..........",
                "..........",
                "....@@....",
                "...@@@@...",
                "...@@@@@..",
                "...@.@.@@.",
                "...@@.@@@.",
                "...@@@@@..",
                "....@@@..."
        );

        CellGrid grid = CellGrid.fromRows(START);

        int count;
        List<Integer> removed = new ArrayList<>();
        do {
            count = grid.update(MAX_NEIGHBOURS);
            removed.add(count);
        } while (count > 0);

        assertEquals(expectedCellsRemoved, removed);
        assertEquals(expectedCellGridOnFinalUpdate, grid.toRows());
    }

}
