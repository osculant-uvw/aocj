package aoc25.day06.domain;

import aoc25.day04.domain.CellGrid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CephalopodMathWorksheet {

    private final long[][] columns;
    private final char[] operations;

    private CephalopodMathWorksheet(long[][] columns, char[] operations) {
        this.columns = columns;
        this.operations = operations;
    }

    public static CephalopodMathWorksheet fromRows(List<String> rows) {
        int length = rows.size() - 1;
        int width = rows.getFirst().trim().split("\\s+").length;

        long[][] numbersGrid = new long[width][length];
        for (int i = 0; i < length; i++) {

            long[] numbers = Arrays.stream(rows.get(i).trim().split("\\s+"))
                    .mapToLong(Long::parseLong)
                    .toArray();

            if (numbers.length != width) {
                throw new IllegalArgumentException(String.format("line %d is not of grid width %d", i, width));
            }

            for (int j = 0; j < width; j++) {
                numbersGrid[j][i] = numbers[j];
            }

        }

        char[] operations = rows.get(length).trim()
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

        return new CephalopodMathWorksheet(numbersGrid, operations);
    }

    // TODO: implement part 2
    public static CephalopodMathWorksheet fromRowsVertifcalDigits(List<String> rows) {
        return new CephalopodMathWorksheet(new long[0][0], new char[0]);
    }

    public List<Long> solutions() {
        List<Long> solutions = new ArrayList<>();

        for (int i = 0; i < columns.length; i++) {

            long[] items = columns[i];
            char op = operations[i];

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

        return solutions;
    }

}
