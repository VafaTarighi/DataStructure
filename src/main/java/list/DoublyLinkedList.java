package list;

import java.util.Deque;
import java.util.LinkedList;

public class DoublyLinkedList<E> {
    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;
    }

    private final Node<E> header;
    private final Node<E> trailer;
    private int size;

    public DoublyLinkedList() {
        header = new Node<>();
        trailer = new Node<>();

        header.data = null;
        trailer.data = null;

        header.prev = null;
        header.next = trailer;

        trailer.prev = header;
        trailer.next = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        return header.next.data;
    }

    public E last() {
        return trailer.prev.data;
    }

    public void addFirst(E e) {
        Node<E> node = new Node<>();
        node.data = e;
        node.prev = header;
        node.next = header.next;
        header.next.prev = node;
        header.next = node;
        size++;
    }

    public void addLast(E e) {
        Node<E> node = new Node<>();
        node.data = e;
        node.next = trailer;
        node.prev = trailer.prev;
        trailer.prev.next = node;
        trailer.prev = node;
        size++;
    }

    public E removeFirst() {
        if (isEmpty())
            return null;

        E e = header.next.data;
        header.next.next.prev = header;
        header.next = header.next.next;
        size--;

        return e;
    }

    public E removeLast() {
        if (isEmpty())
            return null;

        E e = trailer.prev.data;
        trailer.prev.prev.next = trailer;
        trailer.prev = trailer.prev.prev;
        size--;

        return e;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DoublyLinkedList { ");
        Node<E> node = header;
        for (int i = 0; i < size; i++) {
            node = node.next;
            sb.append(node.data).append(", ");
        }

        if (!isEmpty())
            sb.delete(sb.length()-2, sb.length());

        return sb.append(" }").toString();
    }

}
