package aoc25.day06;

import aoc25.day06.domain.CephalopodMathWorksheet;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class Day06 {

    private static final String INPUT_PATH = "inputs/day06.txt";

    public static void main(String[] args) {
        try {
            List<String> input = Files.readAllLines(Path.of(INPUT_PATH));
            CephalopodMathWorksheet worksheet = CephalopodMathWorksheet.fromRows(input);

            List<Long> solutions = worksheet.solutions();
            long solutionSum = solutions.stream().mapToLong(Long::longValue).sum();

            System.out.printf("the sum of the solutions is %d", solutionSum);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

}
