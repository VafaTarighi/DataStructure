package tree;

public class TreeNodeDoesNotExistException extends TreeNodeException {
    public TreeNodeDoesNotExistException() {
        super("Tree node doesn't exist.");
    }
}
