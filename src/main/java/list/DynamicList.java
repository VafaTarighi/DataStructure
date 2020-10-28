package list;

import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings("unchecked")
public class DynamicList<E> {

    private int size = 0;
    private int capacity = 0;
    private E[] array;

    // -------------------- Constructors --------------------
    public DynamicList() {
        array = (E[]) new Object[0];
    }

    public DynamicList(int initialCapacity) {
        array = (E[]) new Object[initialCapacity];
    }

    public DynamicList(E[] arr) {
        array = (E[]) new Object[arr.length];
        size = arr.length;
        System.arraycopy(arr, 0, array, 0, array.length);
    }


    // --------------- access methods --------------
    public E get(int index) {
        validateIndex(index);

        return array[index];
    }

    public int size() {
        return size;
    }

    // --------------- initialization methods -----------------
    public DynamicList<E> add(E obj) {
        ensureCapacity(size + 1);

        array[size++] = obj;

        return this;
    }

    public void set(int index, E obj) {
        validateIndex(index);

        array[index] = obj;
    }

    public DynamicList<E> insert(int index, E obj) {

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

    public E remove(int index) {
        validateIndex(index);

        E removed = array[index];

        ensureTrim();
        System.arraycopy(array, index + 1, array, index, size - index - 1);

        size--;

        return removed;
    }

    public E shift() {
        return this.remove(0);
    }

    public DynamicList<E> unshift(E obj) {
        return this.insert(0, obj);
    }

    public E pop() {
        return this.remove(this.size - 1);
    }

    public DynamicList<E> push(E obj) {
        return this.add(obj);
    }

    public void clean() {
        array = (E[]) new Object[0];
        capacity = 0;
        size = 0;
    }

    public void trim() {
        array = Arrays.copyOf(array, size);
        capacity = size;
    }

    /**
     * Method for sorting the list using Bubble-Sort algorithm
     * @param cmp Item Comparator
     */
    public void sort(Comparator<? super E> cmp) {
        E tmp;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (cmp.compare(array[j], array[j + 1]) > 0) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * Appends {@code other} to the end of {@code this} and  and return a new list
     * of size {@code this.size + other.size}
     * @param other DynamicList to append to the end of {@code this}
     * @return {@code DynamicList} that consists of {@code this.append(other)}
     */
    public DynamicList<E> append(DynamicList<E> other) {
        int resultSize = this.size + other.size;
        E[] resultArray = (E[]) new Object[resultSize];

        System.arraycopy(this.array, 0, resultArray, 0, this.size);
        System.arraycopy(other.array, 0, resultArray, this.size, other.size);

        DynamicList<E> resultList = new DynamicList<>();
        resultList.size = resultSize;
        resultList.capacity = resultSize;
        resultList.array = resultArray;

        return resultList;
    }

    public static void main(String[] args) {
        DynamicList<Integer> l1 = new DynamicList<>();
        l1.add(1).add(2).add(3);
        DynamicList<Integer> l2 = new DynamicList<>();
        l2.add(4).add(5).add(6);

        System.out.println(l2.append(l1));
        System.out.println(l2.merge(l1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }));
    }

    public DynamicList<E> merge(DynamicList<E> other, Comparator<? super E> cmp) {
        DynamicList<E> result = this.append(other);
        result.sort(cmp);
        return result;
    }

    // ----------------- utility methods -------------------
    private void ensureCapacity(int nextSize)   {
        if (capacity >= nextSize) return;

        int newCapacity = nextSize * 3 / 2;

        if (array.length != 0)
            System.arraycopy(array, 0, array = (E[]) new Object[newCapacity], 0, size);
        else
            array = (E[]) new Object[newCapacity];

        capacity = newCapacity;

    }

    private void ensureTrim() {
        if (capacity >= size * 2)
            System.arraycopy(array, 0, array = (E[]) new Object[capacity = size * 4 / 3], 0, size);
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
