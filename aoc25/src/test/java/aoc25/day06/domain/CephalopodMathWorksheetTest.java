package aoc25.day06.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CephalopodMathWorksheetTest {

    static final List<String> ROWS = List.of(
            "123 328  51 64 ",
            " 45 64  387 23 ",
            "  6 98  215 314" ,
            "*   +   *   +  "
    );

    @Test
    void fromRowsForExampleInput() {
        long[][] expectedColumns = {
                {123L, 45L, 6L},
                {328L, 64L, 98L},
                {51L, 387L, 215L},
                {64L,  23L, 314L}
        };

        List<Long> expectedSolutions = List.of(
                33_210L, 490L, 4_243_455L, 401L
        );

        CephalopodMathWorksheet worksheet = CephalopodMathWorksheet.fromRows(ROWS);

        assertArrayEquals(expectedColumns, worksheet.columns);
        assertEquals(expectedSolutions, worksheet.solutions());
    }

    @Test
    void fromRowsVerticalDigitsForExampleInput() {
        long[][] expectedColumns = {
                {4L, 431L, 623L},
                {175L, 581L, 32L},
                {8L, 248L, 369L},
                {356L, 24L, 1L}
        };

        List<Long> expectedSolutions = List.of(
                1058L, 3_253_600L, 625L, 8544L
        );

        CephalopodMathWorksheet worksheet = CephalopodMathWorksheet.fromRowsVerticalDigits(ROWS);

        assertArrayEquals(expectedColumns, worksheet.columns);
        assertEquals(expectedSolutions, worksheet.solutions());
    }

}
