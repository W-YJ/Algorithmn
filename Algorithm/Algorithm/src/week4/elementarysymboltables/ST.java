package week4.elementarysymboltables;

// Symbol table API
public class ST<Key extends Comparable<Key>, Value> {
    // create a ordered symbol table
    public ST() {

    }

    // put key-value pair into the table(remove table from table if value is null)
    public void put(Key key, Value val) {

    }

    // value paired with key(null if key is absent)
    public Value get(Key key) {
        return null;
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

    // smallest key
    public Key min() {
        return null;
    }

    // largest key
    public Key max() {
        return null;
    }

    // largest key less than or equal to key
    public Key floor(Key key) {
        return null;
    }

    // Smallest key greater than or equal to key
    public Key celling(Key key) {
        return null;
    }

    // number of keys less than key
    public int rank(Key key) {
        return 0;
    }

    // key of rank key
    public Key select(int k) {
        return null;
    }

    // delete smallest key
    public void deleteMin() {

    }

    // delete largest key
    public void deleteMax() {

    }

    // keys in [lo...hi], in sorted order
    Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    // all the keys in the table, in sorted order
    Iterable<Key> keys() {
        return null;
    }
}
