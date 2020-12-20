package tree;

import list.DynamicList;

public class Tree<E> {

    public static class TreeNode<E> {

        private E value;
        private final TreeNode<E> parent;
        private final DynamicList<TreeNode<E>> children;

        public E getValue() {
            return value;
        }

        public boolean equalsValue(E val) {
            return this.value.equals(val);
        }

        public void setValue(E value) {
            this.value = value;
        }

        public TreeNode<E> getParent() {
            return parent;
        }

        public DynamicList<TreeNode<E>> getChildren() {
            return children;
        }

        public TreeNode(E value, TreeNode<E> parent, int maxChildren) {
            this.value = value;
            this.parent = parent;
            children = new DynamicList<>(maxChildren);
        }

        public TreeNode(E value, TreeNode<E> parent) {
            this.value = value;
            this.parent = parent;
            children = new DynamicList<>();
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    private final TreeNode<E> root;
    private int size = 0;

    public Tree(E rootValue) {
        root = new TreeNode<>(rootValue, null);
        size++;
    }


    public void addChild(E childValue, TreeNode<E> parent) {
        parent.children.add(new TreeNode<E>(childValue, parent));
        size++;
    }

    public void addChild(E childValue, TreeNode<E> parent, int maxChildrenCount) {
        parent.children.add(new TreeNode<>(childValue, parent, maxChildrenCount));
        size++;
    }

    public void addChildren(DynamicList<TreeNode<E>> list, TreeNode<E> parent) {
        parent.children.addAll(list);
        size += list.size();
    }


    public TreeNode<E> getRoot() {
        return root;
    }

    public int getTreeSize() {
        return size;
    }


}
