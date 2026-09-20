package aoc25.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 {

    private static final String INPUT_PATH = "inputs/day06.txt";

    public static void main(String[] args) {
        try {
            Calculations calculations = parse(Path.of(INPUT_PATH));

            List<Long> solutions = new ArrayList<>();
            for (int i = 0; i < calculations.numbersGrid.length; i++) {

                long[] items = calculations.numbersGrid[i];
                char op = calculations.operations[i];

                long calculation = (op == '+') ? 0  : 1;
                for (long num : items) {

                    if (op == '+') {
                        calculation += num;
                    } else if (op == '*') {
                        calculation *= num;
                    }

                }
                solutions.add(calculation);
            }

            long solutionSum = solutions.stream().mapToLong(Long::longValue).sum();
            System.out.printf("the sum of the solutions is %d", solutionSum);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

    public static Calculations parse(Path path) throws IOException, IllegalArgumentException {

        List<String> lines = Files.readAllLines(path);
        int length = lines.size() - 1;
        int width = lines.getFirst().trim().split("\\s+").length;

        long[][] numbersGrid = new long[width][length];
        for (int i = 0; i < length; i++) {

            long[] numbers = Arrays.stream(lines.get(i).trim().split("\\s+"))
                    .mapToLong(Long::parseLong)
                    .toArray();

            if (numbers.length != width) {
                throw new IllegalArgumentException(String.format("line %d is not of grid width %d", i, width));
            }

            for (int j = 0; j < width; j++) {
                numbersGrid[j][i] = numbers[j];
            }

        }

        char[] operations = lines.get(length).trim()
                .replaceAll("\\s+", "")
                .toCharArray();

        for (char op : operations) {
            if (op != '+' && op != '*') {
                throw new IllegalArgumentException("invalid operator encountered: " + op);
            }
        }

        if (operations.length != width) {
            throw new IllegalArgumentException(
                    String.format("line %d is not of grid width %d", operations.length, width)
            );
        }


        return new Calculations(numbersGrid, operations);
    }

    public record Calculations(long[][] numbersGrid, char[] operations) {}

}
