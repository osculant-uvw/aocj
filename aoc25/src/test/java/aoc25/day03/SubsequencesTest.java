package aoc25.day03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc25.day03.domain.*;

public class SubsequencesTest {

    @Test
    void maxPairForExampleInput() {

        int[] input1 = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1,1, 1};
        int[] input2 = new int[] {8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9};
        int[] input3 = new int[] {2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8};
        int[] input4 = new int[] {8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1};

        assertArrayEquals(new int[] {9, 8}, Subsequences.maxLexOrderedPair(input1));
        assertArrayEquals(new int[] {8, 9}, Subsequences.maxLexOrderedPair(input2));
        assertArrayEquals(new int[] {7, 8}, Subsequences.maxLexOrderedPair(input3));
        assertArrayEquals(new int[] {9, 2}, Subsequences.maxLexOrderedPair(input4));
    }

}
