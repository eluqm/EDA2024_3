
class BTree<T extends Comparable<T>> {
    private BTreeNode<T> root;
    private final int minDegree = 3; // Para un árbol B de orden 5, minDegree debe ser 3

    // Constructor
    public BTree() {
        this.root = new BTreeNode<>(true);
    }

    // Método para insertar una nueva canción en el árbol B
    public void insert(Song song) {
        OrderYear<Song> orderYear = new OrderYear<>(song.getYear());
        orderYear.addMusic(song);
        BTreeNode<T> root = this.root;
        if (root.keys.size() == 2 * minDegree - 1) {
            BTreeNode<T> newNode = new BTreeNode<>(false);
            newNode.children.add(root);
            this.root = newNode;
            splitChild(newNode, 0, root);
            insertNonFull(newNode, orderYear);
        } else {
            insertNonFull(root, orderYear);
        }
    }

    // Método para dividir un nodo hijo cuando está lleno
    private void splitChild(BTreeNode<T> parent, int index, BTreeNode<T> child) {
        BTreeNode<T> newNode = new BTreeNode<>(child.isLeaf);
        parent.children.add(index + 1, newNode);
        parent.keys.add(index, child.keys.get(minDegree - 1));

        newNode.keys.addAll(child.keys.subList(minDegree, 2 * minDegree - 1));
        child.keys.subList(minDegree - 1, 2 * minDegree - 1).clear();

        if (!child.isLeaf) {
            newNode.children.addAll(child.children.subList(minDegree, 2 * minDegree));
            child.children.subList(minDegree, 2 * minDegree).clear();
        }
    }

    // Método para insertar una clave en un nodo que no está lleno
    private void insertNonFull(BTreeNode<T> node, OrderYear<Song> orderYear) {
        int index = node.keys.size() - 1;

        if (node.isLeaf) {
            while (index >= 0 && orderYear.compareTo((OrderYear<Song>) node.keys.get(index)) < 0) {
                index--;
            }
            if (index >= 0 && node.keys.get(index).getYear() == orderYear.getYear()) {
                node.keys.get(index).addMusic((T) orderYear.getListMusic().get(0));
            } else {
                node.keys.add(index + 1, (OrderYear<T>) orderYear);
            }
        } else {
            while (index >= 0 && orderYear.compareTo((OrderYear<Song>) node.keys.get(index)) < 0) {
                index--;
            }
            index++;
            BTreeNode<T> child = node.children.get(index);
            if (child.keys.size() == 2 * minDegree - 1) {
                splitChild(node, index, child);
                if (orderYear.compareTo((OrderYear<Song>) node.keys.get(index)) > 0) {
                    index++;
                }
            }
            insertNonFull(node.children.get(index), orderYear);
        }
    }

    public void delete(Song song) {
        if (root == null) {
            System.out.println("El árbol está vacío");
            return;
        }

        delete(root, song.getArtistName(), song.getTrackName(), song.getYear());

        if (root.keys.size() == 0) {
            if (root.isLeaf) {
                root = null;
            } else {
                root = root.children.get(0);
            }
        }
    }

    private void delete(BTreeNode<T> node, String artistName, String trackName, short year) {
        int idx = findKey(node, year);

        if (idx < node.keys.size() && node.keys.get(idx).getYear() == year) {
            if (node.isLeaf) {
                ((OrderYear<Song>) node.keys.get(idx)).removeMusic(artistName, trackName, year);
            } else {
                deleteInternalNode(node, artistName, trackName, year, idx);
            }
        } else {
            if (node.isLeaf) {
                System.out.println("La llave " + year + " no está en el árbol");
                return;
            }

            boolean flag = (idx == node.keys.size());

            if (node.children.get(idx).keys.size() < minDegree) {
                fill(node, idx);
            }

            if (flag && idx > node.keys.size()) {
                delete(node.children.get(idx - 1), artistName, trackName, year);
            } else {
                delete(node.children.get(idx), artistName, trackName, year);
            }
        }
    }

    private int findKey(BTreeNode<T> node, short year) {
        int idx = 0;
        while (idx < node.keys.size() && ((OrderYear<Song>) node.keys.get(idx)).getYear() < year) {
            idx++;
        }
        return idx;
    }

