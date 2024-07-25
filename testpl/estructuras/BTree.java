package estructuras;

public class BTree<T extends Comparable<T>> {
    BTreeNode<T> root;
    int t;  // Minimum degree

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    public void traverse() {
        if (root != null) {
            root.traverse();
        }
    }

    public BTreeNode<T> search(T key) {
        if (root == null) {
            return null;
        }
        return root.search(key);
    }

    public void insert(T key) {
        if (root == null) {
            root = new BTreeNode<>(t, true);
            root.keys.add(key);
        } else {
            if (root.keys.getSize() == 2 * t - 1) {
                BTreeNode<T> s = new BTreeNode<>(t, false);
                s.children.add(root);
                s.splitChild(0, root);
                int i = 0;
                if (s.keys.get(0).compareTo(key) < 0) {
                    i++;
                }
                s.children.get(i).insertNonFull(key);
                root = s;
            } else {
                root.insertNonFull(key);
            }
        }
    }
}
