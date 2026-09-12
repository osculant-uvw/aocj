package aoc25.common;

public record OrderedPair(long start, long end)  {

    public OrderedPair {
        long small = Math.min(start, end);
        long large = Math.max(start, end);
        start = small;
        end = large;
    }

}
