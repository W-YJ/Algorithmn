package Part1.week4.priorityqueues;

// Using the binary heap data structure
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    // fixed capacity for simplicity, can use resize array
    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }


    // PQ ops
    private boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key key) {
        pq[++N] = key;
        swim(N);
    }

    public Key delMax(Key key) {
        Key max = pq[1];
        exch(1, N--);
        sink(1);

        // prevent loitering
        pq[N + 1] = null;
        return max;
    }


    // heap helper functions
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1))
                j++;
            if (!less(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }

    // array helper functions
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
