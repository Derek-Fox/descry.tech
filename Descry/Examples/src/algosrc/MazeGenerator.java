package algosrc;

import descry.Descry;
import descry.VisualAlgorithm;
import descry.internal.VisualDebugger;
import descry.utility.Mathf;
import descry.utility.Random;

import java.util.Stack;

public class MazeGenerator implements VisualAlgorithm {

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

    private VisualDebugger _graphics;
    private final int _gridSizeX;
    private final int _gridSizeY;
    private float _boardSizeX;
    private float _boardSizeY;
    private float _boardLocalCenterX;
    private float _boardLocalCenterY;
    private float _cellSizeX;
    private float _cellSizeY;
    private float _cellLocalCenterX;
    private float _cellLocalCenterY;

    public static void main(String[] args) {
        Descry.visualize(new MazeGenerator(18, 12));
    }

    public MazeGenerator(int sizeX, int sizeY) {
        _gridSizeX = sizeX;
        _gridSizeY = sizeY;
    }

    @Override
    public void run(VisualDebugger graphics) {

        _graphics = graphics;
        _boardSizeX = graphics.getSizeX() * 0.8f;
        _boardSizeY = graphics.getSizeY() * 0.8f;
        _boardLocalCenterX = _boardSizeX * 0.5f;
        _boardLocalCenterY = _boardSizeY * 0.5f;
        _cellSizeX = _boardSizeX / _gridSizeX;
        _cellSizeY = _boardSizeY / _gridSizeY;
        _cellLocalCenterX = _cellSizeX * 0.5f;
        _cellLocalCenterY = _cellSizeY * 0.5f;

        boolean[][][] paths = generatePaths(_gridSizeX, _gridSizeY);
    }

    private void renderFrame(boolean[][][] maze, boolean[][] visited, int i, int j, Stack<Integer> stackI, Stack<Integer> stackJ) {

        _graphics.beginFrame();
        _graphics.background(200);
        _graphics.translate(
                _graphics.getSizeX() * 0.5f - _boardLocalCenterX,
                _graphics.getSizeY() * 0.5f - _boardLocalCenterY
        );

        if (i < 0 || j < 0) {
            _graphics.noStroke();
        } else {
            _graphics.strokeWeight(1f);
            _graphics.strokeColor(200);
        }

        for (int x = 0; x < _gridSizeX; ++x) {
            for (int y = 0; y < _gridSizeY; ++y) {

                if (x == i && y == j) {
                    _graphics.fillColor(0, 255, 0);
                } else if (visited[x][y]) {
                    _graphics.fillColor(255);
                } else {
                    _graphics.fillColor(220);
                }

                _graphics.rectangle(x * _cellSizeX, y * _cellSizeY, _cellSizeX, _cellSizeY);
            }
        }

        for (int x = 0; x < _gridSizeX; ++x) {
            for (int y = 0; y < _gridSizeY; ++y) {

                if (x == i && y == j) {
                    _graphics.fillColor(0, 255, 0);
                } else if (visited[x][y]) {
                    _graphics.fillColor(255);
                } else {
                    _graphics.fillColor(220);
                }

                _graphics.rectangle(x * _cellSizeX, y * _cellSizeY, _cellSizeX, _cellSizeY);
            }
        }

        int pathLength = stackI.size();
        _graphics.fillColor(220, 220, 255);
        for (int p = 0; p < pathLength; ++p) {
            int x = stackI.get(p);
            int y = stackJ.get(p);
            _graphics.rectangle(x * _cellSizeX, y * _cellSizeY, _cellSizeX, _cellSizeY);
        }

        _graphics.strokeColor(0);
        for (int x = 0; x < _gridSizeX; ++x) {
            for (int y = 0; y < _gridSizeY; ++y) {

                float cellCenterX = x * _cellSizeX + _cellLocalCenterX;
                float cellCenterY = y * _cellSizeY + _cellLocalCenterY;

                if (visited[x][y]) {
                    _graphics.strokeWeight(3f);
                } else if (i >= 0 && j >= 0) {
                    _graphics.strokeWeight(1f);
                }

                for (Direction direction : Direction.values()) {
                    if (!maze[x][y][direction.ordinal()]) { // Passable?

                        if (direction.X != 0) {
                            _graphics.line(
                                    cellCenterX + direction.X * _cellLocalCenterX, cellCenterY - _cellLocalCenterY,
                                    cellCenterX + direction.X * _cellLocalCenterX, cellCenterY + _cellLocalCenterY);
                        }

                        if (direction.Y != 0) {
                            _graphics.line(
                                    cellCenterX - _cellLocalCenterX, cellCenterY + direction.Y * _cellLocalCenterY,
                                    cellCenterX + _cellLocalCenterX, cellCenterY + direction.Y * _cellLocalCenterY);
                        }
                    }
                }
            }
        }
        _graphics.endFrame();
    }

    private boolean[][][] generatePaths(int mapSizeX, int mapSizeY) {

        Direction[] directions = Direction.values();

        boolean[][][] maze = new boolean[mapSizeX][mapSizeY][directions.length];
        boolean[][] visited = new boolean[mapSizeX][mapSizeY];

        int currentI = 0;//Random.range(mapSizeX);
        int currentJ = 0;//Random.range(mapSizeY);
        int nextI;
        int nextJ;

        Stack<Integer> stackI = new Stack<>();
        Stack<Integer> stackJ = new Stack<>();

        stackI.push(currentI);
        stackJ.push(currentJ);
        int stackCount = 1;

        while (stackCount > 0) {

            renderFrame(maze, visited, currentI, currentJ, stackI, stackJ);

            visited[currentI][currentJ] = true;
            nextI = currentI;
            nextJ = currentJ;

            int offset = Random.range(directions.length);
            for (int c = 0; c < directions.length; ++c) {

                int directionIndex = (c + offset) % directions.length;

                // Already passable?
                if (maze[currentI][currentJ][directionIndex]) {
                    continue;
                }

                Direction direction = directions[directionIndex];
                nextI = currentI + direction.X;
                nextJ = currentJ + direction.Y;

                // Out of bounds?
                if (!Mathf.inRangeClosed(nextI, 0, mapSizeX - 1) || !Mathf.inRangeClosed(nextJ, 0, mapSizeY - 1)) {
                    nextI = currentI;
                    nextJ = currentJ;
                    continue;
                }

                // Visited?
                if (visited[nextI][nextJ]) {
                    nextI = currentI;
                    nextJ = currentJ;
                    continue;
                }

                // "Connect" the cells... Use opposite direction for adjacent cell.
                maze[currentI][currentJ][directionIndex] = true;
                switch (direction) {
                    case North -> maze[nextI][nextJ][Direction.South.ordinal()] = true;
                    case South -> maze[nextI][nextJ][Direction.North.ordinal()] = true;
                    case East -> maze[nextI][nextJ][Direction.West.ordinal()] = true;
                    case West -> maze[nextI][nextJ][Direction.East.ordinal()] = true;
                }

                break;
            }

            // Did we move forward?
            if (nextI != currentI || nextJ != currentJ) {
                stackI.push(currentI);
                stackJ.push(currentJ);
                stackCount += 1;
                currentI = nextI;
                currentJ = nextJ;
            } else { // Go back.
                currentI = stackI.pop();
                currentJ = stackJ.pop();
                stackCount -= 1;
            }
        }

        maze[0][0][Direction.South.ordinal()] = true;
        maze[mapSizeX - 1][mapSizeY - 1][Direction.North.ordinal()] = true;

        renderFrame(maze, visited, -1, -1, stackI, stackJ);

        return maze;
    }
}
