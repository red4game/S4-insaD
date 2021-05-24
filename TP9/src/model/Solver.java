package model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Comparator;

import util.Coordinate;

public class Solver {
    private Maze maze;
    private boolean[][] visited;
    private Deque<Coordinate> path;
    private int cost;

    public void setMaze(Maze maze) {
        this.maze = maze;
        visited = new boolean[maze.size() + 2][maze.size() + 2];
        path = new ArrayDeque<>();
        reset();
    }

    public Maze getMaze() {
        return maze;
    }

    public Iterable<Coordinate> getPath() {
        return path;
    }

    public int getCost() {
        return cost;
    }

    private void constructPath(SearchNode exit) {
        this.cost = exit.distance;
        while (exit!=null){
            this.path.addLast(exit.getCoordinate());
            exit = exit.parent;
        }
    }

    private boolean possible(int x, int y) {
        return x != 0 && y != 0 && x != maze.size() + 1 && y != maze.size() + 1
                && !visited[x][y];
    }

    private class SearchNode {
        SearchNode parent;
        int x;
        int y;
        int distance;

        SearchNode(SearchNode parent, int x, int y, int distance) {
            this.parent = parent;
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        Coordinate getCoordinate() {
            return new Coordinate(x, y);
        }
    }

    private SearchNode dfs(int startX, int startY) {
        Deque<SearchNode> stack = new ArrayDeque<>();
        stack.addFirst(new SearchNode(null, startX, startY, 0));
        while (!stack.isEmpty()) {
            SearchNode node = stack.removeFirst();
            int x = node.x;
            int y = node.y;
            visited[x][y] = true;
            // reached goal
            if (x == maze.size() && y == maze.size())
                return node;

            if (!maze.northWalls(x, y) && possible(x, y + 1)) {
                stack.addFirst(new SearchNode(node, x, y + 1, node.distance + 1));
            }
            if (!maze.eastWalls(x, y) && possible(x + 1, y)) {
                stack.addFirst(new SearchNode(node, x + 1, y, node.distance + 1));
            }
            if (!maze.southWalls(x, y) && possible(x, y - 1)) {
                stack.addFirst(new SearchNode(node, x, y - 1, node.distance + 1));
            }
            if (!maze.westWalls(x, y) && possible(x - 1, y)) {
                stack.addFirst(new SearchNode(node, x - 1, y, node.distance + 1));
            }
        }
        return null;
    }

    public void reset() {
        path = new ArrayDeque<>();
        cost = -1;
        for (int x = 0; x <= maze.size() + 1; x++) {
            visited[0][x] = true;
            visited[x][0] = true;
            visited[maze.size() + 1][x] = true;
            visited[x][maze.size() + 1] = true;
        }
        for (int x = 1; x <= maze.size(); x++)
            for (int y = 1; y <= maze.size(); y++)
                visited[x][y] = false;
    }

    public void dfs() {
        reset();
        SearchNode node = dfs(1, 1);
        if (node != null)
            constructPath(node);
    }

    private SearchNode bfs(int startX, int startY) {
        Deque<SearchNode> stack = new ArrayDeque<>();
        stack.addFirst(new SearchNode(null, startX, startY, 0));
        while (!stack.isEmpty()) {
            SearchNode node = stack.removeFirst();
            int x = node.x;
            int y = node.y;
            visited[x][y] = true;
            // reached goal
            if (x == maze.size() && y == maze.size())
                return node;

            if (!maze.northWalls(x, y) && possible(x, y + 1)) {
                stack.addLast(new SearchNode(node, x, y + 1, node.distance + 1));
            }
            if (!maze.eastWalls(x, y) && possible(x + 1, y)) {
                stack.addLast(new SearchNode(node, x + 1, y, node.distance + 1));
            }
            if (!maze.southWalls(x, y) && possible(x, y - 1)) {
                stack.addLast(new SearchNode(node, x, y - 1, node.distance + 1));
            }
            if (!maze.westWalls(x, y) && possible(x - 1, y)) {
                stack.addLast(new SearchNode(node, x - 1, y, node.distance + 1));
            }
        }
        return null;
    }

    public void bfs() {
        reset();
        SearchNode node = bfs(1, 1);
        if (node != null)
            constructPath(node);
    }

    private int transitionCost(boolean hasWall, int cost) {
        return hasWall ? cost : 1;
    }

    private SearchNode dijkstra(int startX, int startY, int breakingWallCost) {
        Comparator<SearchNode> comparator = new SearchNodeComparator();
        PriorityQueue<SearchNode> queue = new PriorityQueue<>(comparator);
        queue.add(new SearchNode(null, startX, startY, 0));
        while (!queue.isEmpty()) {
            // Remove first element from queue
            SearchNode node = queue.poll();
            int x = node.x;
            int y = node.y;
            // Mark it as visited
            visited[x][y] = true;
            // Is it the output?
            if (x == maze.size() && y == maze.size())
                return node;
            // Inspect all its neighbours
            if (possible(x,y-1)) {
                queue.add(new SearchNode(node, x, y - 1,
                        node.distance + this.transitionCost(maze.southWalls(x,y), breakingWallCost)));
            }
            if (possible(x,y+1)) {
                queue.add(new SearchNode(node, x, y + 1,
                        node.distance + this.transitionCost(maze.northWalls(x,y), breakingWallCost)));
            }
            if (possible(x+1,y)) {
                queue.add(new SearchNode(node, x+1, y,
                        node.distance + this.transitionCost(maze.eastWalls(x,y), breakingWallCost)));
            }
            if (possible(x-1,y)) {
                queue.add(new SearchNode(node, x-1, y,
                        node.distance + this.transitionCost(maze.westWalls(x,y), breakingWallCost)));
            }
        }
        return null;
    }

    private class SearchNodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode x, SearchNode y) {
            if (x.distance < y.distance) {
                return -1;
            } else if (x.distance > y.distance) {
                return 1;
            }
            return 0;
        }
    }

    public void dijkstra(int breakingWallCost) {
        reset();
        SearchNode node = dijkstra(1, 1, breakingWallCost);
        if (node != null)
            constructPath(node);
    }
}
