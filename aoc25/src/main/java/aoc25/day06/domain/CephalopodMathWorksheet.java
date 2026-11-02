package aoc25.day06.domain;

import java.util.*;
import java.util.stream.Collectors;

public class CephalopodMathWorksheet {

    final long[][] columns;
    final char[] operations;

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

        return new CephalopodMathWorksheet(numbersGrid, getOperations(rows, width));
    }

    public static CephalopodMathWorksheet fromRowsVerticalDigits(List<String> rows) {
        int length = rows.size() - 2;
        int width = rows.getFirst().length() - 1;

        char[] operations = getOperations(rows, rows.getFirst().trim().split("\\s+").length);
        char[] reversed = new StringBuilder(new String(operations))
                .reverse()
                .toString()
                .toCharArray();

        long[][] numbersGrid = new long[operations.length][3];

        int addPrimaryIdx = 0;
        int addSecondaryIdx = 0;
        for (int colIdx = width; colIdx >= 0; colIdx--) {

            List<Character> characters = new ArrayList<>();
            for (int rowIdx = 0; rowIdx <= length; rowIdx++) {
                characters.add(rows.get(rowIdx).charAt(colIdx));
            }

            String digits = characters.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining())
                    .trim();

            if (!digits.isEmpty()) {
                numbersGrid[addPrimaryIdx][addSecondaryIdx] = Integer.parseInt(digits);
                addSecondaryIdx++;
            } else {
                addPrimaryIdx++;
                addSecondaryIdx = 0;
            }

        }

        return new CephalopodMathWorksheet(numbersGrid, reversed);
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

    static char[] getOperations(List<String> rows, int width) {
        char[] operations = rows.getLast()
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
        return operations;
    }

}
