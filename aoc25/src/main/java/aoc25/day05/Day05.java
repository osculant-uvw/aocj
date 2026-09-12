package aoc25.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aoc25.common.OrderedPair;

public class Day05 {

    static final String INPUT_PATH = "inputs/day05.txt";

    public static void main(String[] args) {
        try {
            InputState state = parse(Path.of(INPUT_PATH));

            Set<Long> collect = new HashSet<>();
            for (long value : state.values) {

                for (OrderedPair range : state.ranges) {
                    if (range.start() <= value && value <= range.end()) {
                        collect.add(value);
                    }
                }

            }

            collect.forEach(System.out::println);
            System.out.printf("the number of valid id's is: %d %n", collect.size());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    static InputState parse(Path path) throws IOException {
        List<OrderedPair> ranges = new ArrayList<>();
        List<Long> values = new ArrayList<>();

        int count = 0;
        List<String> lines = Files.readAllLines(path);

        while (!lines.get(count).isEmpty()) {
            String[] parts = lines.get(count).split("-");
            if (parts.length != 2) {
                throw new IllegalArgumentException(
                        String.format("item [%d] does not contain a single '-': %s", count, lines.get(count))
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

        count++; // skip empty line delimiter

        for (; count < lines.size(); count++) {
            String line = lines.get(count);

            if (line.isEmpty()) {
                throw new IllegalArgumentException(String.format("line [%d] is empty", count));
            }

            if (!line.chars().allMatch(Character::isDigit)) {
                throw new IllegalArgumentException(
                        String.format("line [%d] is not a digit %s", count, line)
                );
            }

            values.add(Long.parseLong(line));
        }

        return new InputState(ranges, values);
    }

    record InputState(List<OrderedPair> ranges, List<Long> values) {}

}
