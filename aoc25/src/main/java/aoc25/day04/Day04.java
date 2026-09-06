package aoc25.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import aoc25.day04.domain.CellGrid;

public class Day04 {

    static final String INPUT_PATH = "inputs/day04.txt";
    static final String TEST_PATH = "src/test/inputs/day04test.txt";

    static final int ACCESSIBLE_CELL_MAX_NEIGHBOURS = 4;

    public static void main(String[] args) {
        try {
            final CellGrid grid = parse(Path.of(INPUT_PATH));

            List<Integer> removed = new ArrayList<>();

            int count;
            do {
                count = grid.update();
                removed.add(count);
            } while (count > 0);

            int total = removed.stream().mapToInt(Integer::intValue).sum();

            System.out.printf("first number of items removed: %d %n", removed.getFirst());
            System.out.printf("total number of items removed: %d %n", total);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    static CellGrid parse(Path path) throws IOException {
        final List<String> input = Files.readAllLines(path);
        CellGrid grid = new CellGrid(input.getFirst().length(), ACCESSIBLE_CELL_MAX_NEIGHBOURS);

        for (int row = 0; row < input.size(); row++) {
            String line = input.get(row);
            grid.addRow();

            if (line.length() != grid.width) {
                throw new IllegalArgumentException(
                        String.format(
                                "line [%d] has length %d which does not match the rest of the grid",
                                row,
                                grid.width
                        )
                );
            }

            for (int column = 0; column < grid.width; column++) {
                if (line.charAt(column) == '@') {
                    grid.setCell(row, column);
                }
            }

        }

        return grid;
    }

}
