package aoc25.common;

public record OrderedPair(long start, long end) implements Comparable<OrderedPair> {

    public OrderedPair(long start, long end) {
        this.start = Math.min(start, end);
        this.end = Math.max(start, end);
    }

    public OrderedPair merge(OrderedPair pair) {
        return new OrderedPair(Math.min(start, pair.start()), Math.max(end, pair.end()));
    }

    public boolean contains(Long value) {
        return start <= value && value <= end();
    }

    public boolean overlaps(OrderedPair pair) {
        return start <= pair.end() && pair.start() <= end;
    }

    @Override
    public int compareTo(OrderedPair other) {
        int result = Long.compare(this.start, other.start());
        if (result != 0) {
            return result ;
        }

        return Long.compare(this.end, other.end());
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }

}
