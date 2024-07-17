package data_structures.btree;

public class BTreeNode {
    int t; // Grado mínimo
    int n; // Número de claves en el nodo
    String[] keys;
    BTreeNode[] children;
    boolean leaf;

    public BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new String[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.n = 0;
    }
}
