package Part1.week6.symboltableapplications;

import Part1.week6.hashtables.SeparateChainingHashST;

public class SparseVector {
    // HashST because order not important
    private SeparateChainingHashST<Integer, Double> v;

    // empty ST represents all Os vector
    public SparseVector() {
        this.v = new SeparateChainingHashST<Integer, Double>();
    }

    // a[i] = value
    public void put(int i, double x) {
        v.put(i, x);
    }

    // return a[i]
    public double get(int i) {
        if (!v.contains(i)) {
            return 0.0;
        } else {
            return v.get(i);
        }
    }

    public Iterable<Integer> indices() {
        return v.keys();
    }

    // dot product is constant time for sparse vectors
    public double dot(double[] that) {
        double sum = 0.0;
        for (int i : indices())
            sum += that[i] * this.get(i);
        return sum;
    }
}
