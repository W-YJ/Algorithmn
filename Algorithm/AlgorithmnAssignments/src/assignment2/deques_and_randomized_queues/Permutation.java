package assignment2.deques_and_randomized_queues;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
    	
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rq.enqueue(s);
        }
        final int n = Integer.parseInt(args[0]);
        for (int i = 0; i < n; i++) {
            System.out.print(rq.dequeue() + " ");
        }
    }

}
