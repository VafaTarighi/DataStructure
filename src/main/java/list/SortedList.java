package list;

import java.util.Comparator;

public class SortedList<E> {

    private final Comparator<E> cmp;
    private final DynamicList<E> list = new DynamicList<>();

    public SortedList(Comparator<E> cmp) {
        this.cmp = cmp;
    }

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

    public E remove(int index) {
        return list.remove(index);
    }

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
