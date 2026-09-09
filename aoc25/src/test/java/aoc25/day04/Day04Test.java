package aoc25.day04;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {

    static final String TEST_PATH = "src/test/inputs/day04test.txt";

    @Test
    void parseTheExampleInput() throws Exception {
        List<String> expected = List.of(
                "..@@.@@@@.",
                "@@@.@.@.@@",
                "@@@@@.@.@@",
                "@.@@@@..@.",
                "@@.@@@@.@@",
                ".@@@@@@@.@",
                ".@.@.@.@@@",
                "@.@@@.@@@@",
                ".@@@@@@@@.",
                "@.@.@@@.@."
        );

        Path path = Path.of(TEST_PATH);
        List<String> result = Day04.parse(path);

        assertEquals(expected.size(), result.size());
        assertEquals(expected, result);
    }

}
