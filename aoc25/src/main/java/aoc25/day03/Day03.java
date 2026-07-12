package aoc25.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import aoc25.day03.domain.Subsequences;

public class Day03 {

    static final String INPUT_PATH = "inputs/day03.txt";
    static final String TEST_PATH = "src/test/inputs/day03test.txt";

    public static void main(String[] args) {
        try {
            List<String> powerBanks = parse(Path.of(INPUT_PATH));

            int sum = 0;
            for (String bank : powerBanks) {
                int[] digits = bank
                        .chars()
                        .map(Character::getNumericValue)
                        .toArray();

                int[] top2 = Subsequences.maxLexOrderedPair(digits);
                sum += Integer.parseInt(
                        top2[0] + "" + top2[1]  // e.g. 4+6=46
                );
            }
            System.out.printf("the total output joltage is: %d ", sum);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    static List<String> parse(Path path) throws IOException, IllegalArgumentException {
        List<String> powerBanks = new ArrayList<>();

        List<String> lines = Files.readAllLines(path);
        int count = 0;
        for (String line : lines) {
            if (!line.chars().allMatch(Character::isDigit)) {
                throw new IllegalArgumentException(
                        String.format("line [%d] %s is not a digit", count, line)
                );
            }
            powerBanks.add(line);
            count++;
        }

        return powerBanks;
    }

}
