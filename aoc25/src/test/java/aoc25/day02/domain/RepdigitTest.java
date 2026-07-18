package aoc25.day02.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepdigitTest {

    private static final List<OrderedPair> EXAMPLE_INPUTS = Arrays.asList(
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

    @Test
    public void findSquareRepBlocksForExampleInput() {
        List<Set<Long>> gather = new ArrayList<>();
        for (OrderedPair range : EXAMPLE_INPUTS) {
            gather.add(Repdigit.getSquareRepBlocks(range));
        }

        List<Set<Long>> expected = List.of(
                Set.of(11L, 22L),
                Set.of(99L),
                Set.of(1010L),
                Set.of(1_188_511_885L),
                Set.of(222_222L),
                Set.of(),
                Set.of(446_446L),
                Set.of(38_593_859L),
                Set.of(),
                Set.of(),
                Set.of()
        );

        assertEquals(expected, gather);
    }

    @Test
    public void findAllRepBlocksForExampleInput() {
        List<Set<Long>> gather = new ArrayList<>();
        for (OrderedPair range : EXAMPLE_INPUTS) {
            gather.add(Repdigit.getAllRepBlocks(range));
        }

        List<Set<Long>> expected = List.of(
                Set.of(11L, 22L),
                Set.of(99L, 111L),
                Set.of(999L, 1010L),
                Set.of(1188511885L),
                Set.of(222222L),
                Set.of(),
                Set.of(446446L),
                Set.of(38593859L),
                Set.of(565656L),
                Set.of(824824824L),
                Set.of(2121212121L)
        );

        assertEquals(expected, gather);
    }

    @Test
    public void getSquareSubrangesForRangeSpanningMultiplePowersOf10() {
        OrderedPair range = new OrderedPair(6666L, 112_000L);

        List<OrderedPair> expected = Arrays.asList(
                new OrderedPair(6666L, 9999L),
                new OrderedPair(100_000L, 112_000L)
        );

        assertEquals(expected, Repdigit.getCandidateSubranges(range, 2));
    }

    @Test
    public void getCubicSubrangesForRangeSpanningMultiplePowersOf10() {
        OrderedPair range = new OrderedPair(5000L, 5_000_000_000_000L);

        List<OrderedPair> expected = Arrays.asList(
                new OrderedPair(100_000L, 999_999L),
                new OrderedPair(100_000_000L, 999_999_999L),
                new OrderedPair(100_000_000_000L, 999_999_999_999L)
        );

        assertEquals(expected, Repdigit.getCandidateSubranges(range, 3));
    }

    @Test
    public void getQuinticSubrangesForRangeSpanningMultiplePowersOf10() {
        OrderedPair range = new OrderedPair(5000L, 5_000_000_000_000L);

        List<OrderedPair> expected = Arrays.asList(
                new OrderedPair(10_000L, 99_999L),
                new OrderedPair(1_000_000_000L, 9_999_999_999L)
        );

        assertEquals(expected, Repdigit.getCandidateSubranges(range, 5));
    }

    @Test
    public void getSubrangesForANegativeRange() {
        OrderedPair range = new OrderedPair(-112_000L, -6666L);

        // if the argument is less than zero, then the result of log10 is NaN
        // when cast to an int NaN takes the value of 0, thus we expect an empty list
        List<OrderedPair> expected = List.of();

        assertEquals(expected, Repdigit.getCandidateSubranges(range, 2));
    }

    @Test
    public void splitStringInto5Parts() {
        String s = Long.toString(9_999_999_999L);
        String[] expected = {"99", "99", "99", "99", "99"};
        assertArrayEquals(expected, Repdigit.divideString(s, 5));
    }

}
