package aoc25.day01;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc25.day01.domain.DialDirection;

class Day01Test {

    static final String TEST_PATH = "src/test/inputs/day01test.txt";

    @Test
    void parseTheExampleInput() throws Exception {
        Path path = Path.of(TEST_PATH);

        List<Day01.Instruction> input = Day01.parse(path);

        List<Day01.Instruction> expected = Arrays.asList(
                new Day01.Instruction(DialDirection.Left, 68),
                new Day01.Instruction(DialDirection.Left, 30),
                new Day01.Instruction(DialDirection.Right, 48),
                new Day01.Instruction(DialDirection.Left, 5),
                new Day01.Instruction(DialDirection.Right, 60),
                new Day01.Instruction(DialDirection.Left, 55),
                new Day01.Instruction(DialDirection.Left, 1),
                new Day01.Instruction(DialDirection.Left, 99),
                new Day01.Instruction(DialDirection.Right, 14),
                new Day01.Instruction(DialDirection.Left, 82)
        );

        assertEquals(expected, input);
    }

}
