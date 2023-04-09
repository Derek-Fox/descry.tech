package algosrc;

import descry.Descry;
import descry.VisualAlgorithm;
import descry.internal.VisualDebugger;

import java.util.*;

public class MazeSolver extends MazeGenerator {

    public MazeSolver(int sizeX, int sizeY) {
        super(sizeX, sizeY);
    }

    public Queue<int[]> solve(VisualDebugger graphics, boolean[][][] maze, int startX, int startY, int endX, int endY) {

        boolean[][] visited = new boolean[_gridSizeX][_gridSizeY];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { startX, startY });

        while (!queue.isEmpty()) {

            renderFrame(graphics, maze, visited);

            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            if (x == endX && y == endY) {
                return queue;
            }

            visited[x][y] = true;

            for (Direction direction : Direction.values()) {

                if (!maze[x][y][direction.ordinal()]) {
                    continue;
                }

                int nextX = x + direction.X;
                int nextY = y + direction.Y;
                boolean xInRange = 0 <= nextX && nextX < _gridSizeX;
                boolean yInRange = 0 <= nextY && nextY < _gridSizeY;
                if (xInRange && yInRange && !visited[nextX][nextY]) {
                    queue.add(new int[] {nextX, nextY});
                }
            }
        }

        return queue;
    }

    private void renderFrame(VisualDebugger graphics, boolean[][][] maze, boolean[][] visited) {

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

                if (visited[x][y]) {
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

    public static void main(String[] args) {
        Descry.visualize(new MazeSolver(18, 12));
    }

    @Override
    public void run(VisualDebugger graphics) {
        setBoardSize(
                graphics.getSizeX() * 0.8f,
                graphics.getSizeY() * 0.8f
        );
        boolean[][][] maze = generatePaths(graphics);
        solve(graphics, maze, 0, 0, _gridSizeX - 1, _gridSizeY - 1);
    }
}

