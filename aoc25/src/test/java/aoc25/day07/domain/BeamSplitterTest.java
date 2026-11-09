package aoc25.day07.domain;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BeamSplitterTest {

    static final List<String> ROWS_1 = List.of(
            ".......S.......",
            "...............",
            ".......^.......",
            "...............",
            "......^.^......",
            "...............",
            ".....^.^.^.....",
            "...............",
            "....^.^...^....",
            "...............",
            "...^.^...^.^...",
            "...............",
            "..^...^.....^..",
            "...............",
            ".^.^.^.^.^...^.",
            "..............."
    );

    static final List<String> ROWS_2 = List.of(
            "...S...........",
            "...............",
            "...^...........",
            "...............",
            "..^.^..........",
            "..............."
    );

    static final List<String> INVALID_ROWS_1 = List.of(
            "...............",
            "...............",
            "...^...........",
            "...............",
            "..^.^..........",
            "..............."
    );

    @Test
    void fromExampleRows1() {
        Map<Integer, NavigableSet<Integer>> expectedSplitters = Map.of(
                2, new TreeSet<>(Set.of(0)),
                4, new TreeSet<>(Set.of(-1, 1)),
                6, new TreeSet<>(Set.of(-2, 0, 2)),
                8, new TreeSet<>(Set.of(-3, -1, 3)),
                10, new TreeSet<>(Set.of(-4, -2, 2, 4)),
                12, new TreeSet<>(Set.of(-5, -1, 5)),
                14, new TreeSet<>(Set.of(-6, -4, -2, 0, 2, 6))
        );

        BeamSplitter beamSplitter = BeamSplitter.fromRows(ROWS_1);

        assertEquals(expectedSplitters, beamSplitter.splittersByRow);
    }

    /** test beam-path which has start 'S' off-center */
    @Test
    void fromExampleRows2() {
        Map<Integer, NavigableSet<Integer>> expectedSplitters = Map.of(
                2, new TreeSet<>(Set.of(0)),
                4, new TreeSet<>(Set.of(-1, 1))
        );

        BeamSplitter beamSplitter = BeamSplitter.fromRows(ROWS_2);

        assertEquals(expectedSplitters, beamSplitter.splittersByRow);
    }

    /** test beam-path which has no start 'S' should throw */
    @Test
    void fromInvalidRows1ThrowsForMissingStart() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> BeamSplitter.fromRows(INVALID_ROWS_1)
        );

        assertEquals("first line does not contain a start 'S' to the beam-path", exception.getMessage());
    }

}
