package algosrc;

import java.util.*;

public class Dijkstra {

    private static final int[] dx = {-1, 0, 1, 0}; // direction deltas for x coordinate
    private static final int[] dy = {0, 1, 0, -1}; // direction deltas for y coordinate

    public static int dijkstra(boolean[][][] maze, int startX, int startY, int endX, int endY) {
        int n = maze.length; // width of maze
        int m = maze[0].length; // height of maze

        int[][] dist = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // min heap for dijkstra

        // initialization
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[startX][startY] = 0;
        pq.add(new int[] {startX, startY, 0});

        // dijkstra
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int x = curr[0];
            int y = curr[1];
            int d = curr[2];

            if (visited[x][y]) continue;
            visited[x][y] = true;

            if (x == endX && y == endY) return dist[x][y];

            for (int i = 0; i < 4; i++) {
                if (maze[x][y][i]) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                        int nd = d + 1;
                        if (nd < dist[nx][ny]) {
                            dist[nx][ny] = nd;
                            pq.add(new int[] {nx, ny, nd});
                        }
                    }
                }
            }
        }

        return -1; // no path found
    }

    public static void main(String[] args) {
        boolean[][][] maze = {
                {{true, false, true, false}, {true, true, false, true}, {false, true, true, false}},
                {{true, false, true, true}, {false, true, true, false}, {true, true, false, true}},
                {{true, true, false, true}, {false, true, false, true}, {true, false, true, false}},
        };
        int startX = 0;
        int startY = 0;
        int endX = 2;
        int endY = 2;
        int shortestPathLength = dijkstra(maze, startX, startY, endX, endY);
        if (shortestPathLength == -1) {
            System.out.println("No path found");
        } else {
            System.out.println("Shortest path length: " + shortestPathLength);
        }
    }
}

