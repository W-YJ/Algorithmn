package part2.Assignment1.wordnet;

import edu.princeton.cs.algs4.*;

public class SAP {
    private Digraph G;
    private int shortest;


    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.G = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        BreadthFirstDirectedPaths vPath = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths wPath = new BreadthFirstDirectedPaths(G, v);
        for (int i = 0; i < G.V(); i++) {
            if(vPath.hasPathTo())

        }

    }

    // a common ancestor of v and w that participates in a shortest ancestral path; - if no path
    public int ancestor(int v, int w) {

    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths vPaths = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths wPaths = new BreadthFirstDirectedPaths(G, v);
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }

    }

    // assist methods
    private void argumentValidate(String check) {
        if (check == null) {
            throw new IllegalArgumentException("the  argument can't be null");
        }
    }


}
