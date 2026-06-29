package aoc25.day01;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import aoc25.day01.domain.*;
import org.junit.jupiter.api.Test;


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
            gather.add(dial.getValue());
        }

        List<Integer> expected = Arrays.asList(82, 52, 0, 95, 55, 0, 99, 0, 14, 32);

        assert gather.equals(expected);
    }

}
