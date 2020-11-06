package queue;

@SuppressWarnings("unchecked")
public class ArrayQueue<E> {

    private final E[] array;
    private int front = 0;
    private final int capacity;
    private int size = 0;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        array = (E[]) new Object[capacity];
    }

    public ArrayQueue<E> enqueue(E e) {
        if (size == capacity)
            throw new IllegalStateException("Queue is full");

        array[(front + size)%capacity] = e;
        size++;

        return this;
    }

    public E dequeue() {
        if (isEmpty())
            return null;

        E e = array[front];
        array[front] = null;
        front = (front + 1)%capacity;
        size--;

        return e;
    }

    public E first() {
        return array[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
