package stack;

@SuppressWarnings("unchecked")
public class ArrayStack<E> {
    private final E[] array;
    private int top = -1;

    public ArrayStack(int capacity) {
        array = (E[]) new Object[capacity];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(E e) {
        if (size() == array.length)
            throw new IllegalStateException("Stack is full");

        array[++top] = e;
    }

    public E peek() {
        if (isEmpty())
            return null;

        return array[top];
    }

    public E pop() {
        if (isEmpty())
            return null;

        E e = array[top];
        array[top--] = null;

        return e;
    }
}
