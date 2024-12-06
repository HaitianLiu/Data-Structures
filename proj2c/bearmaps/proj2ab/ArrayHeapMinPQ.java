package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<PriorityNode> items;
    private HashMap<T, Integer> maps;

    //@sourse: NaiveMinPQ
    private class PriorityNode {
        private T item;
        private double priority;

        PriorityNode(T t, double d) {
            this.item = t;
            this.priority = d;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }
    }

    public ArrayHeapMinPQ() {
        items =  new ArrayList<>();
        items.add(0, null);
        maps = new HashMap<>();
    }

    @Override
    public boolean contains(T item) {
        return maps.containsKey(item);
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        items.add(new PriorityNode(item, priority));
        maps.put(item, size());
        swim(size());
    }

    private void swim(int index) {
        if (index == 1) {
            return;
        }
        if (parent(index) > 0 && items.get(index).priority < items.get(parent(index)).priority) {
            swap(index, parent(index));
            swim(parent(index));
        }
    }

    private void swap(int i, int j) {
        PriorityNode tempi = items.get(i);
        PriorityNode tempj = items.get(j);
        items.set(i, tempj);
        items.set(j, tempi);
        maps.put(tempi.item, j);
        maps.put(tempj.item, i);
    }

    private int parent(int k) {
        return k / 2;
    }
    private int leftChild(int k) {
        return k * 2;
    }
    private int rightChild(int k) {
        return k * 2 + 1;
    }

    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return items.get(1).item;
    }

    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        T temp = getSmallest();
        swap(1, size());
        items.remove(size());
        maps.remove(temp);
        sink(1);
        return temp;
    }

    private void sink(int index) {
        int small = index;
        if (leftChild(index) <= size() && items.get(leftChild(index)).priority < items.get(index).priority) {
            small = leftChild(index);
        }
        if (rightChild(index) <= size() && items.get(rightChild(index)).priority < items.get(small).priority) {
            small = rightChild(index);
        }
        if (small != index) {
            swap(index, small);
            sink(small);
        }
    }

    @Override
    public int size() {
        return items.size() - 1;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        int index = maps.get(item);
        double oldPriority = items.get(index).priority;
        items.get(index).setPriority(priority);
        if (oldPriority > priority) {
            swim(index);
        }
        if (oldPriority < priority) {
            sink(index);
        }
    }
}
