package queue;

import list.SinglyLinkedList;

public class LinkedQueue<E> {
    private final SinglyLinkedList<E> list = new SinglyLinkedList<>();


    // --------------- initialization methods -----------------
    public LinkedQueue<E> enqueue(E obj) {
        list.addLast(obj);
        return this;
    }

    public E dequeue() {
        return list.removeFirst();
    }

    public E first() {
        return list.first();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

}
