package list;

import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings("unchecked")
public class DynamicList<E> {

    private int size = 0;
    private int capacity = 0;
    private E[] array;
    private boolean AUTO_TRIM = false;

    // -------------------- Constructors --------------------

    /**
     * Constructor to create an empty {@code DynamicList}.
     */
    public DynamicList() {
        array = (E[]) new Object[0];
    }

    /**
     * Constructor that creates an empty {@code DynamicList} with the specified initial capacity.
     * @param initialCapacity the initial capacity of the {@code DynamicList}
     */
    public DynamicList(int initialCapacity) {
        array = (E[]) new Object[initialCapacity];
    }

    /**
     * Constructor that creates a {@code DynamicList} from the specified array of data.
     * @param arr array of data
     */
    public DynamicList(E[] arr) {
        array = (E[]) new Object[arr.length];
        size = arr.length;
        System.arraycopy(arr, 0, array, 0, array.length);
    }


    // --------------- access methods --------------

    /**
     * Returns the element at the specified position in this {@code DynamicList}.
     * @param index index of the element in the {@code DynamicList}
     * @return the element
     */
    public E get(int index) {
        validateIndex(index);

        return array[index];
    }

    /**
     * Returns the current size of the {@code DynamicList}.
     * @return current size of the list
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

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(e))
                return true;
        }

        return false;
    }

    // --------------- initialization methods -----------------

    /**
     * Adds the element {@code E obj} to the end of the list.
     * @param obj element to be added to the list
     * @return {@code this} list
     */
    public DynamicList<E> add(E obj) {
        ensureCapacity(size + 1);

        array[size++] = obj;

        return this;
    }

    public void addAll(DynamicList<E> other) {
        for (int i = 0; i < other.size(); i++) {
            this.add(other.get(i));
        }
    }

    /**
     * Replaces the element at the specified position in this {@code DynamicList} with the specified element.
     * @param index index of the element to be replaced
     * @param obj new value of the specified position in the list
     */
    public void set(int index, E obj) {
        validateIndex(index);

        array[index] = obj;
    }

    /**
     * Inserts an element to the specified position in the list.
     * @param index position of list to insert the specified element
     * @param obj the element to be inserted
     * @return {@code this} list
     */
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

    /**
     * Removes the element at the specified position in the {@code DynamicList}.
     * @param index index of the element to be removed in the list
     * @return the removed element
     */
    public E remove(int index) {
        validateIndex(index);

        E removed = array[index];

        if (AUTO_TRIM)
            ensureTrim();

        System.arraycopy(array, index + 1, array, index, size - index - 1);

        size--;

        return removed;
    }

    /**
     * Removes the first element of the list and sifts remaining element to left left.
     * @return first element of list that removed
     */
    public E shift() {
        return this.remove(0);
    }

    /**
     * Inserts the specified element to the beginning of the list.
     * @param obj element to be inserted
     * @return {@code this} list
     */
    public DynamicList<E> unshift(E obj) {
        return this.insert(0, obj);
    }

    /**
     * Removes the last element of the list and returns it
     * @return last element of the list that been removed
     */
    public E removeLast() {
        return this.remove(this.size - 1);
    }

    /**
     * Adds the element {@code E obj} to the end of the list.
     * @param obj element to be added to the list
     * @return {@code this} list
     */
    public DynamicList<E> addLast(E obj) {
        return this.add(obj);
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call return
     */
    public void clean() {
        array = (E[]) new Object[0];
        capacity = 0;
        size = 0;
    }

    public void reset() {
        Arrays.fill(array, 0, size, null);
        size = 0;
    }

    public void setAutoTrim(boolean mode) {
        AUTO_TRIM = mode;
    }

    /**
     * Trims the list to its current size
     */
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

    /**
     * Returns a new sorted {@code DynamicList} that contains all of elements
     * inside {@code this} and {@code other} using specified comparator.
     * @param other other list to be merged with {@code this} list
     * @param cmp comparator to sort the resulting list
     * @return new sorted list containing elements of two specified lists
     */
    public DynamicList<E> merge(DynamicList<E> other, Comparator<? super E> cmp) {
        DynamicList<E> result = this.append(other);
        result.sort(cmp);
        return result;
    }

    // ----------------- utility methods -------------------

    /**
     * Ensures current capacity of the list before adding new elements to the list.
     * Increases capacity of the inner array with growth factor of 3/2 if needed.
     * (For internal usage)
     * @param nextSize minimum capacity needed for adding new element to the list
     */
    private void ensureCapacity(int nextSize)   {
        if (capacity >= nextSize) return;

        int newCapacity = nextSize * 3 / 2;

        if (array.length != 0)
            System.arraycopy(array, 0, array = (E[]) new Object[newCapacity], 0, size);
        else
            array = (E[]) new Object[newCapacity];

        capacity = newCapacity;

    }

    /**
     * for collapsing the inner array to it's currentSize * 4/3
     * (for internal usage)
     */
    private void ensureTrim() {
        if (capacity >= size * 2)
            System.arraycopy(array, 0, array = (E[]) new Object[capacity = size * 4 / 3], 0, size);
    }

    /**
     * validates index of the element of the list to be accessed.
     * @param index position of the list to be accessed
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index: " + index + " for size: " + size);
    }


    @Override
    public String toString() {
        return "List" +
                 Arrays.toString(Arrays.copyOf(array, size));
    }
}
