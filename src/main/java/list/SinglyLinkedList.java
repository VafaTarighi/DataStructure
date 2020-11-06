package list;

/**
 * Singly Linked List Implementation
 *
 */

public class SinglyLinkedList<E> {

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E e, Node<E> next) {
            value = e;
            this.next = next;
        }

    }

    private Node<E> head;
    private Node<E> tail;
    private int size;


    /**
     * Returns fist element of this list or null if the list is empty.
     *
     * @return first element in this list or null if the list is empty
     */
    public E first() {
        return isEmpty()? null : head.value;
    }

    /**
     * Returns last element of this list or null if the list is empty.
     *
     * @return last element of this list or null if the list is empty
     */
    public E last() {
        return isEmpty()? null : tail.value;
    }

    /**
     * Inserts the specified element at the begging of the list.
     *
     * @param e the element to add
     */
    public void addFirst(E e) {
        head = new Node<>(e, head);
        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    /**
     * Inserts the specified element at the end of the list.
     *
     * @param e the element to add
     */
    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.next = newest;
        }
        tail = newest;
        size++;
    }

    /**
     * Removes and returns the element at the begging of this list.
     *
     * @return the element at the begging of this list
     */
    public E removeFirst() {
        if (isEmpty()) return null;

        Node<E> head = this.head;
        this.head = head.next;
        size--;
        return head.value;
    }

    /**
     * An implementation of {@code toString()} method for SinglyLinkedList.
     *
     * @return String representation of this list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Collection { ");

        if (!isEmpty()) {
            Node<E> curr = head;
            do {
                sb.append(curr.value).append(", ");
            } while ((curr = curr.next) != null);
            sb.delete(sb.length() - 2, sb.length());
        }

        return sb.append(" }").toString();
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
}
