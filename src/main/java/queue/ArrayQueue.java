package queue;

/**
 * ArrayQueue implementation that uses an array internally
 *
 */

@SuppressWarnings("unchecked")
public class ArrayQueue<E> {

    private final E[] array;
    private int front = 0;
    private final int capacity;
    private int size = 0;

    /**
     * Public Constructor that creates an empty queue with specified capacity.
     *
     * @param capacity capacity of the queue
     */
    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        array = (E[]) new Object[capacity];
    }

    /**
     * Adds the specified element to the end of the queue.
     *
     * @param e element to be added to the queue
     * @return this queue
     */
    public ArrayQueue<E> enqueue(E e) {
        if (size == capacity)
            throw new IllegalStateException("Queue is full");

        array[(front + size)%capacity] = e;
        size++;

        return this;
    }

    /**
     * Removes and returns the next element in this queue.
     *
     * @return element that been removed
     */
    public E dequeue() {
        if (isEmpty())
            return null;

        E e = array[front];
        array[front] = null;
        front = (front + 1)%capacity;
        size--;

        return e;
    }

    /**
     * Returns the next first element in this array
     *
     * @return next first element in this array
     */
    public E first() {
        return array[front];
    }

    /**
     * Returns current size of this queue
     *
     * @return size of this queue
     */
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this queue contains no elements.
     *
     * @return {@code true} if this queue contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
