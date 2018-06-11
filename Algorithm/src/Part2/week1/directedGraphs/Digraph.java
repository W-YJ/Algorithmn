package Part2.week1.directedGraphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    // adjaceny lists
    private Bag<Integer>[] adj;
    private int[] indegree;

    // create an empty digraph with V vertices
    public Digraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegtive");
        }
        this.V = V;
        this.E = 0;
        // create empty digraph with V vertices
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    // create a digraph from input stream
    public Digraph(In in) {
        this.V = in.readInt();
        if (V < 0)
            throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegtive");

    }

    // add a directed edge v -> w
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    // iterator for vertices pointing from v
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // number of vertices
    public int V() {
        return this.V;
    }

    // number of edges
    public int E() {
        return this.E;

    }

    // reverse of this digraph
    public Digraph reverse() {
        // TODO
        return null;
    }

    // string representation
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + "vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d:", v));
            for (int w : adj[v]) {
                s.append(String.format("%d", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();

    }

    public static void main(String[] args) {
        // read digraph from input stream
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                // print out each edge(once)
                StdOut.println(v + "->" + w);
            }
        }
    }
}
