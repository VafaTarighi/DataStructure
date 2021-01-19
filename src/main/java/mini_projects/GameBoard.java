package mini_projects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class GameBoard implements Serializable {

    private final GameBoard parent;
    private final ArrayList<GameBoard> children = new ArrayList<>();
    private final transient boolean turn;
    private int score;
    private final char[] board;

    public GameBoard(GameBoard parent, int move) {
        if (parent.board[move] != ' ')
            throw new IllegalStateException("place is taken");

        this.parent = parent;
        char[] board = Arrays.copyOf(parent.board, 9);
        board[move] = parent.turn? 'X' : 'O';
        this.board = board;
        this.turn = !parent.turn;
    }

    private GameBoard() {
        this.parent = null;
        board = new char[9];
        Arrays.fill(board, ' ');
        turn = true;
    }

    public static GameBoard getEmptyBoard() {
        return new GameBoard();
    }



    public char[] getBoard() {
        return board;
    }

    public GameBoard getParent() {
        return parent;
    }

    public boolean getTurn() {
        return turn;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<GameBoard> getChildren() {
        return children;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addChild(GameBoard child) {
        children.add(child);
    }
}
