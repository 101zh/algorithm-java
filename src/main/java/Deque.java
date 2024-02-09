import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private static class Node<Item> {
        Item item;
        Node<Item> down;
        Node<Item> up;
    }

    public Node<Item> head;
    public Node<Item> tail;
    private int size;

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> newNode = new Node<>();
        newNode.item = item;
        newNode.down = head;

        if (size == 0) {
            tail = newNode;
        } else {
            head.up = newNode;
        }

        head = newNode;

        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> newNode = new Node<>();
        newNode.item = item;
        newNode.up = tail;

        if (size == 0) {
            head = newNode;
        } else {
            tail.down = newNode;
        }

        tail = newNode;

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        size--;

        Item item = head.item;
        head = head.down;
        if (size > 1)
            head.up = null;
        else
            tail = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        size--;

        Item item = tail.item;
        tail = tail.up;
        if (size > 0)
            tail.down = null;
        else
            head = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node<Item> current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            Item item = current.item;
            current = current.down;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        // deque.addFirst(null);
        deque.addFirst(1);
        deque.addLast(10);
        for (Integer i : deque) {
            System.out.println(i);
        }

        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.size());
        System.out.println(deque.isEmpty());
    }

}
