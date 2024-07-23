package data_structures.btree;

public class BTree {
    private BTreeNode root;
    private int t;  // Grado mínimo

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    public String search(String key) {
        return (root == null) ? null : search(root, key);
    }

    private String search(BTreeNode x, String key) {
        int i = 0;
        while (i < x.n && key.compareTo(x.keys[i]) > 0) {
            i++;
        }
        if (i < x.n && key.equals(x.keys[i])) {
            return x.keys[i];
        } else if (x.leaf) {
            return null;
        } else {
            return search(x.children[i], key);
        }
    }

    // Métodos de inserción y eliminación se implementarán aquí
}
