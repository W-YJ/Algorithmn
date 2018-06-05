package Part1.week6.symboltableapplications;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BlackList {
    public static void main(String[] args) {
        // create a empty set of strings
        SET<String> set = new SET<String>();

        // read in whitelist
        In in = new In(args[0]);
        while(!in.isEmpty())
            set.add(in.readString());

        // print words not in the list
        while(!StdIn.isEmpty()){
            String word = StdIn.readString();
            if(!set.contains(word))
                StdOut.println(word);
        }
    }
}
