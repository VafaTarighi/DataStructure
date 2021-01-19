package mini_projects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TicTacToe {

    GameTree gt;
    GameBoard state;
    boolean turn = true;
    boolean isHumanX;
    boolean endOfGame = false;
    ArrayList<GameBoard> nextMoveBuffer = new ArrayList<>(9);
    BufferedReader reader;


    public void run() {
        state = gt.getStartBoard();
        do {
            drawBoard();
            if (turn && isHumanX || !turn && !isHumanX) {
                humanMove();
            } else {
                pcMove();
            }
            checkEnd();
            turn = !turn;
        } while (!endOfGame);

        drawBoard();

        switch (state.getScore()) {
            case 10:
                System.out.println("You are the Winner!!!");
                break;
            case -10:
                System.out.println("Computer is the Winner!!!");
                break;
            default:
                System.out.println("This is a tie!");
        }

    }

    private void checkEnd() {
        endOfGame = state.getChildren().isEmpty();
    }

    private void humanMove() {
        System.out.printf("Your turn to place an (%c): ", turn? 'X':'O');
        String block;
        int bNum;

        while (true) {
            try {
                block = reader.readLine();
                if (!block.matches("[1-9]")) {
                    System.out.println("Please enter a number.");
                    System.out.printf("Your turn to place an (%c): ", turn? 'X':'O');
                    continue;
                }
                bNum = Integer.parseInt(block) - 1;

                if (state.getBoard()[bNum] != ' ') {
                    System.out.println("Block already taken.");
                    System.out.printf("Your turn to place an (%c): ", turn? 'X':'O');
                    continue;
                }

                break;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (GameBoard child : state.getChildren()) {
            if (child.getBoard()[bNum] != ' ') {
                state = child;
                break;
            }
        }

    }

    private void pcMove() {
        System.out.printf("Computer's turn to place an (%c): ", turn? 'X':'O');

        try {
            for (int i = 0; i < 4; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();

        GameBoard best = state.getChildren().get(0);
        if (state.getTurn()) {
            for (GameBoard child : state.getChildren()) {
                if (child.getScore() > best.getScore())
                    best = child;
            }
        } else {
            for (GameBoard child : state.getChildren()) {
                if (child.getScore() < best.getScore())
                    best = child;
            }
        }

        for (GameBoard child : state.getChildren()) {
            if (child.getScore() == best.getScore())
                nextMoveBuffer.add(child);
        }
        state = nextMoveBuffer.get((int) (Math.random() * nextMoveBuffer.size()));
        nextMoveBuffer.clear();
    }

    private void drawBoard() {
        System.out.println("=================================");
        char[] board = state.getBoard();

        for (int i = 0; ; ) {
            if (board[i] == ' ')
                System.out.print("   |");
            else
                System.out.printf(" %c |", board[i]);


            i++;
            if (i >= 9) {
                System.out.print("\b\n");
                break;
            }

            if (i % 3 == 0)
                System.out.println("\b\n---|---|---");
        }
        System.out.println("=================================");
    }


    public TicTacToe() {
        gt = GameTree.createGameTree();
        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("_-_-_-__Tic Tac Toe__-_-_-_\n");
        System.out.print("choose (X/O) : ");
        String choice = "";
        try {
            while (!(choice = reader.readLine()).matches("[XxOo]")) {
                System.out.print("chose (X/O) : ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        isHumanX = choice.matches("[Xx]");

        run();
    }

    public static void main(String[] args) {
        new TicTacToe();
    }

}