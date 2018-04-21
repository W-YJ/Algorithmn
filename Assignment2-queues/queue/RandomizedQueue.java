import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public RandomizedQueue() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }

    public int size() {
        Iterator<Item> iterator = this.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            n++;
            iterator.next();
        }
        return n;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            last = new Node();
            last.item = item;
            last.prev = null;
            last.next = null;
            first = last;
        } else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.prev = oldLast;
            last.next = null;
            oldLast.next = last;
        }
    }

    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        int n = StdRandom.uniform(this.size());
        Node target = first;
        for (int i = 0; i < n; i++) {
            target = target.next;
        }
        Item item = target.item;
        if (this.size() == 1) {
            first = null;
            last = null;
        } else if (this.size() > 1) {
            if (target == first) {
                first = target.next;
                target.next.prev = null;
            } else if (target == last) {
                last = target.prev;
                target.prev.next = null;
            } else {
                target.prev.next = target.next;
                target.next.prev = target.prev;
            }
        }
        return item;
    }

    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        int n = StdRandom.uniform(this.size());
        Node target = first;
        for (int i = 0; i < n; i++) {
            target = target.next;
        }
        Item item = target.item;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
