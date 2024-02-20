package Queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        items[size] = item;

        size++;

        if (size == items.length) {
            resize(items.length * 2);
        }
    }

    private void resize(int s) {
        Item[] temp = (Item[]) new Object[s];
        for (int i = 0; i < size; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0)
            throw new NoSuchElementException();

        int index = (int) (StdRandom.uniform() * size);
        Item item = items[index];
        items[index] = items[size - 1];
        items[size - 1] = null;

        size--;

        if (size <= items.length / 4) {
            resize(items.length / 2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0)
            throw new NoSuchElementException();

        return items[(int) (StdRandom.uniform() * size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int cur = -1;
        private Item[] shuffled = (Item[]) new Object[size];

        public ListIterator() {

            for (int i = 0; i < size; i++) {
                shuffled[i] = items[i];
            }

            for (int i = 0; i < size; i++) {
                int randIndex = (int) (StdRandom.uniform() * (size - i)) + i;

                Item tempItem = shuffled[randIndex];
                shuffled[randIndex] = shuffled[i];
                shuffled[i] = tempItem;

            }

        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            cur++;
            return shuffled[cur];
        }

        @Override
        public boolean hasNext() {
            return cur + 1 < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        System.out.println(randomizedQueue.isEmpty());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.size());
        System.out.println(randomizedQueue.dequeue());

        for (Integer i : randomizedQueue) {
            System.out.println(i);
        }

    }

}
