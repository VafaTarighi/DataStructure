package mini_projects;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GameTree implements Serializable {


    private final GameBoard startBoard;


    public GameBoard getStartBoard() {
        return startBoard;
    }

    private GameTree(GameBoard startBoard) {
        this.startBoard = startBoard;
    }

    public static GameTree createGameTree() {

        GameBoard start = GameBoard.getEmptyBoard();
        GameBoard cur;
        char[] curBoard;
        int win;
        GameBoard child;

        Queue<GameBoard> queue = new LinkedList<>();
        Stack<GameBoard> stack = new Stack<>();
        queue.add(start);

        // Using BFS to create moves
        while (!queue.isEmpty()) {
            cur = queue.poll();
            stack.push(cur);
            curBoard = cur.getBoard();

            // check for win and if it's a win, set score
            win = isWin(cur);
            if (win != 0) {
                if (win == 1)
                    cur.setScore(10);
                else
                    cur.setScore(-10);

                continue;
            }

            // find and add next moves to the queue
            for (int i = 0; i < 9; i++) {
                if (curBoard[i] == ' ') {
                    child = new GameBoard(cur, i);
                    queue.add(child);
                    cur.addChild(child);
                }
            }

        }

        //Using DFS to set board state scores
        int[] scores = new int[9];
        int ci = 0;
        GameBoard parent;
        int minMaxVal;
        while (true) {
            parent = stack.peek().getParent();
            minMaxVal = !stack.peek().getTurn()? 1 : -1;

            scores[ci++] = stack.pop().getScore() - minMaxVal;

            if (stack.isEmpty())
                break;

            while (stack.peek().getParent() == parent) {
                scores[ci++] = stack.pop().getScore() - minMaxVal;
            }

            parent.setScore(parent.getTurn()?
                    maxScore(scores, ci) : minScore(scores, ci));

            ci = 0;

        }

        return new GameTree(start);
    }

    private static int maxScore(int[] scores, int ci) {
        int max = scores[0];
        int sc;
        for (int i = 1; i < ci; i++) {
            sc = scores[i];
            if (max < sc)
                max = sc;
        }
        return max;
    }

    private static int minScore(int[] scores, int ci) {
        int min = scores[0];
        int sc;
        for (int i = 0; i < ci; i++) {
            sc = scores[i];
            if (min > sc) {
                min = sc;
            }
        }
        return min;
    }

    public static int isWin(GameBoard gb) {
        char[] board = gb.getBoard();

        // check rows
        for (int i = 0; i < 9; i+=3) {
            if (board[i] != ' ')
                if (board[i] == board[i + 1] && board[i + 1] == board[i + 2])
                    return (board[i] == 'X')? 1 : -1;
        }

        // check columns
        for (int i = 0; i < 3; i++) {
            if (board[i] != ' ')
                if (board[i] == board[i + 3] && board[i + 3] == board[i + 6])
                    return (board[i] == 'X')? 1 : -1;
        }

        // check diagonals
        if (board[4] != ' ') {
            if (board[0] == board[4] && board[4] == board[8])
                return (board[0] == 'X')? 1 : -1;
            if (board[2] == board[4] && board[4] == board[6])
                return (board[2] == 'X')? 1 : -1;
        }

        return 0;
    }

}
