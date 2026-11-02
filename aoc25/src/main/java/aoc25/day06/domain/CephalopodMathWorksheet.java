package aoc25.day06.domain;

import java.util.*;
import java.util.stream.Collectors;

public class CephalopodMathWorksheet {

    final List<List<Long>> columns;
    final char[] operations;

    private CephalopodMathWorksheet(List<List<Long>> columns, char[] operations) {
        this.columns = columns;
        this.operations = operations;
    }

    public static CephalopodMathWorksheet fromRows(List<String> rows) {
        int length = rows.size() - 1;
        int width = rows.getFirst().trim().split("\\s+").length;

        List<List<Long>> columns = new ArrayList<>();
        for (int cap = 0; cap < width; cap++) {
            columns.add(new ArrayList<>());
        }

        for (int i = 0; i < length; i++) {

            long[] numbers = Arrays.stream(rows.get(i).trim().split("\\s+"))
                    .mapToLong(Long::parseLong)
                    .toArray();

            if (numbers.length != width) {
                throw new IllegalArgumentException(String.format("line %d is not of grid width %d", i, width));
            }

            for (int j = 0; j < width; j++) {
                columns.get(j).add(numbers[j]);
            }

        }

        return new CephalopodMathWorksheet(columns, getOperations(rows, width));
    }

    public static CephalopodMathWorksheet fromRowsVerticalDigits(List<String> rows) {
        int length = rows.size() - 2;
        int width = rows.getFirst().length() - 1;

        char[] operations = getOperations(rows, rows.getFirst().trim().split("\\s+").length);
        char[] reversed = new StringBuilder(new String(operations))
                .reverse()
                .toString()
                .toCharArray();

        List<List<Long>> columns = new ArrayList<>();
        for (int cap = 0; cap < reversed.length; cap++) {
            columns.add(new ArrayList<>());
        }

        int addPrimaryIdx = 0;
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
                columns.get(addPrimaryIdx).add(Long.parseLong(digits));
            } else {
                addPrimaryIdx++;
            }

        }

        return new CephalopodMathWorksheet(columns, reversed);
    }

    public List<Long> solutions() {
        List<Long> solutions = new ArrayList<>();

        for (int i = 0; i < columns.size(); i++) {

            List<Long> items = columns.get(i);
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
