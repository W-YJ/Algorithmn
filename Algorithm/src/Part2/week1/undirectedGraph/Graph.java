package Part2.week1.undirectedGraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

// Graph API
public class Graph {
    // create an empty graph with V vertices
    public Graph(int V) {
    }

    // create a graph from input stream
    public Graph(In in) {

    }

    // add an edge v-w
    public void addEdge(int v, int w) {

    }

    // vertices adjacent to v
    public Iterable<Integer> adj(int v) {
        return null;
    }

    // number of vertices
    public int V() {
        return 0;
    }

    // number of edges
    public int E() {
        return 0;
    }

    // string representation
    public String toString() {
        return "";
    }

    // typical graph-processing code
    // compute the degree of v
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v))
            degree++;
        return degree;
    }

    // compute maximum degree
    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++) {
            if (degree(G, v) > max)
                max = degree(G, v);
        }
        return max;
    }

    // compute average degree
    public static double averageDegree(Graph G) {
        return 2.0 * G.E() / G.V();
    }

    // count self-loops
    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w) {
                    count++;
                }
            }
        }
        return count / 2; // each edge counted twice
    }

    public static void main(String[] args) {
        // read graph from input stream
        In in = new In(args[0]);
        Graph G = new Graph(in);

        // print out each edge(twice)
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                StdOut.println(v + "-" + w);
            }
        }
    }

}
