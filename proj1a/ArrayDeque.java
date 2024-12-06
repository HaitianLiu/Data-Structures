public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextfirst;
    private int nextlast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextfirst = 0;
        nextlast = 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }

    private int moveback(int i) {
        return (i - 1 + items.length) % items.length;
    }

    private int movenext(int i) {
        return (i + 1) % items.length;
    }


    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextfirst] = item;
        nextfirst = moveback(nextfirst);
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextlast] = item;
        nextlast = movenext(nextlast);
        size += 1;
    }

    public void printDeque() {
        int pos = movenext(nextfirst);
        for (int i = 0; i < size; i += 1) {
            System.out.print(items[pos] + " ");
            pos = movenext(pos);
        }

    }


    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextfirst = movenext(nextfirst);
        T first = items[nextfirst];
        items[nextfirst] = null;
        size -= 1;
        if (needReduce()) {
            resize(items.length / 2);
        }

        return first;
    }


    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextlast = moveback(nextlast);
        T last = items[nextlast];
        items[nextlast] = null;
        size -= 1;
        if (needReduce()) {
            resize(items.length / 2);
        }

        return last;
    }


    public T get(int index) {
        if (index > size) {
            return null;
        }
        return items[movenext(nextfirst + index)];
    }

    private boolean needReduce() {
        return size < (items.length / 4) & items.length > 16;
    }


    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        int pos = movenext(nextfirst);
        for (int i = 0; i < size; i += 1) {
            a[i] = items[pos];
            pos = movenext(pos);
        }
        items = a;
        nextfirst = cap - 1;
        nextlast = size;
    }
}
