package aoc25.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import aoc25.day02.domain.OrderedPair;
import aoc25.day02.domain.Repdigit;

public class Day02 {

    static final String INPUT_PATH = "inputs/day02.txt";
    static final String TEST_PATH = "src/test/resources/day02.txt";

    public static void main(String[] args) {
        try {
            List<OrderedPair> ranges = parse(Path.of(INPUT_PATH));

            Set<Long> blocks1 = ranges.stream()
                    .flatMap(range -> Repdigit.getSquareRepBlocks(range).stream())
                    .collect(Collectors.toCollection(HashSet::new));

            long sum1 = blocks1.stream()
                    .mapToLong(Long::longValue)
                    .sum();

            System.out.printf("part 1: the sum of the invalid id's is: %d %n", sum1);

            Set<Long> blocks = ranges.stream()
                    .flatMap(range -> Repdigit.getAllRepBlocks(range).stream())
                    .collect(Collectors.toCollection(HashSet::new));

            long sum2 = blocks.stream()
                    .mapToLong(Long::longValue)
                    .sum();

            System.out.printf("part 2: the sum of the invalid id's is: %d %n", sum2);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    static List<OrderedPair> parse(Path path) throws IOException, IllegalArgumentException {
        List<OrderedPair> ranges = new ArrayList<>();

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

            ranges.add(new OrderedPair(Long.parseLong(s1), Long.parseLong(s2)));
            count++;
        }

        return ranges;
    }

}
