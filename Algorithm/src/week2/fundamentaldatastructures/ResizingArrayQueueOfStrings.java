package com.iamwyj.week2.fundamentaldatastructures;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class ResizingArrayQueueOfStrings {

	private String[] q;
	private int head = 0;
	private int tail = 0;

	public ResizingArrayQueueOfStrings() {
		q = new String[1];
	}

	public void enqueue(String item) {
		if ((tail + 1) % q.length == head) {
			bigger(2 * q.length);
		}
		q[(tail++) % q.length] = item;
	}

	public String dequeue() {
		if (isEmpty()) {
			return null;
		}
		String item = q[head++];
		if ((tail - head) > 0 && (tail - head) == q.length / 4) {
			smaller(q.length / 2);
		}
		return item;
	}

	public boolean isEmpty() {
		return head == tail;
	}

	/**
	 * repeat doubling
	 * 
	 * @param capacity
	 */
	private void bigger(int capacity) {
		String[] copy = new String[capacity];
		for (int i = head; i < head + q.length; i++) {
			copy[i] = q[i % q.length];
		}
		q = copy;

	}

	private void smaller(int capacity) {

		String[] copy = new String[capacity];
		for (int i = head; i <= tail; i++) {
			copy[i % copy.length] = q[i];
		}
		q = copy;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
