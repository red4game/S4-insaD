package model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class Maze {
    private int N; // dimension of maze
    private boolean[][] north; // is there a wall to north of cell i, j
    private boolean[][] east;
    private boolean[][] south;
    private boolean[][] west;
    private boolean[][] visited;

    public Maze(int N) {
        this.N = N;
        init();
        generate();
    }

    public int size() {
        return N;
    }

    public boolean northWalls(int x, int y) {
        return north[x][y];
    }

    public boolean eastWalls(int x, int y) {
        return east[x][y];
    }

    public boolean southWalls(int x, int y) {
        return south[x][y];
    }

    public boolean westWalls(int x, int y) {
        return west[x][y];
    }

    private void init() {
        visited = new boolean[N + 2][N + 2];
        for (int x = 0; x < N + 2; x++)
            visited[x][0] = visited[x][N + 1] = true;
        for (int y = 0; y < N + 2; y++)
            visited[0][y] = visited[N + 1][y] = true;

        // initialize all walls as present
        north = new boolean[N + 2][N + 2];
        east = new boolean[N + 2][N + 2];
        south = new boolean[N + 2][N + 2];
        west = new boolean[N + 2][N + 2];
        for (int x = 0; x < N + 2; x++)
            for (int y = 0; y < N + 2; y++)
                north[x][y] = east[x][y] = south[x][y] = west[x][y] = true;
    }

    // generate the maze
    private void generate() {
        Deque<Integer> stackX = new ArrayDeque<>();
        Deque<Integer> stackY = new ArrayDeque<>();
        visited[1][1] = true;
        stackX.addFirst(1);
        stackY.addFirst(1);

        while (!stackX.isEmpty()) { 
            int x = stackX.removeFirst();
            int y = stackY.removeFirst();
            visited[x][y] = true;
            // while there is an unvisited neighbor
            if (!visited[x][y + 1] || !visited[x + 1][y] || !visited[x][y - 1]
                    || !visited[x - 1][y]) {
                // pick random neighbor (could use Knuth's trick instead)
                stackX.addFirst(x);
                stackY.addFirst(y);
                while (true) {
                    double r = Math.random();
                    if (r < 0.25 && !visited[x][y + 1]) {
                        north[x][y] = south[x][y + 1] = false;
                        stackX.addFirst(x);
                        stackY.addFirst(y + 1);
                        break;
                    } else if (r >= 0.25 && r < 0.50 && !visited[x + 1][y]) {
                        east[x][y] = west[x + 1][y] = false;
                        stackX.addFirst(x + 1);
                        stackY.addFirst(y);
                        break;
                    } else if (r >= 0.5 && r < 0.75 && !visited[x][y - 1]) {
                        south[x][y] = north[x][y - 1] = false;
                        stackX.addFirst(x);
                        stackY.addFirst(y - 1);
                        break;
                    } else if (r >= 0.75 && r < 1.00 && !visited[x - 1][y]) {
                        west[x][y] = east[x - 1][y] = false;
                        stackX.addFirst(x - 1);
                        stackY.addFirst(y);
                        break;
                    }
                }
            }
        }
        if (N <= 3) return;
        Random r = new Random();
        for (int i = 0; i < N; i++) {
            int x = 2 + r.nextInt(N - 2);
            int y = 2 + r.nextInt(N - 2);
            switch (r.nextInt(4)) {
            case 0:
                north[x][y] = south[x][y + 1] = false;
                break;
            case 1:
                east[x][y] = west[x + 1][y] = false;
                break;
            case 2:
                south[x][y] = north[x][y - 1] = false;
                break;
            case 3:
                west[x][y] = east[x - 1][y] = false;
                break;
            default:
                break;
            }
        }
    }
}