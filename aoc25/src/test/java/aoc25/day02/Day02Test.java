package aoc25.day02;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc25.day02.domain.OrderedPair;

public class Day02Test {

    static final String TEST_PATH = "src/test/inputs/day02test.txt";

    @Test
    void parseTheExampleInput() throws Exception {
        Path path = Paths.get(TEST_PATH);

        List<OrderedPair> input = Day02.parse(path);

        List<OrderedPair> expected = Arrays.asList(
                new OrderedPair(11L, 22L),
                new OrderedPair(95L, 115L),
                new OrderedPair(998L, 1012L),
                new OrderedPair(1_188_511_880L, 1_188_511_890L),
                new OrderedPair(222_220L, 222_224L),
                new OrderedPair(1_698_522L, 1_698_528L),
                new OrderedPair(446_443L, 446_449L),
                new OrderedPair(38_593_856L, 38_593_862L),
                new OrderedPair(565_653L, 565_659L),
                new OrderedPair(824_824_821L, 824_824_827L),
                new OrderedPair(2_121_212_118L, 212_1212_124L)
        );

        assertEquals(expected, input);
    }

}
