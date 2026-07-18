package aoc25.day02.domain;

import aoc25.day02.Day02;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Stores an immutable integer range. Supplies methods to find string patterns on elements within the range.
 */
public record ProductRange(long start, long end) {

    public static Set<Integer> K_PRIMES = Set.of(
            2, 3, 5, 7, 11, 13, 17, 19
    );

    public Set<Long> getEnclosedSquareStringIntegers() {
        Set<Long> set = new HashSet<>();

        for (ProductRange range : getKStringSubranges(2)) {

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

    public Set<Long> getEnclosedKStringIntegers() {
        Set<Long> set = new HashSet<>();

        for (int prime : K_PRIMES) {

            for (ProductRange range : getKStringSubranges(prime)) {

                for (long i = range.start; i <= range.end; i++) {
                    String[] strings = splitStringIntoNParts(Long.toString(i), prime);

                    boolean match = true;
                    String s0 = strings[0];
                    int index = 1;
                    while (match && index < strings.length) {
                        match = s0.equals(strings[index]);
                        index++;
                    }

                    if (match) {
                        set.add(i);
                    }
                }

            }

        }

        return set;
    }

    public List<ProductRange> getKStringSubranges(int k) {
        List<ProductRange> kSubranges = new ArrayList<>();

        int minPower = (int) Math.log10(start);
        int maxPower = (int) Math.log10(end);

        for (int p = minPower; p <= maxPower; p++) {
            if ( (p+1) % k == 0) { // doesn't generalise to k
                long newStart = (long) Math.max(start, Math.pow(10, p));
                long newEnd = (long) Math.min(end, Math.pow(10, p + 1) - 1);
                kSubranges.add(new ProductRange(newStart, newEnd));
            }
        }

        return kSubranges;
    }

    public static String[] splitStringIntoNParts(String s, int n) {
        String[] parts = new String[n];

        // for our use we are assured that the string is divisible by n
        int partLength = s.length() / n;

        for (int i = 0; i < n; i++) {
            parts[i] = s.substring(i*partLength, i*partLength+partLength);
        }

        return parts;
    }

}
