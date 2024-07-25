package estructuras;

public class BTreeNode<T extends Comparable<T>> {
    int t;  // Minimum degree
    DynamicArray<T> keys;
    DynamicArray<BTreeNode<T>> children;
    boolean leaf;

    public BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new DynamicArray<>();
        this.children = new DynamicArray<>();
    }

    public void insertNonFull(T key) {
        int i = keys.getSize() - 1;

        if (leaf) {
            keys.add(null);  // Add a placeholder for the new key

            while (i >= 0 && keys.get(i).compareTo(key) > 0) {
                keys.set(i + 1, keys.get(i));
                i--;
            }
            keys.set(i + 1, key);
        } else {
            while (i >= 0 && keys.get(i).compareTo(key) > 0) {
                i--;
            }
            i++;
            if (children.get(i).keys.getSize() == 2 * t - 1) {
                splitChild(i, children.get(i));
                if (keys.get(i).compareTo(key) < 0) {
                    i++;
                }
            }
            children.get(i).insertNonFull(key);
        }
    }

    public void splitChild(int i, BTreeNode<T> y) {
        BTreeNode<T> z = new BTreeNode<>(y.t, y.leaf);
        for (int j = 0; j < t - 1; j++) {
            z.keys.add(y.keys.get(t));
            y.keys.remove(t);
        }
        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.children.add(y.children.get(t));
                y.children.remove(t);
            }
        }
        children.add(null);
        for (int j = children.getSize() - 1; j > i + 1; j--) {
            children.set(j, children.get(j - 1));
        }
        children.set(i + 1, z);
        keys.add(null);
        for (int j = keys.getSize() - 1; j > i; j--) {
            keys.set(j, keys.get(j - 1));
        }
        keys.set(i, y.keys.get(t - 1));
        y.keys.remove(t - 1);
    }

    public void traverse() {
        int i;
        for (i = 0; i < keys.getSize(); i++) {
            if (!leaf) {
                children.get(i).traverse();
            }
            System.out.print(" " + keys.get(i));
        }
        if (!leaf) {
            children.get(i).traverse();
        }
    }

    public BTreeNode<T> search(T key) {
        int i = 0;
        while (i < keys.getSize() && key.compareTo(keys.get(i)) > 0) {
            i++;
        }
        if (i < keys.getSize() && key.compareTo(keys.get(i)) == 0) {
            return this;
        }
        if (leaf) {
            return null;
        }
        return children.get(i).search(key);
    }
}
