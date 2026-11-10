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
            "........S......",
            "...............",
            ".......^.......",
            "...............",
            "......^.^......",
            "...............",
            ".....^.^.^.....",
            "..............."
    );

    static final List<String> INVALID_ROWS_1 = List.of(
            "...............",
            "...............",
            ".......^.......",
            "...............",
            "......^.^......",
            "...............",
            ".....^.^.^.....",
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
                2, new TreeSet<>(Set.of(-1)),
                4, new TreeSet<>(Set.of(-2, 0)),
                6, new TreeSet<>(Set.of(-3, -1, 1))
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

    @Test
    void walkExampleRows1() {
        int expectedTotalBeamsSplits = 21;

        BeamSplitter beamSplitter = BeamSplitter.fromRows(ROWS_1);

        int totalBeamSplits = 0;
        for (int i = 0; i < beamSplitter.getLastRowIdx(); i++) {
            totalBeamSplits += beamSplitter.walk();
        }

        assertEquals(expectedTotalBeamsSplits, totalBeamSplits);
    }

    @Test
    void walkExampleRows2() {
        Map<Integer, NavigableSet<Integer>> expectedPaths = Map.of(
                0, new TreeSet<>(Set.of(0)),
                1, new TreeSet<>(Set.of(0)),
                2, new TreeSet<>(Set.of(0)),
                3, new TreeSet<>(Set.of(0)),
                4, new TreeSet<>(Set.of(-1, 1)),
                5, new TreeSet<>(Set.of(-1, 1)),
                6, new TreeSet<>(Set.of(-2, 0, 2)),
                7, new TreeSet<>(Set.of(-2, 0, 2))
        );
        List<Integer> expectedBeamsSplitOnWalk = List.of(
                0, 0, 0, 1, 0, 2, 0
        );

        BeamSplitter beamSplitter = BeamSplitter.fromRows(ROWS_2);

        List<Integer> beamsSplitOnWalk = new ArrayList<>();
        for (int i = 0; i < beamSplitter.getLastRowIdx(); i++) {
            beamsSplitOnWalk.add(beamSplitter.walk());
        }

        assertEquals(expectedPaths, beamSplitter.pathsByRow);
        assertEquals(expectedBeamsSplitOnWalk, beamsSplitOnWalk);
    }

}
