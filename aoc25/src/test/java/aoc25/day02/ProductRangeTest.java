package aoc25.day02;

import aoc25.day02.domain.ProductRange;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        List<List<Long>> gather = new ArrayList<>();
        for (ProductRange range : ranges) {
            gather.add(range.getEnclosedSquareStringIntegers());
        }

        List<List<Long>> expected = List.of(
                List.of(11L, 22L),
                List.of(99L),
                List.of(1010L),
                List.of(1188511885L),
                List.of(222222L),
                List.of(),
                List.of(446446L),
                List.of(38593859L),
                List.of(),
                List.of(),
                List.of()
        );

        assertEquals(expected, gather);
    }

}
