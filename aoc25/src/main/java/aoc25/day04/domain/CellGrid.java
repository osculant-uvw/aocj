package aoc25.day04.domain;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class CellGrid {
    private final List<BitSet> grid;
    private final int width;

    private CellGrid(List<BitSet> grid, int width) {
        this.grid = grid;
        this.width = width;
    }

    public static CellGrid fromRows(List<String> rows) {
        List<BitSet> grid = new ArrayList<>();
        int width = 0;

        for (String row : rows) {
            width = Math.max(row.length(), width);

            BitSet bits = new BitSet();
            for (int i = 0; i < row.length(); i++) {
                if (row.charAt(i) == '@') {
                    bits.set(i);
                } else if (row.charAt(i) != '.') {
                    throw new IllegalArgumentException(
                            String.format("unsupported cell representation %c at position %d", row.charAt(i), i)
                    );
                }
            }
            grid.add(bits);

        }

        return new CellGrid(grid, width);
    }

    public List<String> toRows() {
        List<String> rows = new ArrayList<>();

        for (BitSet bits : grid) {
            StringBuilder row = new StringBuilder();

            for (int i = 0; i < width; i++) {
                if (bits.get(i)) {
                   row.append('@');
                } else {
                    row.append('.');
                }
            }

            rows.add(row.toString());
        }

        return rows;
    }

    /**
     * Atomically updates the grid removing cells that are accessible based on their neighbour count
     * in the current global state.
     * @return the number of cells removed.
     */
    public int update(int maxNeighbours) {
        int removed = 0;
        for (Cell pair : getRemovableCells(maxNeighbours)) {
            grid.get(pair.x).clear(pair.y);
            removed++;
        }

        return removed;
    }

    private List<Cell> getRemovableCells(int maxNeighbours) {
        List<Cell> accessibleCells = new ArrayList<>();

        for (int i = 0; i < grid.size(); i++) {
            BitSet top = i > 0 ? grid.get(i - 1) : new BitSet();
            BitSet current = grid.get(i);
            BitSet bottom = i + 1 < grid.size() ? grid.get(i + 1) : new BitSet();

            for (int j = 0; j < width; j++) {
                if (!current.get(j)) {
                    continue;
                }

                boolean topLeft = j > 0 && top.get(j - 1);
                boolean topMiddle = top.get(j);
                boolean topRight = top.get(j + 1);

                boolean left = j > 0 && current.get(j - 1);
                boolean right = current.get(j + 1);

                boolean bottomLeft = j > 0 && bottom.get(j - 1);
                boolean bottomMiddle = bottom.get(j);
                boolean bottomRight = bottom.get(j + 1);

                int neighbours = boolToInt(topLeft)
                        + boolToInt(topMiddle)
                        + boolToInt(topRight)
                        + boolToInt(left)
                        + boolToInt(right)
                        + boolToInt(bottomLeft)
                        + boolToInt(bottomMiddle)
                        + boolToInt(bottomRight);

                if (neighbours < maxNeighbours) {
                    accessibleCells.add(new Cell(i, j));
                }
            }
        }

        return accessibleCells;
    }

    private static int boolToInt(boolean value) { return value ? 1 : 0; }

    private record Cell(int x, int y) {};

}
