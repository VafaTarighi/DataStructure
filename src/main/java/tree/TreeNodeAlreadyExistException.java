package tree;

public class TreeNodeAlreadyExistException extends TreeNodeException {
    public TreeNodeAlreadyExistException()  {
        super("Tree node already exists.");
    }
}
