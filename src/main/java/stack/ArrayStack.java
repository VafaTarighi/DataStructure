package stack;

/**
 * ArrayStack implementation that uses an array internally
 *
 */

@SuppressWarnings("unchecked")
public class ArrayStack<E> {
    private final E[] array;
    private int top = -1;

    /**
     * Public Constructor that creates an empty stack with specified capacity.
     *
     * @param capacity capacity of the stack
     */
    public ArrayStack(int capacity) {
        array = (E[]) new Object[capacity];
    }

    /**
     * Returns current size of this stack
     *
     * @return size of this queue
     */
    public int size() {
        return top + 1;
    }

    /**
     * Returns {@code true} if this stack contains no elements.
     *
     * @return {@code true} if this stack contains no elements
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Pushes the specified element to the top of the stack
     *
     * @param e element to be pushed to the stack
     */
    public void push(E e) {
        if (size() == array.length)
            throw new IllegalStateException("Stack is full");

        array[++top] = e;
    }

    /**
     * Returns the top element of the stack or null if the stack is empty.
     *
     * @return the top element of the stack or null if the stack is empty
     */
    public E peek() {
        if (isEmpty())
            return null;

        return array[top];
    }

    /**
     * Popes and returns the top element of the stack or null if the stack is empty.
     *
     * @return the top element of the stack or null if the stack is empty
     */
    public E pop() {
        if (isEmpty())
            return null;

        E e = array[top];
        array[top--] = null;

        return e;
    }
}
