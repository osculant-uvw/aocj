package aoc25.day06.domain;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CephalopodWorksheetTest {

    static final List<String> ROWS_1 = List.of(
            "123 328  51 64 ",
            " 45 64  387 23 ",
            "  6 98  215 314" ,
            "*   +   *   +  "
    );

    static final List<String> ROWS_2 = List.of(
            "3   235 6     6",
            "967 631 68    9",
            "779  12 282  43",
            "555   2 9935 65",
            "*   +   +    * "
    );

    @Test
    void fromRowsForExampleRows1() {
        List<List<Long>> expectedColumns = List.of(
                List.of(123L, 45L, 6L),
                List.of(328L, 64L, 98L),
                List.of(51L, 387L, 215L),
                List.of(64L,  23L, 314L)
        );

        List<Long> expectedSolutions = List.of(
                33_210L, 490L, 4_243_455L, 401L
        );

        CephalopodWorksheet worksheet = CephalopodWorksheet.fromRows(ROWS_1);

        assertEquals(expectedColumns, worksheet.columns);
        assertEquals(expectedSolutions, worksheet.solutions());
    }

    @Test
    void fromRowsForExampleRows2() {
        List<List<Long>> expectedColumns = List.of(
                List.of(3L, 967L, 779L, 555L),
                List.of(235L, 631L, 12L, 2L),
                List.of(6L, 68L, 282L, 9935L),
                List.of(6L, 9L, 43L, 65L)
        );

        List<Long> expectedSolutions = List.of(
                1_254_232_845L, 880L, 10_291L, 150_930L
        );

        CephalopodWorksheet worksheet = CephalopodWorksheet.fromRows(ROWS_2);

        assertEquals(expectedColumns, worksheet.columns);
        assertEquals(expectedSolutions, worksheet.solutions());
    }

    @Test
    void fromRowsVerticalDigitsForExampleRows1() {
        List<List<Long>> expectedColumns = List.of(
                List.of(4L, 431L, 623L),
                List.of(175L, 581L, 32L),
                List.of(8L, 248L, 369L),
                List.of(356L, 24L, 1L)
        );

        List<Long> expectedSolutions = List.of(
                1058L, 3_253_600L, 625L, 8544L
        );

        CephalopodWorksheet worksheet = CephalopodWorksheet.fromRowsVerticalDigits(ROWS_1);

        assertEquals(expectedColumns, worksheet.columns);
        assertEquals(expectedSolutions, worksheet.solutions());
    }

    @Test
    void fromRowsVerticalDigitsForExampleRows2() {
        List<List<Long>> expectedColumns = List.of(
                List.of(6935L, 46L),
                List.of(5L, 23L, 889L, 6629L),
                List.of(5122L, 331L, 26L),
                List.of(795L, 675L, 3975L)
        );

        List<Long> expectedSolutions = List.of(
                319_010L, 7546L, 5479L, 2_133_084_375L
        );

        CephalopodWorksheet worksheet = CephalopodWorksheet.fromRowsVerticalDigits(ROWS_2);

        assertEquals(expectedColumns, worksheet.columns);
        assertEquals(expectedSolutions, worksheet.solutions());
    }

}
