package Part1.week2.fundamentaldatastructures;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] s;
    private int N = 0;

    public ResizingArrayStack() {
        this.s = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        if (N == s.length)
            resize(2 * s.length);
        s[N++] = item;
    }

    public Item pop() {
        if (!isEmpty()) {
            throw new EmptyStackException();
        }
        Item item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4)
            resize(s.length / 2);
        return item;
    }

    // repeated doubling
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            /* not supported */
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (i <= 0) {
                throw new NoSuchElementException();
            }
            return s[--i];
        }

    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(stack.pop());
            else
                stack.push(s);
        }

    }

}
