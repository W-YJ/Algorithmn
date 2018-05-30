package com.iamwyj.week2.fundamentaldatastructures;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStack<Item> {

	private Item[] s;
	private int N = 0;

	public FixedCapacityStack(int capacity) {
		s = (Item[]) new Object[capacity];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public void push(Item item) {
		s[N++] = item;
	}

	public Item pop() {
		Item item = s[--N];
		s[N] = null;
		return item;
	}

	public static void main(String[] args) {
		FixedCapacityStack<String> stack = new FixedCapacityStack<String>(10);
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("-"))
				StdOut.print(stack.pop());
			else
				stack.push(s);
		}
	}

}
