package aoc25.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import aoc25.day02.domain.ProductRange;

public class Day02 {

    static final String INPUT_PATH = "inputs/day02.txt";

    public static void main(String[] args) {
        try {
            List<ProductRange> ranges = parse(Path.of(INPUT_PATH));

            long sum = 0;
            for (ProductRange range : ranges) {
                sum += range.getEnclosedSquareStringIntegers()
                        .stream()
                        .mapToLong(Long::longValue)
                        .sum();
            }
            System.out.printf("the sum of the invalid id's is: %d", sum);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    static List<ProductRange> parse(Path path) throws IOException, IllegalArgumentException {
        List<ProductRange> ranges = new ArrayList<>();

        final List<String> items = List.of(Files.readString(path).trim().split(","));
        int count = 0;
        for (String item : items) {

            final String[] parts = item.split("-");
            if (parts.length != 2) {
                throw new IllegalArgumentException(
                        String.format("item [%d] does not contain '-': %s", count, item)
                );
            }

            final String s1 = parts[0];
            final String s2 = parts[1];
            if (!s1.chars().allMatch(Character::isDigit) || !s2.chars().allMatch(Character::isDigit)) {
                throw new IllegalArgumentException(
                        String.format("item [%d] substring %s or %s is not a digit", count, s1, s2)
                );
            }

            ranges.add(new ProductRange(Long.parseLong(s1), Long.parseLong(s2)));
            count++;
        }

        return ranges;
    }

}
