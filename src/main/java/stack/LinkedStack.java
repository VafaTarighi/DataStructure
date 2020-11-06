package stack;

import list.SinglyLinkedList;

public class LinkedStack<E> {
    private final SinglyLinkedList<E> list = new SinglyLinkedList<>();

    public E top() {
        return list.first();
    }

    public void push(E e) {
        list.addFirst(e);
    }

    public E pop() {
        return list.removeFirst();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