    private void deleteInternalNode(BTreeNode<T> node, String artistName, String trackName, short year, int idx) {
        OrderYear<Song> k = (OrderYear<Song>) node.keys.get(idx);

        if (node.children.get(idx).keys.size() >= minDegree) {
            OrderYear<Song> pred = getPredecessor(node, idx);
            node.keys.set(idx, (OrderYear<T>) pred);
            delete(node.children.get(idx), artistName, trackName, year);
        } else if (node.children.get(idx + 1).keys.size() >= minDegree) {
            OrderYear<Song> succ = getSuccessor(node, idx);
            node.keys.set(idx, (OrderYear<T>) succ);
            delete(node.children.get(idx + 1), artistName, trackName, year);
        } else {
            merge(node, idx);
            delete(node.children.get(idx), artistName, trackName, year);
        }
    }

    private OrderYear<Song> getPredecessor(BTreeNode<T> node, int idx) {
        BTreeNode<T> cur = node.children.get(idx);
        while (!cur.isLeaf) {
            cur = cur.children.get(cur.keys.size());
        }
        return (OrderYear<Song>) cur.keys.get(cur.keys.size() - 1);
    }

    private OrderYear<Song> getSuccessor(BTreeNode<T> node, int idx) {
        BTreeNode<T> cur = node.children.get(idx + 1);
        while (!cur.isLeaf) {
            cur = cur.children.get(0);
        }
        return (OrderYear<Song>) cur.keys.get(0);
    }

    private void fill(BTreeNode<T> node, int idx) {
        if (idx != 0 && node.children.get(idx - 1).keys.size() >= minDegree) {
            borrowFromPrev(node, idx);
        } else if (idx != node.keys.size() && node.children.get(idx + 1).keys.size() >= minDegree) {
            borrowFromNext(node, idx);
        } else {
            if (idx != node.keys.size()) {
                merge(node, idx);
            } else {
                merge(node, idx - 1);
            }
        }
    }

    private void borrowFromPrev(BTreeNode<T> node, int idx) {
        BTreeNode<T> child = node.children.get(idx);
        BTreeNode<T> sibling = node.children.get(idx - 1);

        child.keys.add(0, node.keys.get(idx - 1));
        node.keys.set(idx - 1, sibling.keys.remove(sibling.keys.size() - 1));

        if (!child.isLeaf) {
            child.children.add(0, sibling.children.remove(sibling.children.size() - 1));
        }
    }

    private void borrowFromNext(BTreeNode<T> node, int idx) {
        BTreeNode<T> child = node.children.get(idx);
        BTreeNode<T> sibling = node.children.get(idx + 1);

        child.keys.add(node.keys.get(idx));
        node.keys.set(idx, sibling.keys.remove(0));

        if (!child.isLeaf) {
            child.children.add(sibling.children.remove(0));
        }
    }

    private void merge(BTreeNode<T> node, int idx) {
        BTreeNode<T> child = node.children.get(idx);
        BTreeNode<T> sibling = node.children.get(idx + 1);

        child.keys.add(node.keys.remove(idx));
        child.keys.addAll(sibling.keys);

        if (!child.isLeaf) {
            child.children.addAll(sibling.children);
        }

        node.children.remove(idx + 1);
    }


    // Método para recorrer el árbol B y mostrar sus claves
    public void traverse() {
        traverse(root);
    }

    // Método auxiliar para recorrer el árbol B en orden
    private void traverse(BTreeNode<T> node) {
        int i;
        for (i = 0; i < node.keys.size(); i++) {
            if (!node.isLeaf) {
                traverse(node.children.get(i));
            }
            System.out.print(node.keys.get(i).toString() + "\n");
        }

        if (!node.isLeaf) {
            traverse(node.children.get(i));
        }
    }

    // Método para recorrer el árbol B y mostrar sus claves en orden descendente
    public void traverseDescending() {
        traverseDescending(root);
    }

    // Método auxiliar para recorrer el árbol B en orden descendente
    private void traverseDescending(BTreeNode<T> node) {
        int i;
        for (i = node.keys.size() - 1; i >= 0; i--) {
            if (!node.isLeaf) {
                traverseDescending(node.children.get(i + 1));
            }
            System.out.print(node.keys.get(i).toString() + "\n");
        }

        if (!node.isLeaf) {
            traverseDescending(node.children.get(0));
        }
    }
}