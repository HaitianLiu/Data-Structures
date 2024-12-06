public class LinkedListDeque<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node temp = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size += 1;
    }

    public void addLast(T item) {
        Node temp = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }

    public void printDeque() {
        Node temp = sentinel.next;
        while (temp != sentinel) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
    }
    // **   Remove the first item and return it. **/
    public T removeFirst() {
        if (sentinel.next.item == null) {
            return null;
        }

        T first = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return first;
    }


    //**    Remove the last item and return it. **/
    public T removeLast() {
        if (sentinel.prev.item == null) {
            return null;
        }
        T last = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return last;
    }

    public T get(int index) {
        Node curr = sentinel.next;
        if (index > size - 1) {
            return null;
        }
        for (int i = 0; i < index; i += 1) {
            curr = curr.next;
        }
        return curr.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getRecur(index, sentinel.next);
    }
    private T getRecur(int index, Node temp) {
        if (index == 0) {
            return temp.item;
        }
        return getRecur(index - 1, temp.next);
    }

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> test = new LinkedListDeque<>();
//        test.addFirst(100);
//        test.addLast(444);
//        test.addFirst(888);
//        test.addLast(456);
////        test.printDeque();
//        System.out.print(test.get(2));
//        test.removeLast();
//        System.out.print(test);
//    }


}
