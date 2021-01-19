package Heap;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriorityQueue<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V> {

    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    public HeapPriorityQueue() {
        super();
    }
    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    protected int parent(int j) {
        return (j - 1)/2;
    }
    protected int left(int j) {
        return j*2 + 1;
    }
    protected int right(int j) {
        return j*2 +2;
    }
    protected boolean hasLeft(int j) {
        return left(j) < heap.size();
    }
    protected boolean hasRight(int j) {
        return right(j) < heap.size();
    }

    protected void swap(int i, int j) {
        
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Entry<K, V> min() {
        return null;
    }

    @Override
    public Entry<K, V> removeMin() {
        return null;
    }
}
