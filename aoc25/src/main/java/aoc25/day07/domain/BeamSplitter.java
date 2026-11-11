package aoc25.day07.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.List;
import java.util.TreeSet;

public class BeamSplitter {

    final Map<Integer, NavigableSet<Integer>> splittersByRow;
    final int lastRowIdx;

    // each row, column records a position the beam-path has visited
    // each position has a count of the number of trajectories which have reached the position
    final Map<Integer, Map<Integer, Long>> pathCountsByRow = new HashMap<>();
    int currentRowIdx = 0;

    private BeamSplitter(Map<Integer, NavigableSet<Integer>> splittersByRow) {
        this.splittersByRow = splittersByRow;
        this.lastRowIdx = Collections.max(splittersByRow.keySet()) + 1;

        this.pathCountsByRow.put(this.currentRowIdx, new HashMap<>(Map.of(0, 1L)));
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

    /**
     * Walk the beam(s) forward,
     * each beam impeded by a beam-splitter forms two new beams to the right and left of the splitter.
     * Keeps count of the incoming trajectories (timelines), by propagating the count to each destination.
     * @return the number of beams split.
     */
    public int walk() {
        int numberOfBeamsSplit = 0;

        if (currentRowIdx + 1 > lastRowIdx) {
            return numberOfBeamsSplit;
        }

        Map<Integer, Long> nextPaths = new HashMap<>();
        NavigableSet<Integer> nextSplitters  = splittersByRow.getOrDefault(
                currentRowIdx + 1, Collections.emptyNavigableSet()
        );

        Map<Integer, Long> currentPaths = pathCountsByRow.get(currentRowIdx);
        for (int position : currentPaths.keySet()) {
            long count = currentPaths.get(position);

            if (nextSplitters.contains(position)) {
                nextPaths.merge(position - 1, count, Long::sum);
                nextPaths.merge(position + 1, count, Long::sum);
                numberOfBeamsSplit++;
            } else {
                nextPaths.merge(position, count, Long::sum);
            }
        }

        currentRowIdx++;
        pathCountsByRow.put(currentRowIdx, nextPaths);

        return numberOfBeamsSplit;
    }

    public int getLastRowIdx() { return lastRowIdx; }

    public long getNumberOfTimelines() {
        Map<Integer, Long> current = pathCountsByRow.get(currentRowIdx);
        return current.values().stream().mapToLong(Long::longValue).sum();
    }

}
