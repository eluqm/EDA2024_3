class BTreeNode {
    int t;  // Minimum degree (defines the range for number of keys)
    DynamicArray<Song> keys;  // An array of keys
    DynamicArray<BTreeNode> children;  // An array of child pointers
    int n;  // Current number of keys
    boolean leaf;  // Is true when node is leaf. Otherwise false

    BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new DynamicArray<>();
        this.children = new DynamicArray<>();
        this.n = 0;
    }

    void insertNonFull(Song k) {
        int i = n - 1;

        if (leaf) {
            while (i >= 0 && keys.get(i).compareTo(k) > 0) {
                i--;
            }
            keys.add(i + 1, k);
            n++;
        } else {
            while (i >= 0 && keys.get(i).compareTo(k) > 0) {
                i--;
            }
            if (children.get(i + 1).n == 2 * t - 1) {
                splitChild(i + 1, children.get(i + 1));
                if (keys.get(i + 1).compareTo(k) < 0) {
                    i++;
                }
            }
            children.get(i + 1).insertNonFull(k);
        }
    }

    void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.t, y.leaf);
        z.n = t - 1;

        for (int j = 0; j < t - 1; j++) {
            z.keys.add(y.keys.get(j + t));
        }

        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.children.add(y.children.get(j + t));
            }
        }

        for (int j = n; j >= i + 1; j--) {
            children.add(j + 1, children.get(j));
        }

        children.add(i + 1, z);

        for (int j = n - 1; j >= i; j--) {
            keys.add(j + 1, keys.get(j));
        }

        keys.add(i, y.keys.get(t - 1));
        n++;
    }

    void traverse() {
        int i;
        for (i = 0; i < n; i++) {
            if (!leaf) {
                children.get(i).traverse();
            }
            System.out.print(" " + keys.get(i));
        }
        if (!leaf) {
            children.get(i).traverse();
        }
    }

    BTreeNode search(Song k) {
        int i = 0;
        while (i < n && k.compareTo(keys.get(i)) > 0) {
            i++;
        }
        if (i < n && keys.get(i).compareTo(k) == 0) {
            return this;
        }
        if (leaf) {
            return null;
        }
        return children.get(i).search(k);
    }
}

