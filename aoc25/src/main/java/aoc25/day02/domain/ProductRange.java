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
        Set<Long> list = new HashSet<>();

        for (long i = start; i <= end; i++) {
            String s = Long.toString(i);

            int size = s.length();
            if (size % 2 == 0) {
                String s1 = s.substring(0, (size / 2));
                String s2 = s.substring(size / 2);

                if (s1.equals(s2)) {
                    list.add(i);
                }
            }

        }

        return list;
    }

}
