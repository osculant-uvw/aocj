package aoc25.day03.domain;

/**
 * Static class with methods for forming ordered subsequences from finite sequences.
 */
public final class Subsequences {

    private Subsequences() {}

    /**
     * Returns the lexicographically largest ordered pair of integer digits.
     * @param elements array with integer digits, expected to be in the range [0, 9] but which are unchecked.
     * @return an array containing the selected digits in order.
     */
    public static int[] maxLexOrderedPair(int[] elements) {
        int first = 0;
        int second = 0;

        for (int e : elements) {
            if (first < second) {
                first = second;
                second = e;
            } else if (second < e) {
                second = e;
            }
        }

        return new int[] {first, second};
    }

}
