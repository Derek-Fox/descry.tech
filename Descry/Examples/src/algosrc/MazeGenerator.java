package algosrc;

import descry.Descry;
import descry.VisualAlgorithm;
import descry.internal.VisualDebugger;
import descry.utility.Mathf;
import descry.utility.Random;

import java.util.Stack;

public class MazeGenerator implements VisualAlgorithm {
    
    protected final int _gridSizeX;
    protected final int _gridSizeY;
    protected float _boardLocalCenterX;
    protected float _boardLocalCenterY;
    protected float _cellSizeX;
    protected float _cellSizeY;
    protected float _cellLocalCenterX;
    protected float _cellLocalCenterY;

    public static void main(String[] args) {
        Descry.visualize(new MazeGenerator(18, 12));
    }

    public MazeGenerator(int sizeX, int sizeY) {
        _gridSizeX = sizeX;
        _gridSizeY = sizeY;
    }

    public void setBoardSize(float boardSizeX, float boardSizeY) {
        _boardLocalCenterX = boardSizeX * 0.5f;
        _boardLocalCenterY = boardSizeY * 0.5f;
        _cellSizeX = boardSizeX / _gridSizeX;
        _cellSizeY = boardSizeY / _gridSizeY;
        _cellLocalCenterX = _cellSizeX * 0.5f;
        _cellLocalCenterY = _cellSizeY * 0.5f;
    }

    @Override
    public void run(VisualDebugger graphics) {
        setBoardSize(
                graphics.getSizeX() * 0.8f,
                graphics.getSizeY() * 0.8f
        );
        generatePaths(graphics);
    }

    private void renderFrame(VisualDebugger graphics, boolean[][][] maze, boolean[][] visited, int i, int j, Stack<int[]> stack) {

        if (graphics == null) {
            return;
        }

        graphics.beginFrame();
        graphics.background(200);
        graphics.translate(
                graphics.getSizeX() * 0.5f - _boardLocalCenterX,
                graphics.getSizeY() * 0.5f - _boardLocalCenterY
        );

        if (i < 0 || j < 0) {
            graphics.noStroke();
        } else {
            graphics.strokeWeight(1f);
            graphics.strokeColor(200);
        }

        for (int x = 0; x < _gridSizeX; ++x) {
            for (int y = 0; y < _gridSizeY; ++y) {

                if (x == i && y == j) {
                    graphics.fillColor(0, 255, 0);
                } else if (visited[x][y]) {
                    graphics.fillColor(255);
                } else {
                    graphics.fillColor(220);
                }

                graphics.rectangle(x * _cellSizeX, y * _cellSizeY, _cellSizeX, _cellSizeY);
            }
        }

        for (int x = 0; x < _gridSizeX; ++x) {
            for (int y = 0; y < _gridSizeY; ++y) {

                if (x == i && y == j) {
                    graphics.fillColor(0, 255, 0);
                } else if (visited[x][y]) {
                    graphics.fillColor(255);
                } else {
                    graphics.fillColor(220);
                }

                graphics.rectangle(x * _cellSizeX, y * _cellSizeY, _cellSizeX, _cellSizeY);
            }
        }

        graphics.fillColor(220, 220, 255);
        for (int p = 0; p < stack.size(); ++p) {
            int x = stack.get(p)[0];
            int y = stack.get(p)[1];
            graphics.rectangle(x * _cellSizeX, y * _cellSizeY, _cellSizeX, _cellSizeY);
        }

        graphics.strokeColor(0);
        for (int x = 0; x < _gridSizeX; ++x) {
            for (int y = 0; y < _gridSizeY; ++y) {

                float cellCenterX = x * _cellSizeX + _cellLocalCenterX;
                float cellCenterY = y * _cellSizeY + _cellLocalCenterY;

                if (visited[x][y]) {
                    graphics.strokeWeight(3f);
                } else if (i >= 0 && j >= 0) {
                    graphics.strokeWeight(1f);
                }

                for (Direction direction : Direction.values()) {
                    if (!maze[x][y][direction.ordinal()]) { // Passable?

                        if (direction.X != 0) {
                            graphics.line(
                                    cellCenterX + direction.X * _cellLocalCenterX, cellCenterY - _cellLocalCenterY,
                                    cellCenterX + direction.X * _cellLocalCenterX, cellCenterY + _cellLocalCenterY);
                        }

                        if (direction.Y != 0) {
                            graphics.line(
                                    cellCenterX - _cellLocalCenterX, cellCenterY + direction.Y * _cellLocalCenterY,
                                    cellCenterX + _cellLocalCenterX, cellCenterY + direction.Y * _cellLocalCenterY);
                        }
                    }
                }
            }
        }
        graphics.endFrame();
    }

    public boolean[][][] generatePaths(VisualDebugger graphics) {

        Direction[] directions = Direction.values();

        boolean[][][] maze = new boolean[_gridSizeX][_gridSizeY][directions.length];
        boolean[][] visited = new boolean[_gridSizeX][_gridSizeY];

        int currentI = 0;//Random.range(mapSizeX);
        int currentJ = 0;//Random.range(mapSizeY);
        int nextI;
        int nextJ;

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] { currentI, currentJ });
        int stackCount = 1;

        while (stackCount > 0) {

            renderFrame(graphics, maze, visited, currentI, currentJ, stack);

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
                if (!Mathf.inRangeClosed(nextI, 0, _gridSizeX - 1) || !Mathf.inRangeClosed(nextJ, 0, _gridSizeY - 1)) {
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
                stack.push(new int[] { currentI, currentJ });
                stackCount += 1;
                currentI = nextI;
                currentJ = nextJ;
            } else { // Go back.
                int[] current = stack.pop();
                currentI = current[0];
                currentJ = current[1];
                stackCount -= 1;
            }
        }

        maze[0][0][Direction.South.ordinal()] = true;
        maze[_gridSizeX - 1][_gridSizeY - 1][Direction.North.ordinal()] = true;

        renderFrame(graphics, maze, visited, -1, -1, stack);
        return maze;
    }
}
