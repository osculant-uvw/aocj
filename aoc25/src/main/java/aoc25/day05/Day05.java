package aoc25.day05;

import aoc25.common.OrderedPair;
import aoc25.day05.domain.InventoryManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Day05 {

    static final String INPUT_PATH = "inputs/day05.txt";

    public static void main(String[] args) {
        try {
            Inventory state = parse(Path.of(INPUT_PATH));
            InventoryManager manager = new InventoryManager(state.ids(), state.idRanges());

            long size = manager.idRanges().stream()
                    .mapToLong(a -> a.end() - a.start() + 1)
                    .sum();

            System.out.printf("the number of valid id's from values is: %d %n", manager.idsInRanges().size());
            System.out.printf("the number of valid id's from ranges is: %d %n", size);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    static Inventory parse(Path path) throws IOException {
        Set<Long> ids = new HashSet<>();
        Set<OrderedPair> idRanges = new HashSet<>();

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

            idRanges.add(new OrderedPair(Long.parseLong(s1), Long.parseLong(s2)));
            count++;
        }

        count++; // skip empty line delimiter

        for (; count < lines.size(); count++) {
            String line = lines.get(count);

            if (line.isEmpty()) {
                throw new IllegalArgumentException(String.format("line [%d] is empty", count));
            }

            if (!line.chars().allMatch(Character::isDigit)) {
                throw new IllegalArgumentException(String.format("line [%d] is not a digit %s", count, line));
            }

            ids.add(Long.parseLong(line));
        }

        return new Inventory(ids, idRanges);
    }

    record Inventory(Set<Long> ids, Set<OrderedPair> idRanges) {}

}
