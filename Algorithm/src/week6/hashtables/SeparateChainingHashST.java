package week6.hashtables;

public class SeparateChainingHashST<Key, Value> {
    private int M = 97; // number of chains

    // array doubling and halving code omitted
    private Node[] st = new Node[M]; // array of chains

    public boolean contains(Key i) {
        // TODO
        return false;
    }

    public Iterable<Key> keys() {
        // TODO
        return null;
    }


    private static class Node {

        // no generic array creation(declare key and value of type Object)
        private Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private int hash(Key key) {
        // make it positive by ending of the sign bit
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next)
            if (key.equals(x.key))
                return (Value) x.val;
        return null;
    }

    public void put(Key key, Value val) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next)
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }

        st[i] = new Node(key, val, st[i]);


    }
}
