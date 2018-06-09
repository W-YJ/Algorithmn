package Part2.week1.undirectedGraph;

public class ConnectedComponents {
    private boolean marked[];
    // id[v] = id of components containing v
    private int[] id;
    // number of components
    private int count;

    public ConnectedComponents(Graph G) {
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    // number of components
    public int count() {
        return count;
    }

    // id of component containing v
    public int id(int v) {
        return id[v];
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        // all vertices discovered in same call of dfs have same id
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }

    }

}
