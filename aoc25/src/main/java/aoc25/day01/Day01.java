package aoc25.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import aoc25.day01.domain.*;

public class Day01 {

    static final String INPUT_PATH = "src/test/inputs/day01test.txt";
    static final int DIAL_START = 50;
    static final int DIAL_SIZE = 100;

    public static void main(String[] args) {
        try {
            List<Instruction> instructions = parse(Path.of(INPUT_PATH));

            int zeroOccurences = 0;
            Dial dial = new Dial(DIAL_START, DIAL_SIZE);
            for (Instruction instr : instructions) {
                dial.rotate(instr.direction, instr.number);
                if (dial.getValue() == 0) {
                    zeroOccurences++;
                }
            }
            System.out.printf("number of times the dial points to 0: %s  %n", zeroOccurences);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    static List<Instruction> parse(Path path) throws IOException, IllegalArgumentException {
        List<Instruction> instructions = new ArrayList<>();

        List<String> lines = Files.readAllLines(path);
        int count = 0;
        for (String line: lines) {

            final String s = line.substring(1);
            if (!s.chars().allMatch(Character::isDigit)) {
                throw new IllegalArgumentException(
                        String.format("line [%d] substring %s is not a digit", count, s)
                );
            }
            final int amount = Integer.parseInt(s);

            instructions.add(
                    switch (line.charAt(0)) {
                        case 'R' -> new Instruction(DialDirection.Right, amount);
                        case 'L' -> new Instruction(DialDirection.Left, amount);
                        default -> throw new IllegalArgumentException(
                                String.format("line [%d] substring %s is not a valid direction", count, line.charAt(0))
                        );
                    }
            );
            count++;
        }

        return instructions;
    }

    record Instruction(DialDirection direction, int number) {}

}
