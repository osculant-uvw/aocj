package aoc25.day03;

import aoc25.day01.Day01;
import aoc25.day01.domain.DialDirection;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {

    static final String TEST_PATH = "src/test/inputs/day03test.txt";

    @Test
    void parseTheExampleInput() throws Exception {
        Path path = Path.of(TEST_PATH);

        List<String> input = Day03.parse(path);

        List<String> expected = Arrays.asList(
                "987654321111111",
                "811111111111119",
                "234234234234278",
                "818181911112111"
        );

        assertEquals(expected, input);
    }

}
