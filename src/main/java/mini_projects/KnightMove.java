package mini_projects;

import list.DynamicList;
import tree.Tree;

/**
 * This code creates a tree of all possible moves of a chess knight on
 * a NxN (N_max = 26) board with the given depth and can determine the
 * shortest path to a given position on the board.
 */

public class KnightMove {

    private final Tree<String> tree;
    private final int boardScale;


    public KnightMove(int boardScale, String knightPos, int maxDepth) {
        // validations
        if (boardScale < 1)
            throw new IllegalArgumentException("Negative board size");
        if (boardScale > 26)
            throw new IllegalArgumentException("Board size out of bound");
        if (!knightPos.matches("[a-z][1-9][0-9]?"))
            throw new IllegalArgumentException("position must be composed" +
                    "\n of a lowercase letter and a number.(e.g., a5, 3b, ...)");
        if (Integer.parseInt(knightPos.substring(1)) > 26
                || knightPos.charAt(0) > ('a' + (boardScale - 1)))
            throw new IndexOutOfBoundsException("position index out of bound");


        tree = new Tree<>(knightPos);
//        positions = new DynamicList<>();
//        positions.add(tree.getRoot());
        this.boardScale = boardScale;

        fillTree(maxDepth);

    }

    private void fillTree(int maxDepth) {
        DynamicList<Tree.TreeNode<String>> currentLevel = new DynamicList<>();
        DynamicList<Tree.TreeNode<String>> nextLevel = new DynamicList<>();

        currentLevel.add(tree.getRoot());

        Tree.TreeNode<String> curPos;

        for (int i = 0; i < maxDepth; i++) {
            while (!currentLevel.isEmpty()) {

                curPos = currentLevel.removeLast();

                nextLevel.addAll(getMoves(curPos));
            }
//            positions.addAll(nextLevel);
            currentLevel.addAll(nextLevel);
            nextLevel.reset();
        }

    }

    private DynamicList<Tree.TreeNode<String>> getMoves(Tree.TreeNode<String> position) {
        String pos = position.getValue();
        String parentPos;
        try {
            parentPos = position.getParent().getValue();
        } catch (Exception ignored) {
            parentPos = "";
        }
        char x = pos.charAt(0);
        int y = Integer.parseInt(pos.substring(1));

        char newX;
        int newY;

        //up left
        newX = xDec(x, 1);
        newY = yInc(y, 2);
        if (newY != -1 && newX != '#') {
            tree.addChild(newX+""+newY, position, 8);
        }

        //up right
        newX = xInc(x, 1);
        newY = yInc(y, 2);
        if (newY != -1 && newX != '#') {
            tree.addChild(newX+""+newY, position, 8);
        }

        //down left
        newX = xDec(x, 1);
        newY = yDec(y, 2);
        if (newY != -1 && newX != '#') {
            tree.addChild(newX+""+newY, position, 8);
        }

        //down right
        newX = xInc(x, 1);
        newY = yDec(y, 2);
        if (newY != -1 && newX != '#') {
            tree.addChild(newX+""+newY, position, 8);
        }

        //left down
        newX = xDec(x, 2);
        newY = yDec(y, 1);
        if (newX != '#' && newY != -1) {
            tree.addChild(newX+""+newY, position, 8);
        }

        //left up
        newX = xDec(x, 2);
        newY = yInc(y, 1);
        if (newX != '#' && newY != -1) {
            tree.addChild(newX+""+newY, position, 8);
        }

        //right down
        newX = xInc(x, 2);
        newY = yDec(y, 1);
        if (newX != '#' && newY != -1) {
            tree.addChild(newX+""+newY, position, 8);
        }

        //right up
        newX = xInc(x, 2);
        newY = yInc(y, 1);
        if (newX != '#' && newY != -1) {
            tree.addChild(newX+""+newY, position, 8);
        }

        return position.getChildren();
    }

    private Tree.TreeNode<String> findTarget(String pos) {
        StringBuilder visited = new StringBuilder(boardScale*boardScale);
        Tree.TreeNode<String> root = tree.getRoot();
        Tree.TreeNode<String> target;
        String tarStr;
        DynamicList<Tree.TreeNode<String>> children;
        Tree.TreeNode<String> child;
        DynamicList<Tree.TreeNode<String>> currLevel = new DynamicList<>(100);
        DynamicList<Tree.TreeNode<String>> nextLevel = new DynamicList<>(100);

        currLevel.add(root);
        visited.append(root.getValue()).append(":");

        while (!currLevel.isEmpty()) {
            while (!currLevel.isEmpty()) {
                target = currLevel.removeLast();
                tarStr = target.getValue();
                if (tarStr.equals(pos))
                    return target;

                visited.append(target.getValue()).append(":");
                children = target.getChildren();
                for (int i = 0; i < children.size(); i++) {
                    child = children.get(i);
                    if (visited.indexOf(child.getValue()) == -1)
                        nextLevel.add(child);
                }
            }
            currLevel.addAll(nextLevel);
            nextLevel.reset();
        }

        return null;
    }

    public String getPath(String pos) {
        Tree.TreeNode<String> target = findTarget(pos);
        StringBuilder path = new StringBuilder();

        if (target == null)
            return "path not found.";

        while (target != null) {
            path.append(target.getValue()).append("->");
            target = target.getParent();
        }

        String[] nodes = path.substring(0, path.length()-2).split("->");
        path = new StringBuilder();
        for (int i = nodes.length - 1; i >= 0; i--) {
            path.append(nodes[i]).append("->");
        }

        return path.substring(0, path.length()-2);

    }

    public int getTotalPossibleMoves() {
        return tree.getTreeSize();
    }


    // Utility methods
    private char xInc(char x, int i) {
        char xi = (char) (x + i);
        if (xi < 'a' + boardScale)
            return xi;
        else
            return '#';
    }
    private char xDec(char x, int i) {
        char xd = (char) (x - i);
        if (xd >= 'a')
            return xd;
        else
            return '#';
    }
    private int yInc(int y, int i) {
        int yi = y + i;
        if (yi <= boardScale)
            return yi;
        else
            return -1;
    }
    private int yDec(int y, int i) {
        int yd = y - i;
        if (yd > 0)
            return yd;
        else
            return -1;
    }



    // Main method
    public static void main(String[] args) {
        long start, end;

        start = System.currentTimeMillis();


        /*
         * Change (boardScale{firstArg}  knightPos{secondArg}  maxDepth{thirdArg})
         * to modify board start point, scale and maximum depth of path tree
         * higher depths can lead to OutOfMemoryError: Java heap space
         */
        KnightMove km = new KnightMove(8, "a1", 8);

        /*
         * Change getPath()'s parameter to change target position to find it's shortest path
         */
        String path = km.getPath("h8");


        end = System.currentTimeMillis();
        System.out.println("path: " + path);

        System.out.println("path length: " + (path.split("->").length-1));
        System.out.println("tree size: " + km.getTotalPossibleMoves());
        System.out.println("exe time: " + (end - start) + "ms");
    }
}
