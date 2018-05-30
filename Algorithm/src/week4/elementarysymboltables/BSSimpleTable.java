package week4.elementarysymboltables;

public class BSSimpleTable<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    // create a symbol table
    public BSSimpleTable() {

    }

    // put key-value pair into the table(remove table from table if value is null)
    public void put(Key key, Value val) {

    }

    // value paired with key(null if key is absent)
    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
            return vals[i];
        else
            return null;
    }

    private int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp<0)
                hi = mid+1;
            else if(cmp>0)
                lo=mid+1;
            else
                return mid;
        }
        return lo;
    }

    // remove key(and its value) from table
    public void delete(Key key) {
        put(key, null);
    }

    // is there a value paired with key?
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // is it the table empty?
    public boolean isEmpty() {
        return false;
    }

    // number of key-value pairs in the table
    public int size() {
        return 0;
    }

    // all the keys in the table
    Iterable<Key> keys() {
        return null;
    }
}
