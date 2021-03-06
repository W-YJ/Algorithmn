package Part1.week6.symboltableapplications;

import edu.princeton.cs.algs4.*;

import java.io.File;

public class FileIndex {
    public static void main(String[] args) {
        // Symbol table
        ST<String, SET<File>> st = new ST<String, SET<File>>();

        // list of file names from command line
        for (String filename : args) {
            File file = new File(filename);
            In in = new In(file);

            // for each word in file, add file to corresponding set
            while (!in.isEmpty()) {
                String key = in.readString();
                if (!st.contains(key))
                    st.put(key, new SET<File>());
                SET<File> set = st.get(key);
                set.add(file);
            }
        }

        // process queries
        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            StdOut.println(st.get(query));
        }
    }
}
