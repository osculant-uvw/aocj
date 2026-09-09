package aoc25.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import aoc25.day04.domain.CellGrid;

public class Day04 {

    static final String INPUT_PATH = "inputs/day04.txt";
    static final int MAX_NEIGHBOURS = 4;

    public static void main(String[] args) {
        try {
            List<String> input = parse(Path.of(INPUT_PATH));
            CellGrid grid = CellGrid.fromRows(input);

            int count;
            List<Integer> removed = new ArrayList<>();
            do {
                count = grid.update(MAX_NEIGHBOURS);
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

    static List<String> parse(Path path) throws IOException, IllegalArgumentException {
        List<String> input = Files.readAllLines(path);
        int width = input.getFirst().length();

        int count = 0;
        for (String line : input) {
            if (line.length() != width) {
                throw new IllegalArgumentException(
                        String.format(
                                "line [%d] has length %d which does not match the rest of the grid",
                                count,
                                line.length())
                        );
            }
            count++;
        }

        return input;
    }

}
