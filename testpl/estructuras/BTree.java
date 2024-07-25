package estructuras;

public class BTree {
    BTreeNode root;
    int t;

    BTree(int t) {
        this.root = null;
        this.t = t;
    }

    void traverse() {
        if (root != null) {
            root.traverse();
        }
    }

    BTreeNode search(Song k) {
        return (root == null) ? null : root.search(k);
    }

    void insert(Song k) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys.add(0, k);
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.children.add(0, root);
                s.splitChild(0, root);

                int i = 0;
                if (s.keys.get(0).compareTo(k) < 0) {
                    i++;
                }
                s.children.get(i).insertNonFull(k);
                root = s;
            } else {
                root.insertNonFull(k);
            }
        }
    }
}
