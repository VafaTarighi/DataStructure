package list;

import java.util.Comparator;

public class SortedList<E> {

    private final Comparator<E> cmp;
    private DynamicList<E> list;

    /**
     * Public constructor that creates an empty SortedList.
     *
     * @param cmp Comparator used for keep the list sorted
     */
    public SortedList(Comparator<E> cmp) {
        this.list = new DynamicList<>();
        this.cmp = cmp;
    }

    /**
     * Private constructor used in merge method to create a new list.
     *
     * @param list new SortedList
     * @param cmp element comparator
     */
    private SortedList(DynamicList<E> list, Comparator<E> cmp) {
        this.list = list;
        this.cmp = cmp;
    }

    /**
     * Inserts the specified element to the proper position in this list.
     *
     * @param obj element to be added
     * @return {@code this} list
     */
    public SortedList<E> add(E obj) {
        int size = list.size();
        for (int i = 0; i <= size; i++) {
            if (i == size || cmp.compare(list.get(i), obj) > 0) {
                list.insert(i, obj);
                break;
            }
        }
        return this;
    }

    /**
     * Removes the element with the specified index in the list.
     *
     * @param index index of the element
     * @return removed element
     */
    public E remove(int index) {
        return list.remove(index);
    }

    /**
     * Merges two SortedList with each other and return a new list.
     *
     * @param other the other list to be merged with this list
     * @return new list containing all of the items of this list and the other one
     */
    public SortedList<E> merge(SortedList<E> other) {
        return new SortedList<>(list.merge(other.list, cmp), cmp);
    }

    /**
     * Returns current size of this list
     *
     * @return size of this list
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns an string representation of this list
     *
     * @return string representation of this list
     */
    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {
        SortedList<Integer> list = new SortedList<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        list.add(9);
        list.add(1);
        list.add(4);
        list.add(9);
        list.add(7);
        list.add(2);
        list.add(3);
        list.add(8);
        list.add(5);
    }
}
