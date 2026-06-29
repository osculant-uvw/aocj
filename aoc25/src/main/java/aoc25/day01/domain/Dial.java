package aoc25.day01.domain;

/**
 * A circular dial with values in range [0, size). Rotations wrap within range.
 */
public class Dial {

    public final int SIZE;
    private int value;

    public Dial(int start, int size){
        value = Math.floorMod(start, size);
        SIZE = size;
    }

    public int getValue() {return value;}

    public void rotate(DialDirection direction, int turns) {
        switch (direction) {
            case Right -> value = Math.floorMod(value + turns, SIZE);
            case Left  -> value = Math.floorMod(value - turns, SIZE);
        }
    }

}
