package mini_apps;

import stack.LinkedStack;
import queue.LinkedQueue;

import java.util.Arrays;

/**
 * Program that uses Path-Finding BFS & DFS algorithms
 * to solve a maze problem by giving a matrix(char[][])
 * map of roads ( represented by '0' character)
 * and barriers( represented by '1' character)
 */

public class MouseAndCheese {

    private static class Node {
        final int i;
        final int j;
        final int distance;
        final Node parent;

        public Node(int i, int j, int distance, Node parent) {
            this.i = i;
            this.j = j;
            this.distance = distance;
            this.parent = parent;
        }
    }

    private final char[][] MAP;

    // Restricting Instantiation
    public MouseAndCheese(char[][] MAP) {
        this.MAP = MAP;
    }


    public Node bfsSearch(Node src, int cheeseI, int cheeseJ) {
        char[][] map = copyMap();

        // Marking cheese location in the map with char 'D' if it's not a wall
        if (map[cheeseI][cheeseJ] != 1)
            map[cheeseI][cheeseJ] = 'D';

        LinkedQueue<Node> queue = new LinkedQueue<>();
        queue.enqueue(src);

        while (!queue.isEmpty()) {
            Node curr = queue.dequeue();

            if (map[curr.i][curr.j] == 'D') {
                return curr;
            } else if (map[curr.i][curr.j] != '1') {
                // Setting node as visited
                map[curr.i][curr.j] = '1';

                addNeighbors(curr, map, queue);
            }
        }

        return null;

    }

    public Node dfsSearch(Node src, int cheeseI, int cheeseJ) {
        char[][] map = copyMap();
        // Marking cheese location in the map with char 'D' if it's not a wall
        if (map[cheeseI][cheeseJ] != 1)
            map[cheeseI][cheeseJ] = 'D';

        LinkedStack<Node> stack = new LinkedStack<>();
        stack.push(src);

        Node curr;
        while (!stack.isEmpty()) {
            curr = stack.top();

            if (map[curr.i][curr.j] == 'D')
                return curr;

            map[curr.i][curr.j] = '1';
            Node neighbor = getNeighbor(curr, map);
            if (neighbor != null)
                stack.push(neighbor);
            else {
                stack.pop();
            }
        }

        return null;
    }

    private Node getNeighbor(Node curr, char[][] map) {
        int h = map.length;
        int w = map[0].length;

        //Up
        if (curr.i - 1 >= 0 && map[curr.i - 1][curr.j] != '1') {
            return new Node(curr.i - 1, curr.j, curr.distance + 1, curr);
        }
        //Down
        if (curr.i + 1 < h && map[curr.i + 1][curr.j] != '1') {
            return new Node(curr.i + 1, curr.j, curr.distance + 1, curr);
        }
        //Left
        if (curr.j - 1 >= 0 && map[curr.i][curr.j - 1] != '1') {
            return new Node(curr.i, curr.j - 1, curr.distance + 1, curr);
        }
        //Right
        if (curr.j + 1 < w && map[curr.i][curr.j + 1] != '1') {
            return new Node(curr.i, curr.j + 1, curr.distance + 1, curr);
        }

        return null;

    }

    private void addNeighbors(Node curr, char[][] map, LinkedQueue<Node> queue) {
        int h = map.length;
        int w = map[0].length;

        Node nei;
        //Up
        if (curr.i - 1 >= 0 && map[curr.i - 1][curr.j] != '1') {
            nei = new Node(curr.i - 1, curr.j, curr.distance + 1, curr);
            queue.enqueue(nei);
        }
        //Down
        if (curr.i + 1 < h && map[curr.i + 1][curr.j] != '1') {
            nei = new Node(curr.i + 1, curr.j, curr.distance + 1, curr);
            queue.enqueue(nei);
        }
        //Left
        if (curr.j - 1 >= 0 && map[curr.i][curr.j - 1] != '1') {
            nei = new Node(curr.i, curr.j - 1, curr.distance + 1, curr);
            queue.enqueue(nei);
        }
        //Up
        if (curr.j + 1 < w && map[curr.i][curr.j + 1] != '1') {
            nei = new Node(curr.i, curr.j + 1, curr.distance + 1, curr);
            queue.enqueue(nei);
        }

    }

    private char[][] copyMap() {
        int h = MAP.length;
        int w = MAP[0].length;

        char[][] copy = new char[h][w];
        for (int i = 0; i < MAP.length; i++) {
            copy[i] = Arrays.copyOf(MAP[i], w);
        }

        return copy;
    }

    public String getPath(Node dest) {
        StringBuilder path = new StringBuilder(dest.distance);

        Node curr = dest;
        Node prev;
        while ((prev = curr.parent) != null) {
            if (curr.i > prev.i)
                path.append('D');
            else if (curr.i < prev.i)
                path.append('U');
            else if (curr.j > prev.j)
                path.append('R');
            else if (curr.j < prev.j)
                path.append('L');

            curr = prev;
        }

        return path.reverse().toString();
    }

    static final char[][] myMap = {
            {'S', '0', '1', '0', '1', '1', '0'},
            {'1', '0', '0', '1', '0', '0', '0'},
            {'1', '0', '0', '1', '0', '0', '1'},
            {'1', '1', '0', '0', '1', '0', '0'},
            {'0', '0', '0', '0', '1', '0', '1'},
            {'0', '1', '0', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '1', '0'},
    };

    public static void main(String[] args) {
        MouseAndCheese mac = new MouseAndCheese(myMap);
        Node mouse = new Node(6, 0, 0, null);
        String path;

        Node cheese = mac.bfsSearch(mouse, 0, 3);
        System.out.println("BFS:");
        if (cheese != null) {
            path = mac.getPath(cheese);
            System.out.println("  Path: " + path + "\n  Distance: " + cheese.distance);
        } else
            System.out.println("  Path: N/A");

        cheese = mac.dfsSearch(mouse, 5, 6);
        System.out.println("DFS:");
        if (cheese != null) {
            path = mac.getPath(cheese);
            System.out.println("  Path: " + path + "\n  Distance: " + cheese.distance);
        } else
            System.out.println("  Path: N/A");

    }

    // Extra mazes to try

    static final char[][] maze = {
            {'1','1','1','1','1','1','1','1','1','1','1','1','1'},
            {'1','0','1','0','1','0','1','0','0','0','0','0','1'},
            {'1','0','1','0','0','0','1','0','1','1','1','0','1'},
            {'1','0','0','0','1','1','1','0','0','0','0','0','1'},
            {'1','0','1','0','0','0','0','0','1','1','1','0','1'},
            {'1','0','1','0','1','1','1','0','1','0','0','0','1'},
            {'1','0','1','0','1','0','0','0','1','1','1','0','1'},
            {'1','0','1','0','1','1','1','0','1','0','1','0','1'},
            {'1','0','0','0','0','0','0','0','0','0','1','0','1'},
            {'1','1','1','1','1','1','1','1','1','1','1','1','1'}
    };

    static final char[][] maze2 = {
            {'0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0'},
            {'1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0'},
            {'0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0'},
            {'0', '1', '0', '1', '1', '0', '0', '1', '1', '1', '0', '1', '0'},
            {'0', '0', '0', '1', '0', '0', '0', '0', '0', '1', '0', '1', '0'},
            {'0', '1', '1', '1', '1', '1', '0', '1', '0', '1', '0', '1', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '1', '0', '0'},
    };

}
