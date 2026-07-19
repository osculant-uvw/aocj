package aoc25.day02.domain;

public record OrderedPair(long start, long end)  {

    public OrderedPair {
        long small = Math.min(start, end);
        long large = Math.max(start, end);
        start = small;
        end = large;
    }

}
