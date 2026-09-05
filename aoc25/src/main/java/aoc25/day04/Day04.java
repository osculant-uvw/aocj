package aoc25.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

            int accessible = numberOfAccessibleItems(grid);
            System.out.printf("number of accessible items: %s", accessible);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    static CellGrid parse(Path path) throws IOException {
        final List<String> input = Files.readAllLines(path);
        CellGrid grid = new CellGrid(input.getFirst().length(), ACCESSIBLE_CELL_MAX_NEIGHBOURS);

        int count = 0;
        for (String line : input) {

            if (line.length() != grid.width) {
                throw new IllegalArgumentException(
                        String.format(
                                "line [%d] has length %d which does not match the rest of the grid",
                                count,
                                grid.width
                        )
                );
            }

            BitSet bits = new BitSet(grid.width);
            for (int i = 0; i < grid.width; i++) {
                if (line.charAt(i) == '@') {
                    bits.set(i);
                }
            }

            grid.add(bits);
            count++;
        }

        return grid;
    }

    static int numberOfAccessibleItems(CellGrid grid) {
        return grid.update();
    }

}
