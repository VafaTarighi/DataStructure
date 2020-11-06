package list;

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


    public E first() {
        return isEmpty()? null : head.value;
    }

    public E last() {
        return isEmpty()? null : tail.value;
    }

    public void addFirst(E e) {
        head = new Node<>(e, head);
        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

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

    public E removeFirst() {
        if (isEmpty()) return null;

        Node<E> head = this.head;
        this.head = head.next;
        size--;
        return head.value;
    }

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

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
