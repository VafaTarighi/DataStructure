package queue;

import list.DynamicList;

/**
 * Beta version of Queue implementation
 *
 */
public class Queue<E> {
    private final DynamicList<E> list = new DynamicList<>();


    // --------------- initialization methods -----------------
    public Queue<E> add(E obj) {
        list.add(obj);
        return this;
    }

    public E poll() {
        return list.remove(0);
    }

    public E peek() {
        return list.get(0);
    }

    public int size() {
        return list.size();
    }

}
