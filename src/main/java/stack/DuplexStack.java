package stack;

import java.util.Arrays;

/**
 * DuplexStack implementation that there is two stacks witch use one common array
 *
 */

@SuppressWarnings("unchecked")
public class DuplexStack<E> {
    private final E[] array;
    private int top1;
    private int top2;

    /**
     * Public constructor that creates two empty stacks with specified common capacity
     * @param capacity capacity of the two stacks
     */
    public DuplexStack(int capacity) {
        array = (E[]) new Object[capacity];
        top1 = -1;
        top2 = capacity;
    }

    /**
     * Returns size of the stack-no.1
     * @return  size of the stack-no.1
     */
    public int size1() {
        return top1 + 1;
    }

    /**
     * Returns size of the stack-no.2
     * @return  size of the stack-no.2
     */
    public int size2() {
        return array.length - top2;
    }

    /**
     * Returns {@code true} if this stack-no.1 contains no elements.
     *
     * @return {@code true} if this stack-no.1 contains no elements
     */
    public boolean isEmpty1() {
        return top1 == -1;
    }

    /**
     * Returns {@code true} if this stack-no.2 contains no elements.
     *
     * @return {@code true} if this stack-no.2 contains no elements
     */
    public boolean isEmpty2() {
        return top2 == array.length;
    }

    /**
     * Returns {@code true} if the capacity of the stacks is full.
     *
     * @return {@code true} if the capacity of the stacks is full
     */
    public boolean isFull() {
        return top1 + 1 == top2;
    }

    /**
     * Pushes the specified element to the top of the stack-no.1
     *
     * @param e element to be pushed to the stack-no.1
     */
    public void push1(E e) {
        if (isFull())
            throw new IllegalStateException("Stack is full");

        array[++top1] = e;
    }

    /**
     * Pushes the specified element to the top of the stack-no.2
     *
     * @param e element to be pushed to the stack-no.2
     */
    public void push2(E e) {
        if (isFull())
            throw new IllegalStateException("Stack is full");

        array[--top2] = e;
    }

    /**
     * Returns the top element of the stack-no.1 or null if the stack is empty.
     *
     * @return the top element of the stack-no.1 or null if the stack is empty
     */
    public E peek1() {
        if (isEmpty1())
            return null;

        return array[top1];
    }

    /**
     * Returns the top element of the stack-no.2 or null if the stack is empty.
     *
     * @return the top element of the stack-no.2 or null if the stack is empty
     */
    public E peek2() {
        if (isEmpty2())
            return null;

        return array[top2];
    }

    /**
     * Popes and returns the top element of the stack-no.1 or null if the stack is empty.
     *
     * @return the top element of the stack.no.1 or null if the stack is empty
     */
    public E pop1() {
        if (isEmpty1())
            return null;

        E e = array[top1];
        array[top1--] = null;

        return e;
    }

    /**
     * Popes and returns the top element of the stack-no.2 or null if the stack is empty.
     *
     * @return the top element of the stack-no.2 or null if the stack is empty
     */
    public E pop2() {
        if (isEmpty2())
            return null;

        E e = array[top2];
        array[top2++] = null;

        return e;
    }

    /**
     * Removes all the element of the stack-no.1
     */
    public void clean1() {
        Arrays.fill(array, 0, size1(), null);
    }

    /**
     * Removes all the element of the stack-no.2
     */
    public void clean2() {
        Arrays.fill(array, top2, top2 + size2(), null);
    }

    @Override
    public String toString() {
        return "DuplexStack{\n      " +
                Arrays.toString(array) +
                "\n      top1=" + top1 +
                "\n      top2=" + top2 +
                "\n   }";
    }
}
