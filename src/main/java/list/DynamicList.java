package list;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class DynamicList<T> {

    private int size = 0;
    private int capacity = 0;
    private T[] array;

    // -------------------- Constructors --------------------
    public DynamicList() {
        array = (T[]) new Object[0];
    }

    public DynamicList(int initialCapacity) {
        array = (T[]) new Object[initialCapacity];
    }

    public DynamicList(T[] arr) {
        array = (T[]) new Object[arr.length];
        size = arr.length;
        System.arraycopy(arr, 0, array, 0, array.length);
    }


    // --------------- access methods --------------
    public T get(int index) {
        validateIndex(index);

        return array[index];
    }

    public int size() {
        return size;
    }

    // --------------- initialization methods -----------------
    public DynamicList<T> add(T obj) {
        ensureCapacity(size + 1);

        array[size++] = obj;

        return this;
    }

    public void set(int index, T obj) {
        validateIndex(index);

        array[index] = obj;
    }

    public DynamicList<T> insert(int index, T obj) {

        if (index == size) {
            this.add(obj);
        } else {
            validateIndex(index);
            ensureCapacity(size + 1);
            System.arraycopy(array, index, array, index + 1, size - index);
            size++;
            this.set(index, obj);
        }

        return this;
    }

    public T remove(int index) {
        validateIndex(index);

        T removed = array[index];

        ensureTrim();
        System.arraycopy(array, index + 1, array, index, size - index - 1);

        size--;

        return removed;
    }

    public T shift() {
        return this.remove(0);
    }

    public DynamicList<T> unshift(T obj) {
        return this.insert(0, obj);
    }

    public T pop() {
        return this.remove(this.size - 1);
    }

    public DynamicList<T> push(T obj) {
        return this.add(obj);
    }

    public void clean() {
        array = (T[]) new Object[0];
        capacity = 0;
        size = 0;
    }

    public void trim() {
        array = Arrays.copyOf(array, size);
        capacity = size;
    }

    // ----------------- utility methods -------------------
    private void ensureCapacity(int nextSize)   {
        if (capacity >= nextSize) return;

        int newCapacity = nextSize * 3 / 2;

        if (array.length != 0)
            System.arraycopy(array, 0, array = (T[]) new Object[newCapacity], 0, size);
        else
            array = (T[]) new Object[newCapacity];

        capacity = newCapacity;

    }

    private void ensureTrim() {
        if (capacity >= size * 2)
            System.arraycopy(array, 0, array = (T[]) new Object[capacity = size * 4 / 3], 0, size);
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index: " + index + " for size: " + size);
    }


    @Override
    public String toString() {
        return "DynamicList" +
                 Arrays.toString(Arrays.copyOf(array, size));
    }
}
