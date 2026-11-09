package aoc25.day07.domain;

import java.util.*;

public class BeamSplitter {

    final Map<Integer, NavigableSet<Integer>> splittersByRow;
    final Map<Integer, NavigableSet<Integer>> pathsByRow = new HashMap<>();
    int currentRow = 0;

    private BeamSplitter(Map<Integer, NavigableSet<Integer>> splittersByRow) {
        this.splittersByRow = splittersByRow;
        this.pathsByRow.put(this.currentRow, new TreeSet<>(Set.of(0)));
    }

    /**
     * @param rows containing S to indicate the start of the beam-path
     *             and ^ to indicate the positions of beam-splitters.
     *             The beam-splitters are expected to be seperated by an empty line indicating
     *             the space between the last split.
     * @return a BeamSplitter whose path may be simulated.
     */
    public static BeamSplitter fromRows(List<String> rows) throws IllegalArgumentException {
        Map<Integer, NavigableSet<Integer>> splitters = new HashMap<>();

        int startIdx = rows.getFirst().indexOf('S');
        if (startIdx == -1) {
            throw new IllegalArgumentException(
                    "first line does not contain a start 'S' to the beam-path"
            );
        }

        for (int lineIdx = 2; lineIdx < rows.size(); lineIdx += 2) {
            NavigableSet<Integer> splittersInRow = new TreeSet<>();
            splitters.put(lineIdx, splittersInRow);

            String line = rows.get(lineIdx);

            int charIdx = 0;
            for (char character : line.toCharArray()) {
                if (character == '^') {
                    splitters.get(lineIdx).add(charIdx - startIdx);
                }
                charIdx++;
            }
        }

        return new BeamSplitter(splitters);
    }

    // TODO: implement to make tests pictorial
    public List<String> toRows() {
        return new ArrayList<>(List.of());
    }

    // TODO: implement method which simulates the beam-path
    /**
     * Walk the beam(s) forward by two steps,
     * each beam impeded by a beam-splitter forms two new beams to the right and left of the splitter.
     * @return the number of beams split.
     */
    public int walk() {
        return 0;
    }

}
