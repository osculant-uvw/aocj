package aoc25.day02;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc25.day02.domain.*;

public class Day02Test {

    static final String TEST_PATH = "src/test/inputs/day02test.txt";

    @Test
    void parseTheExampleInput() throws Exception {
        Path path = Paths.get(TEST_PATH);

        List<ProductRange> input = Day02.parse(path);

        List<ProductRange> expected = Arrays.asList(
                new ProductRange(11, 22),
                new ProductRange(95, 115),
                new ProductRange(998, 1012),
                new ProductRange(1188511880, 1188511890),
                new ProductRange(222220, 222224),
                new ProductRange(1698522, 1698528),
                new ProductRange(446443, 446449),
                new ProductRange(38593856, 38593862),
                new ProductRange(565653, 565659),
                new ProductRange(824824821, 824824827),
                new ProductRange(2121212118, 2121212124)
        );

        assertEquals(expected, input);
    }

}
