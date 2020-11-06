package list;

/**
 * Doubly Linked List Implementation
 *
 */

public class DoublyLinkedList<E> {

    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;
    }

    private final Node<E> header;
    private final Node<E> trailer;
    private int size;

    /**
     * Public Constructor that creates an empty list.
     */
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

    /**
     * Returns current size of this list
     *
     * @return size of this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns fist element of this list or null if the list is empty.
     *
     * @return first element in this list or null if the list is empty
     */
    public E first() {
        return header.next.data;
    }

    /**
     * Returns last element of this list or null if the list is empty.
     *
     * @return last element of this list or null if the list is empty
     */
    public E last() {
        return trailer.prev.data;
    }

    /**
     * Inserts the specified element at the begging of the list.
     *
     * @param e the element to add
     */
    public void addFirst(E e) {
        Node<E> node = new Node<>();
        node.data = e;
        node.prev = header;
        node.next = header.next;
        header.next.prev = node;
        header.next = node;
        size++;
    }

    /**
     * Inserts the specified element at the end of the list.
     *
     * @param e the element to add
     */
    public void addLast(E e) {
        Node<E> node = new Node<>();
        node.data = e;
        node.next = trailer;
        node.prev = trailer.prev;
        trailer.prev.next = node;
        trailer.prev = node;
        size++;
    }

    /**
     * Removes and returns the element at the begging of this list.
     *
     * @return the element at the begging of this list
     */
    public E removeFirst() {
        if (isEmpty())
            return null;

        E e = header.next.data;
        header.next.next.prev = header;
        header.next = header.next.next;
        size--;

        return e;
    }

    /**
     * Removes and returns the element at the end of this list.
     *
     * @return the element at the end of this list
     */
    public E removeLast() {
        if (isEmpty())
            return null;

        E e = trailer.prev.data;
        trailer.prev.prev.next = trailer;
        trailer.prev = trailer.prev.prev;
        size--;

        return e;
    }

    /**
     * An implementation of {@code toString()} method for DoublyLinkedList.
     *
     * @return String representation of this list
     */
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
