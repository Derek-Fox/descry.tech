package algosrc;

public enum Direction {

    North(0,  1),
    South(0, -1),
    East( 1, 0),
    West(-1, 0);

    public int X;
    public int Y;

    Direction(int x, int y) {
        X = x;
        Y = y;
    }
}
