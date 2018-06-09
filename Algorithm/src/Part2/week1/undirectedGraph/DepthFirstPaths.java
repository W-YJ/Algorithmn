package Part2.week1.undirectedGraph;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstPaths {
    // marked[v] = true, if v connected to s
    private boolean[] marked;
    // edgeTo[v] = previous: vertex on path from s to v
    private int[] edgeTo;
    private int s;

    public DepthFirstPaths(Graph G, int s) {
        // initialize data structures
        // TODO
        // find vertices connected to s
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                // recursive DFS does the work
                dfs(G, w);
                edgeTo[w] = v;
            }
        }
    }


    public static void main(String[] args) {
        Graph G = new Graph(10);
        int s = 5;
        DepthFirstPaths paths = new DepthFirstPaths(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (paths.hasPathTo(v))
                StdOut.println(v);
        }
    }

    // is there a path from source s
    private boolean hasPathTo(int v) {
        return marked[v];
    }

    // path from s to v; null if no such path
    Iterable<Integer> pathTO(int v) {
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }

        path.push(s);
        return path;
    }
}
