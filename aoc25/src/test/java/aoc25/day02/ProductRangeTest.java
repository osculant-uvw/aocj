package aoc25.day02;

import aoc25.day02.domain.ProductRange;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductRangeTest {

    @Test
    public void findSquareStringsFromExampleProductRanges() {
        List<ProductRange> ranges = Arrays.asList(
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

        List<Set<Long>> gather = new ArrayList<>();
        List<Set<Long>> gather2 = new ArrayList<>();
        for (ProductRange range : ranges) {
            gather.add(range.getEnclosedSquareStringIntegers());
            gather2.add(range.getEnclosedKStringIntegers());
        }

        List<Set<Long>> expected = List.of(
                Set.of(11L, 22L),
                Set.of(99L),
                Set.of(1010L),
                Set.of(1188511885L),
                Set.of(222222L),
                Set.of(),
                Set.of(446446L),
                Set.of(38593859L),
                Set.of(),
                Set.of(),
                Set.of()
        );

        List<Set<Long>> expected2 = List.of(
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
        assertEquals(expected2, gather2);
    }

    @Test
    public void getSquareSubRangesFromRangeSpanningMultiplePowersOf10() {
        ProductRange spanningRange = new ProductRange(6666L, 112_000L);

        List<ProductRange> squareRanges = spanningRange.getKStringSubranges(2);

        List<ProductRange> expected = Arrays.asList(
                new ProductRange(6666L, 9999L),
                new ProductRange(100_000L, 112_000L)
        );

        assertEquals(expected, squareRanges);
    }

    @Test
    public void getKSubrangesFromRangeSpanningMultiplePowersOf10() {
        ProductRange spanningRange = new ProductRange(5000L, 5_000_000_000_000L);

        List<ProductRange> k3Subranges = spanningRange.getKStringSubranges(3);
        List<ProductRange> k3Expected = Arrays.asList(
                new ProductRange(100_000L, 999_999L),
                new ProductRange(100_000_000L, 999_999_999L),
                new ProductRange(100_000_000_000L, 999_999_999_999L)
        );
        assertEquals(k3Expected, k3Subranges);

        List<ProductRange> k5Subranges = spanningRange.getKStringSubranges(5);
        List<ProductRange> k5Expected = Arrays.asList(
                new ProductRange(10_000L, 99_999L),
                new ProductRange(1_000_000_000L, 9_999_999_999L)
        );
        assertEquals(k5Expected, k5Subranges);
    }

    @Test
    public void splitStringInto5Parts() {
        String s = "9999999999";

        String[] expected = {"99", "99", "99", "99", "99"};
        assertArrayEquals(expected, ProductRange.splitStringIntoNParts(s, 5));
    }

}
