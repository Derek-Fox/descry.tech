package algosrc;

import descry.Descry;
import descry.VisualAlgorithm;
import descry.internal.VisualDebugger;

import java.util.*;

public class MazeSolver extends MazeGenerator {

    public MazeSolver(int sizeX, int sizeY) {
        super(sizeX, sizeY);
    }

    Cell[][] grid;

    static final class Cell {
        private final boolean[] paths;
        private int[] lastCell;
        private boolean visited;
        private boolean inPath;

        Cell(boolean[] paths, int[] lastCell, boolean visited, boolean inPath) {
            this.paths = paths;
            this.lastCell = lastCell;
            this.visited = visited;
        }

        public boolean[] paths() {
            return paths;
        }


        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Cell) obj;
            return Objects.equals(this.paths, that.paths) &&
                    Objects.equals(this.lastCell, that.lastCell) &&
                    this.visited == that.visited;
        }

        @Override
        public int hashCode() {
            return Objects.hash(paths, lastCell, visited);
        }

        @Override
        public String toString() {
            return "Cell[" +
                    "paths=" + paths + ", " +
                    "lastCell=" + lastCell + ", " +
                    "visited=" + visited + ']';
        }


    }

    public void solve(VisualDebugger graphics, boolean[][][] maze, int startX, int startY, int endX, int endY) {

        Cell[][] cellArray = new Cell[_gridSizeX][_gridSizeY];
        for (int i = 0; i < _gridSizeX; i++) {
            for (int j = 0; j < _gridSizeY; j++) {
                boolean[] paths = {
                        maze[i][j][0],
                        maze[i][j][1],
                        maze[i][j][2],
                        maze[i][j][3]
                };

                cellArray[i][j] = new Cell(paths, new int[]{-1, -1}, false, false);
            }
        }

        renderFrame(graphics, cellArray);

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});

        while (!queue.isEmpty()) {

            renderFrame(graphics, cellArray);

            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            if (x == endX && y == endY) {
                while (cellArray[x][y].lastCell[0] != -1) {
                    cellArray[x][y].inPath = true;
                    renderFrame(graphics, cellArray);
                    int nextX = cellArray[x][y].lastCell[0];
                    int nextY = cellArray[x][y].lastCell[1];
                    x = nextX;
                    y = nextY;
                }

                cellArray[startX][startY].inPath = true;
                renderFrame(graphics, cellArray);
                return;
            }

            cellArray[x][y].visited = true;

            for (Direction direction : Direction.values()) {

                if (!cellArray[x][y].paths[direction.ordinal()]) {
                    continue;
                }

                int nextX = x + direction.X;
                int nextY = y + direction.Y;
                boolean xInRange = 0 <= nextX && nextX < _gridSizeX;
                boolean yInRange = 0 <= nextY && nextY < _gridSizeY;
                if (xInRange && yInRange && !cellArray[nextX][nextY].visited) {
                    cellArray[nextX][nextY].lastCell[0] = x;
                    cellArray[nextX][nextY].lastCell[1] = y;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }

    }

    private void renderFrame(VisualDebugger graphics, Cell[][] cellArray) {

        if (graphics == null) {
            return;
        }

        graphics.beginFrame();
        graphics.background(200);
        graphics.translate(
                graphics.getSizeX() * 0.5f - _boardLocalCenterX,
                graphics.getSizeY() * 0.5f - _boardLocalCenterY
        );

        graphics.noStroke();

        for (int x = 0; x < _gridSizeX; ++x) {
            for (int y = 0; y < _gridSizeY; ++y) {

                if (cellArray[x][y].inPath) {
                    graphics.fillColor(227, 180, 39);
                } else if (cellArray[x][y].visited) {
                    graphics.fillColor(255);
                } else {
                    graphics.fillColor(220);
                }

                graphics.rectangle(x * _cellSizeX, y * _cellSizeY, _cellSizeX, _cellSizeY);
            }
        }

        graphics.strokeColor(0);
        for (int x = 0; x < _gridSizeX; ++x) {
            for (int y = 0; y < _gridSizeY; ++y) {

                float cellCenterX = x * _cellSizeX + _cellLocalCenterX;
                float cellCenterY = y * _cellSizeY + _cellLocalCenterY;

                graphics.strokeWeight(3f);

                for (Direction direction : Direction.values()) {
                    if (!cellArray[x][y].paths[direction.ordinal()]) { // Passable?

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

    public static void main(String[] args) {
        Descry.visualize(new MazeSolver(18, 12));
    }

    @Override
    public void run(VisualDebugger graphics) {
        setBoardSize(
                graphics.getSizeX() * 0.8f,
                graphics.getSizeY() * 0.8f
        );
        graphics.setFrameRate(20);
        boolean[][][] maze = generatePaths(null);
        solve(graphics, maze, 0, 0, _gridSizeX - 1, _gridSizeY - 1);
    }
}

