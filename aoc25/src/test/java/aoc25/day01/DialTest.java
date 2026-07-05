package aoc25.day01;

import java.util.Arrays;
import java.util.List;

import aoc25.day01.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DialTest {

    @Test
    void rotateByExampleInstructions() {
        Dial dial = new Dial(Day01.DIAL_START, Day01.DIAL_SIZE);

        List<Day01.Instruction> instructions = Arrays.asList(
                new Day01.Instruction(DialDirection.Left, 68),
                new Day01.Instruction(DialDirection.Left, 30),
                new Day01.Instruction(DialDirection.Right, 48),
                new Day01.Instruction(DialDirection.Left, 5),
                new Day01.Instruction(DialDirection.Right, 60),
                new Day01.Instruction(DialDirection.Left, 55),
                new Day01.Instruction(DialDirection.Left, 1),
                new Day01.Instruction(DialDirection.Left, 99),
                new Day01.Instruction(DialDirection.Right, 14),
                new Day01.Instruction(DialDirection.Left, 82)
        );

        List<Integer> gather = new java.util.ArrayList<>();
        for (Day01.Instruction instr: instructions) {
            dial.rotate(instr.direction(), instr.number());
            gather.add(dial.getPosition());
        }

        List<Integer> expected = Arrays.asList(82, 52, 0, 95, 55, 0, 99, 0, 14, 32);

        assert gather.equals(expected);
        assert dial.getZeroPasses() == 6;
    }

    @Test
    void rotateRightAndLeftFromRightSkewedStart() {
        Dial dial_1 = new Dial(75, Day01.DIAL_SIZE);
        Dial dial_2 = new Dial(75, Day01.DIAL_SIZE);

        dial_1.rotate(DialDirection.Right, 250);
        dial_2.rotate(DialDirection.Left, 250);

        // 250 mod 100 = half-turn
        // half-turns left and right will meet at the same position
        assert dial_1.getPosition() == 25;
        assert dial_2.getPosition() == 25;

        // start position is skewed towards the rhs
        // 75 + 250 = 325, quotient 100 = 3
        // 75 - 250 = -175, quotient 100 = -2, take abs
        assert dial_1.getZeroPasses() == 3;
        assert dial_2.getZeroPasses() == 2;
    }

    @Test
    void rotateRightAndLeftOntoZero() {
        Dial dial_1 = new Dial(75, Day01.DIAL_SIZE);
        Dial dial_2 = new Dial(75, Day01.DIAL_SIZE);

        // rotate onto zero
        dial_1.rotate(DialDirection.Right, 25);
        dial_2.rotate(DialDirection.Left, 75);

        assertEquals(1, dial_1.getZeroPasses());
        assertEquals(1, dial_2.getZeroPasses());
    }

    @Test
    void rotateRightAndLeftFromZero() {
        Dial dial_1 = new Dial(0, Day01.DIAL_SIZE);
        Dial dial_2 = new Dial(0, Day01.DIAL_SIZE);

        dial_1.rotate(DialDirection.Right, 50);
        dial_2.rotate(DialDirection.Left, 50);

        // rotations from zero do not recontribute to count
        assertEquals(0, dial_1.getZeroPasses());
        assertEquals(0, dial_2.getZeroPasses());
    }

}
