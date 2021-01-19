package tree;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class ArrayBinaryTree<E> {

    private static class BTNode<E> {
        private E value;

        public BTNode(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }
    }

    private BTNode<E>[] array;
    private int capacity;
    private int size;


    // _____Constructors_____
    public ArrayBinaryTree() {
        array = new BTNode[0];
    }

    public ArrayBinaryTree(E[] array) {
        this.array = new BTNode[array.length];
        for (int i = 0; i < array.length; i++) {
            this.array[i] = new BTNode<>(array[i]);
        }
    }


    // _____Access Methods_____
    public E root() throws EmptyTreeException {
        if (size == 0)
            throw new EmptyTreeException();
        return array[0].getValue();
    }

    public E getElement(int j) throws TreeNodeException {
        validateIndex(j);

        return array[j].getValue();
    }

    public E parent(int j) throws TreeNodeException {
        validateIndex(j);

        return array[(j - 1)/2].getValue();
    }

    public E leftChild(int j) throws TreeNodeException {
        validateIndex(j);
        validateIndex(j*2 + 1);

        return array[j*2 + 1].getValue();
    }

    public E rightChild(int j) throws TreeNodeException {
        validateIndex(j);
        validateIndex(j*2 + 2);

        return array[j*2 + 2].getValue();
    }

    public E sibling(int j) throws TreeNodeException {
        validateIndex(j);
        if (j == 0)
            throw new TreeNodeException("Root has no sibling.");

        int sibJ = j % 2 == 0? j -1 : j +1;
        validateIndex(j);

        return array[sibJ].getValue();
    }

    private void validateIndex(int j) throws TreeNodeException {
        if (j < 0 || j > capacity) {
            throw new IndexOutOfBoundsException();
        }
        if (array[j] == null)
            throw new TreeNodeDoesNotExistException();
    }

    public int size() {
        return size;
    }

    public boolean hasLeft(int j) throws TreeNodeException {
        validateIndex(j);

        return !(j*2+1 < 0) && j*2+1 < capacity && array[j*2+1] != null;
    }
    public boolean hasRight(int j) throws TreeNodeException {
        validateIndex(j);

        return !(j*2+2 < 0) && j*2+2 < capacity && array[j*2+2] != null;
    }

    public boolean isInternal(int j) throws TreeNodeException {
        validateIndex(j);

        if (hasLeft(j))
            return false;
        return true;
    }

    public boolean isExternal(int j) throws TreeNodeException {
        return !isInternal(j);
    }

    public boolean isEmpty() {
        return size == 0;
    }


    // _____Modification Methods_____

    public int addRoot(E e) throws TreeNodeException {
        ensureCapacity(1);
        if (array[0] != null)
            throw new TreeNodeAlreadyExistException();

        array[0] = new BTNode<>(e);
        size++;

        return 0;
    }

    public int addLeft(int j, E e) throws TreeNodeException {
        validateIndex(j);
        if (hasLeft(j))
            throw new TreeNodeAlreadyExistException();

        int l = j*2 + 1;
        ensureCapacity(l);
        array[l] = new BTNode<>(e);
        size++;

        return l;
    }

    public int addRight(int j, E e) throws TreeNodeException {
        validateIndex(j);
        if (hasRight(j))
            throw new TreeNodeAlreadyExistException();

        int r = j*2 + 2;
        ensureCapacity(r);
        array[r] = new BTNode<>(e);
        size++;

        return r;
    }

    public E set(int j, E e) throws TreeNodeException {
        validateIndex(j);

        E old = array[j].getValue();
        array[j].setValue(e);

        return old;
    }

    public static void main(String[] args) throws TreeNodeException, EmptyTreeException {
        ArrayBinaryTree<String> abt = new ArrayBinaryTree<>();

        abt.addRoot("R");
        abt.addLeft(0, "Rl");
        abt.addRight(0, "Rr");
        abt.addRight(2, "Rrr");

        System.out.println(abt.leftChild(0));
        System.out.println(abt.rightChild(0));
        System.out.println(abt.rightChild(2));
        System.out.println(abt.root());
    }



    private void ensureCapacity(int nextSize)   {
        if (capacity > nextSize) return;

        int newCapacity = nextSize * 2;

        if (array.length != 0)
            System.arraycopy(array, 0, array = new BTNode[newCapacity], 0, size);
        else
            array = new BTNode[newCapacity];

        capacity = newCapacity;

    }


}
