package aoc25.day02.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Static class with methods for working with repeated blocks of digits within Longs.
 */
public class Repdigit {

    private Repdigit() {}

    private static final int[] PRIMES_UP_TO_MAX_LONG_DIGITS  = {
            2, 3, 5, 7, 11, 13, 17, 19
    };

    /**
     * @param range positive ordered pair.
     * @return a set containing all elements in the range [a, b],
     * who form Longs that have square repeated blocks of digits e.g. 121,121 is 121 repeated twice.
     */
    public static Set<Long> getSquareRepBlocks(OrderedPair range) {
        Set<Long> blocks = new HashSet<>();

        for (OrderedPair subrange : getCandidateSubranges(range, 2)) {

            for (long e = subrange.start(); e <= subrange.end(); e++) {
                String[] strings = divideString(Long.toString(e), 2);
                if (strings[0].equals(strings[1])) {
                    blocks.add(e);
                }
            }

        }

        return blocks;
    }

    /**
     * @param range positive ordered pair.
     * @return a set containing all elements in the range [a, b],
     * who form Longs that have some sequence of digits repeated at least twice e.g. 121,212 is 12 repeated three times.
     */
    public static Set<Long> getAllRepBlocks(OrderedPair range) {
        Set<Long> blocks = new HashSet<>();

        for (int p : PRIMES_UP_TO_MAX_LONG_DIGITS) {

            for (OrderedPair subrange : getCandidateSubranges(range, p)) {

                for (long e = subrange.start(); e <= subrange.end(); e++) {
                    String[] strings = divideString(Long.toString(e), p);

                    boolean match = true;
                    String s0 = strings[0];
                    int index = 1;
                    while (match && index < strings.length) {
                        match = s0.equals(strings[index]);
                        index++;
                    }

                    if (match) {
                        blocks.add(e);
                    }
                }

            }

        }

        return blocks;
    }

    static List<OrderedPair> getCandidateSubranges(OrderedPair range, int k) {
        List<OrderedPair> subranges = new ArrayList<>();

        int minExponent = (int) Math.log10(range.start());
        int maxExponent = (int) Math.log10(range.end());

        for (int p = minExponent; p <= maxExponent; p++) {
            if ( (p + 1) % k == 0) {
                long start = (long) Math.max(range.start(), Math.pow(10, p));
                long end = (long) Math.min(range.end(), Math.pow(10, p + 1) - 1);
                subranges.add(new OrderedPair(start, end));
            }
        }

        return subranges;
    }

    static String[] divideString(String s, int k) {
        String[] parts = new String[k];

        // use within this static class ensures that the string is divisible by k
        int partLength = s.length() / k;
        for (int i = 0; i < k; i++) {
            parts[i] = s.substring(i * partLength, (i * partLength) + partLength);
        }

        return parts;
    }

}
