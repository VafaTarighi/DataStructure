package queue;

import list.SinglyLinkedList;

/**
 * LinkedQueue implementation that adapts an SinglyLinkedList internally
 *
 */

public class LinkedQueue<E> {
    private final SinglyLinkedList<E> list = new SinglyLinkedList<>();


    /**
     * Adds the specified element to the end of the queue.
     *
     * @param e element to be added to the queue
     * @return this queue
     */
    public LinkedQueue<E> enqueue(E e) {
        list.addLast(e);
        return this;
    }

    /**
     * Removes and returns the next element in this queue.
     *
     * @return element that been removed
     */
    public E dequeue() {
        return list.removeFirst();
    }

    /**
     * Returns the next first element in this array
     *
     * @return next first element in this array
     */
    public E first() {
        return list.first();
    }

    /**
     * Returns {@code true} if this queue contains no elements.
     *
     * @return {@code true} if this queue contains no elements
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns current size of this queue
     *
     * @return size of this queue
     */
    public int size() {
        return list.size();
    }

}
