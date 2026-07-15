package aoc25.day02.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Stores an immutable integer range. Supplies methods to find string patterns on elements within the range.
 */
public record ProductRange(long start, long end) {

    public Set<Long> getEnclosedSquareStringIntegers() {
        Set<Long> set = new HashSet<>();

        for (ProductRange range : getSquareSubRanges()) {

            for (long i = range.start; i <= range.end; i++) {
                String s = Long.toString(i);
                String s1 = s.substring(0, (s.length() / 2));
                String s2 = s.substring(s.length() / 2);
                if (s1.equals(s2)) {
                    set.add(i);
                }
            }

        }

        return set;
    }

    public List<ProductRange> getSquareSubRanges() {
        List<ProductRange> ranges = new ArrayList<>();

        int minPower = (int) Math.log10(start);
        int maxPower = (int) Math.log10(end);

        for (int p = minPower; p <= maxPower; p++) {
            if (p % 2 != 0) {
                long newStart = (long) Math.max(start, Math.pow(10, p));
                long newEnd = (long) Math.min(end, Math.pow(10, p + 1) - 1);
                ranges.add(new ProductRange(newStart, newEnd));
            }
        }

        return ranges;
    }

}
