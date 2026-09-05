package aoc25.day04;

import aoc25.day04.domain.NeighbourGrid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;


public class Day04 {

    static final String INPUT_PATH = "inputs/day04.txt";
    static final String TEST_PATH = "src/test/inputs/day04test.txt";

    public static void main(String[] args) {
        try {
            final NeighbourGrid grid = parse(Path.of(INPUT_PATH));

            int accessible = numberOfAccessibleItems(grid);
            System.out.printf("number of accessible items: %s", accessible);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    static NeighbourGrid parse(Path path) throws IOException {
        List<BitSet> grid = new ArrayList<>();

        final List<String> input = Files.readAllLines(path);
        final int width = input.getFirst().length();

        int count = 0;
        for (String line : input) {

            if (line.length() != width) {
                throw new IllegalArgumentException(
                        String.format("line [%d] has length %d which does not match the rest of the grid", count, width)
                );
            }

            BitSet bits = new BitSet(width);
            for (int i = 0; i < width; i++) {
                if (line.charAt(i) == '@') {
                    bits.set(i);
                }
            }

            grid.add(bits);
            count++;
        }

        return new NeighbourGrid(grid, width);
    }

    static int numberOfAccessibleItems(NeighbourGrid grid) {
        int accessible = 0;

        BitSet empty = new BitSet(grid.width());

        for (int i = 0; i < grid.grid().size(); i++) {

            BitSet top = i > 0 ? grid.grid().get(i - 1) : empty;
            BitSet current = grid.grid().get(i);
            BitSet bottom = i + 1 < grid.grid().size() ? grid.grid().get(i + 1) : empty;

            for (int j = 0; j < grid.width(); j++) {
                if (!current.get(j)) {
                    continue;
                }

                boolean topLeft = j > 0 && top.get(j - 1);
                boolean topMiddle = top.get(j);
                boolean topRight = top.get(j + 1);

                boolean left = j > 0 && current.get(j - 1);
                boolean right = current.get(j + 1);

                boolean bottomLeft = j > 0 && bottom.get(j - 1);
                boolean bottomMiddle = bottom.get(j);
                boolean bottomRight = bottom.get(j + 1);

                int adjacent = boolToInt(topLeft)
                        + boolToInt(topMiddle)
                        + boolToInt(topRight)
                        + boolToInt(left)
                        + boolToInt(right)
                        + boolToInt(bottomLeft)
                        + boolToInt(bottomMiddle)
                        + boolToInt(bottomRight);

                if (adjacent < 4) {
                    accessible++;
                }
            }
        }

        return accessible;
    }

    private static int boolToInt(boolean value) {
        return value ? 1 : 0;
    }

    public record NeighbourGrid(List<BitSet> grid, int width) {}

}
