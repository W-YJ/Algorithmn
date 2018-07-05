package part1.assignment1.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private boolean[] state;
    private final WeightedQuickUnionUF grid, auxGrid;

    // create n-by-n state, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Argurment n out of bounds");
        }
        this.n = n;
        int gridCount = n * n;
        state = new boolean[n * n + 2];
        grid = new WeightedQuickUnionUF(gridCount + 2);
        auxGrid = new WeightedQuickUnionUF(gridCount + 1);
        for (int i = 1; i <= gridCount; i++) {
            state[i] = false;
        }
        state[0] = true;
        state[gridCount + 1] = true;
    }

    // open site(row, col) if it is not open already
    public void open(int i, int j) {
        if (isOpen(i, j))
            return;
        int idx = xyTo1D(i, j);
        state[idx] = true;
        if (i != 1 && isOpen(i - 1, j)) {
            if (!grid.connected(idx, xyTo1D(i - 1, j))) {
                grid.union(idx, xyTo1D(i - 1, j));
                auxGrid.union(idx, xyTo1D(i - 1, j));
            }
        }
        if (i != n && isOpen(i + 1, j)) {
            if (!grid.connected(idx, xyTo1D(i + 1, j))) {
                grid.union(idx, xyTo1D(i + 1, j));
                auxGrid.union(idx, xyTo1D(i + 1, j));
            }
        }
        if (j != 1 && isOpen(i, j - 1)) {
            if (!grid.connected(idx, xyTo1D(i, j - 1))) {
                grid.union(idx, xyTo1D(i, j - 1));
                auxGrid.union(idx, xyTo1D(i, j - 1));
            }
        }
        if (j != n && isOpen(i, j + 1)) {
            if (!grid.connected(idx, xyTo1D(i, j + 1))) {
                grid.union(idx, xyTo1D(i, j + 1));
                auxGrid.union(idx, xyTo1D(i, j + 1));
            }
        }

        if (isTopSite(idx)) {
            if (!grid.connected(0, idx)) {
                grid.union(0, idx);
                auxGrid.union(0, idx);
            }
        }
        if (isBottomSite(idx)) {
            if (!grid.connected(state.length - 1, idx)) {
                grid.union(state.length - 1, idx);
            }
        }
    }

    private boolean isTopSite(int index) {
        return index <= n;
    }

    private boolean isBottomSite(int index) {
        return index >= (n - 1) * n + 1;
    }

    // is site(row, col) open?
    public boolean isOpen(int i, int j) {
        return state[xyTo1D(i, j)];
    }

    // is site(row, col) full?
    public boolean isFull(int i, int j) {
        int idx = xyTo1D(i, j);
        return grid.connected(0, idx) && auxGrid.connected(0, idx);
    }

    // number of open sites
    public int numberOfOpenSites() {
        int num = 0;
        for (boolean b : state) {
            if (b)
                num++;
        }
        return num;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(0, state.length - 1);
    }

    private int xyTo1D(int i, int j) {
        indexValidate(i, j);
        return (i - 1) * n + j;
    }

    private void indexValidate(int i, int j) {
        if (i < 1 || i > n) {
            throw new IllegalArgumentException("row i out of bound.");
        }
        if (j < 1 || j > n) {
            throw new IllegalArgumentException("column j out of bound.");
        }
    }
}
