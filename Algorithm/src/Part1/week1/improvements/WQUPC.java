package Part1.week1.improvements;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Weighted quick-union with path compression
 */
public class WQUPC {
    private int id[];
    private int sz[];

    public WQUPC(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    /*
     * path compression
     */
    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    /*
     * weighted quick union
     */
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j)
            return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] = sz[j] + sz[i];
        } else {
            id[j] = i;
            sz[i] = sz[i] + sz[j];
        }
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        WQUPC uf = new WQUPC(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }

}
