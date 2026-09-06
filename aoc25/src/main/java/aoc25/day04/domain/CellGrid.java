package aoc25.day04.domain;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class CellGrid extends AbstractList<BitSet> {
    private final List<BitSet> rows = new ArrayList<>();

    public final int width;
    public final int accessibleCellMaxNeighbours;

    public CellGrid(int width, int accessibleCellMaxNeighbours) {
        this.width = width;
        this.accessibleCellMaxNeighbours = accessibleCellMaxNeighbours;
    }

    @Override
    public BitSet get(int index) {
        return rows.get(index);
    }

    @Override
    public int size() {
        return rows.size();
    }

    @Override
    public boolean add(BitSet bits) {
        if (bits.length() > width) {
            throw new IllegalArgumentException("Row exceeds grid width");
        }
        return rows.add(bits);
    }

    /**
     * Atomically updates the grid removing cells that are accessible based on their neighbour count
     * in the current global state.
     * @return the number of cells removed.
     */
    public int update() {
        int removed = 0;
        for (Cell pair : locateAccessibleCells()) {
            rows.get(pair.x).clear(pair.y);
            removed++;
        }

        return removed;
    }

    private List<Cell> locateAccessibleCells() {
        List<Cell> accessibleCells = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {
            BitSet top = i > 0 ? rows.get(i - 1) : new BitSet();
            BitSet current = rows.get(i);
            BitSet bottom = i + 1 < rows.size() ? rows.get(i + 1) : new BitSet();

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

                int adjacent = boolToInt(topLeft)
                        + boolToInt(topMiddle)
                        + boolToInt(topRight)
                        + boolToInt(left)
                        + boolToInt(right)
                        + boolToInt(bottomLeft)
                        + boolToInt(bottomMiddle)
                        + boolToInt(bottomRight);

                if (adjacent < accessibleCellMaxNeighbours) {
                    accessibleCells.add(new Cell(i, j));
                }
            }
        }

        return accessibleCells;
    }

    private static int boolToInt(boolean value) { return value ? 1 : 0; }

    private record Cell(int x, int y) {};

}

