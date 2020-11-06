package stack;

import list.SinglyLinkedList;

/**
 * LinkedStack implementation that adapts a SinglyLinkedList internally.
 *
 */

public class LinkedStack<E> {
    private final SinglyLinkedList<E> list = new SinglyLinkedList<>();

    /**
     * Returns the top element of the stack or null if the stack is empty.
     *
     * @return the top element of the stack or null if the stack is empty
     */
    public E top() {
        return list.first();
    }

    /**
     * Pushes the specified element to the top of the stack
     *
     * @param e element to be pushed to the stack
     */
    public void push(E e) {
        list.addFirst(e);
    }

    /**
     * Popes and returns the top element of the stack or null if the stack is empty.
     *
     * @return the top element of the stack or null if the stack is empty
     */
    public E pop() {
        return list.removeFirst();
    }

    /**
     * Returns current size of this stack
     *
     * @return size of this queue
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns {@code true} if this stack contains no elements.
     *
     * @return {@code true} if this stack contains no elements
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
