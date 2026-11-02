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

            CephalopodMathWorksheet worksheet1 = CephalopodMathWorksheet.fromRows(input);
            CephalopodMathWorksheet worksheet2 = CephalopodMathWorksheet.fromRowsVerticalDigits(input);

            List<Long> solutions1 = worksheet1.solutions();
            List<Long> solutions2 = worksheet2.solutions();

            System.out.printf("the sum of the solutions for task 1 are %d %n", solutions1.stream().mapToLong(Long::longValue).sum());
            System.out.printf("the sum of the solutions for task 2 are %d %n", solutions2.stream().mapToLong(Long::longValue).sum());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

}
