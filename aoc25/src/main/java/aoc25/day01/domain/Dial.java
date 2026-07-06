package aoc25.day01.domain;

/**
 * A circular dial with position in range [0, size). Rotations wrap within range.
 */
public class Dial {

    public final int size;

    private int position;
    private int zeroPasses = 0;

    public Dial(int start, int size){
        this.position = Math.floorMod(start, size);
        this.size = size;
    }

    public int getPosition() {return position;}
    public int getZeroPasses() {return zeroPasses;}

    public void rotate(DialDirection direction, int amount) {
        int distanceToFirstZero = switch (direction) {
            case Right -> size - position;
            case Left -> (position == 0) ? size : position;
        };

        if (distanceToFirstZero <= amount) {
            zeroPasses += 1 + (amount - distanceToFirstZero) / size;
        }

        position = switch (direction) {
            case Right -> Math.floorMod(position + amount, size);
            case Left -> Math.floorMod(position - amount, size);
        };
    }

}
