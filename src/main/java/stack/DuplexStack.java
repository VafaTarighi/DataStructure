package stack;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class DuplexStack<E> {
    private final E[] array;
    private int top1;
    private int top2;

    public DuplexStack(int capacity) {
        array = (E[]) new Object[capacity];
        top1 = -1;
        top2 = capacity;
    }

    public int size1() {
        return top1 + 1;
    }

    public int size2() {
        return array.length - top2;
    }

    public boolean isEmpty1() {
        return top1 == -1;
    }

    public boolean isEmpty2() {
        return top2 == array.length;
    }

    public boolean isFull() {
        return top1 + 1 == top2;
    }

    public void push1(E e) {
        if (isFull())
            throw new IllegalStateException("Stack is full");

        array[++top1] = e;
    }

    public void push2(E e) {
        if (isFull())
            throw new IllegalStateException("Stack is full");

        array[--top2] = e;
    }

    public E peek1() {
        if (isEmpty1())
            return null;

        return array[top1];
    }

    public E peek2() {
        if (isEmpty2())
            return null;

        return array[top2];
    }

    public E pop1() {
        if (isEmpty1())
            return null;

        E e = array[top1];
        array[top1--] = null;

        return e;
    }

    public E pop2() {
        if (isEmpty2())
            return null;

        E e = array[top2];
        array[top2++] = null;

        return e;
    }

    public void clean1() {
        Arrays.fill(array, 0, size1(), null);
    }

    public void clean2() {
        Arrays.fill(array, top2, top2 + size2(), null);
    }

    @Override
    public String toString() {
        return "DuplexStack{\n" +
                "array=" + Arrays.toString(array) +
                "\n, top1=" + top1 +
                "\n, top2=" + top2 +
                "\n}";
    }
}
