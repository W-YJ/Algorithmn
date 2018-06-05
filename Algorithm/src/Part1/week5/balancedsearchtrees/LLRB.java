package Part1.week5.balancedsearchtrees;

public class LLRB<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        boolean color; // color of parent link

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private Node root;

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x.value;
        }
        return null;
    }

    private boolean isRed(Node x) {
        // null links are black
        if (x == null)
            return false;
        return x.color == RED;
    }

    // Left rotation. Orient a (temporarily) right-leaning red link to lean left
    private Node rotateLeft(Node h) {
        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    // Right rotation. Orient a left-leaning red link to (temporarily) lean right.
    private Node rotateRight(Node h) {
        assert (h.color = RED);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    // Color flip. Recolor to split a (temporary) 4-node.
    private void flipColors(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private Node put(Node h, Key key, Value value) {
        if (h == null)
            return new Node(key, value, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0)
            h.left = put(h.left, key, value);
        else if (cmp > 0)
            h.right = put(h.right, key, value);
        else h.value = value;

        // Right child red, left child black: rotate left.
        if(isRed(h.right)&&!isRed(h.left))
            h = rotateLeft(h);//lean left
        // Left child, left-left grandchild red: rotate right.
        if(isRed(h.right)&&isRed(h.left.left))
            h = rotateRight(h); //balance 4-node
        // Both children red: flip colors.
        if(isRed(h.left)&&isRed(h.right))
            flipColors(h); //split 4-node

        return h;

    }
}
