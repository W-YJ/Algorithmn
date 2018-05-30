package assignment2.deques_and_randomized_queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node first;
	private Node last;

	private class Node {
		Item item;
		Node prev;
		Node next;
	}

	public Deque() {
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

	public void addFirst(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}

		if (isEmpty()) {
			first = new Node();
			first.item = item;
			first.prev = null;
			first.next = null;
			last = first;
		} else {
			Node oldFirst = first;
			first = new Node();
			first.item = item;
			first.prev = null;
			first.next = oldFirst;
			oldFirst.prev = first;
		}
	}

	public void addLast(Item item) {
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

	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item item = first.item;
		if (first == last) {
			first = null;
			last = null;
		} else {
			first = first.next;
			first.prev = null;
		}
		return item;
	}

	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item item = last.item;
		if (first == last) {
			first = null;
			last = null;
		} else {
			last = last.prev;
			last.next = null;
		}
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
