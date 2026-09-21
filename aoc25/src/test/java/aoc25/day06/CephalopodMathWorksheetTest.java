package aoc25.day06;

import java.io.IOException;
import java.util.List;

import aoc25.day06.domain.CephalopodMathWorksheet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CephalopodMathWorksheetTest {

    static final List<String> ROWS = List.of(
            "123 328  51 64",
            " 45 64  387 23",
            "  6 98  215 314" ,
            "*   +   *   +"
    );

    @Test
    void parseTheExampleInput() throws IOException {
        List<Long> expectedSolutions = List.of(
                33_210L, 490L, 4_243_455L, 401L
        );

        CephalopodMathWorksheet worksheet = CephalopodMathWorksheet.fromRows(ROWS);

        assertEquals(expectedSolutions, worksheet.solutions());
    }

}
