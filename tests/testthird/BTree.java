import java.util.ArrayList;
import java.util.List;

class BTree<K extends Comparable<K>, T> {
    private BTreeNode<K, T> root;
    private final int minDegree = 3; // Para un árbol B de orden 5, minDegree debe ser 3

    // Constructor
    public BTree() {
        this.root = new BTreeNode<>(true);
    }

    // Método para insertar una nueva canción en el árbol B
    public void insert(T item, K attribute) {
        OrderAttribute<K, T> orderAttribute = new OrderAttribute<>(attribute);
        orderAttribute.addMusic(item);
        BTreeNode<K, T> root = this.root;
        if (root.keys.size() == 2 * minDegree - 1) {
            BTreeNode<K, T> newNode = new BTreeNode<>(false);
            newNode.children.add(root);
            this.root = newNode;
            splitChild(newNode, 0, root);
            insertNonFull(newNode, orderAttribute);
        } else {
            insertNonFull(root, orderAttribute);
        }
    }

    // Método para dividir un nodo hijo cuando está lleno
    private void splitChild(BTreeNode<K, T> parent, int index, BTreeNode<K, T> child) {
        BTreeNode<K, T> newNode = new BTreeNode<>(child.isLeaf);
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
    private void insertNonFull(BTreeNode<K, T> node, OrderAttribute<K, T> orderAttribute) {
        int index = node.keys.size() - 1;

        if (node.isLeaf) {
            while (index >= 0 && orderAttribute.compareTo(node.keys.get(index)) < 0) {
                index--;
            }
            if (index >= 0 && node.keys.get(index).getAttributeKey().equals(orderAttribute.getAttributeKey())) {
                node.keys.get(index).addMusic(orderAttribute.getListMusic().get(0));
            } else {
                node.keys.add(index + 1, orderAttribute);
            }
        } else {
            while (index >= 0 && orderAttribute.compareTo(node.keys.get(index)) < 0) {
                index--;
            }
            index++;
            BTreeNode<K, T> child = node.children.get(index);
            if (child.keys.size() == 2 * minDegree - 1) {
                splitChild(node, index, child);
                if (orderAttribute.compareTo(node.keys.get(index)) > 0) {
                    index++;
                }
            }
            insertNonFull(node.children.get(index), orderAttribute);
        }
    }

    // Método para eliminar una canción del árbol B
    public void delete(T item, K attribute) {
        if (root == null) {
            System.out.println("El árbol está vacío");
            return;
        }

        delete(root, item, attribute);

        if (root.keys.size() == 0) {
            if (root.isLeaf) {
                root = null;
            } else {
                root = root.children.get(0);
            }
        }
    }

    // Método auxiliar para eliminar una canción en un nodo específico
    private void delete(BTreeNode<K, T> node, T item, K attribute) {
        int idx = findKey(node, attribute);

        if (idx < node.keys.size() && node.keys.get(idx).getAttributeKey().equals(attribute)) {
            if (node.isLeaf) {
                removeItemFromOrderAttribute(node.keys.get(idx), item);
            } else {
                deleteInternalNode(node, item, attribute, idx);
            }
        } else {
            if (node.isLeaf) {
                System.out.println("El item " + item + " no está en el árbol");
                return;
            }

            boolean flag = (idx == node.keys.size());

            if (node.children.get(idx).keys.size() < minDegree) {
                fill(node, idx);
            }

            if (flag && idx > node.keys.size()) {
                delete(node.children.get(idx - 1), item, attribute);
            } else {
                delete(node.children.get(idx), item, attribute);
            }
        }
    }

    // Método para encontrar la posición de una clave en un nodo
    private int findKey(BTreeNode<K, T> node, K attribute) {
        int idx = 0;
        while (idx < node.keys.size() && node.keys.get(idx).getAttributeKey().compareTo(attribute) < 0) {
            idx++;
        }
        return idx;
    }

    // Método para eliminar una clave de un nodo interno
    private void deleteInternalNode(BTreeNode<K, T> node, T item, K attribute, int idx) {
        OrderAttribute<K, T> k = node.keys.get(idx);

        if (node.children.get(idx).keys.size() >= minDegree) {
            OrderAttribute<K, T> pred = getPredecessor(node, idx);
            node.keys.set(idx, pred);
            delete(node.children.get(idx), item, attribute);
        } else if (node.children.get(idx + 1).keys.size() >= minDegree) {
            OrderAttribute<K, T> succ = getSuccessor(node, idx);
            node.keys.set(idx, succ);
            delete(node.children.get(idx + 1), item, attribute);
        } else {
            merge(node, idx);
            delete(node.children.get(idx), item, attribute);
        }
    }

    // Método para obtener el predecesor de una clave en un nodo
    private OrderAttribute<K, T> getPredecessor(BTreeNode<K, T> node, int idx) {
        BTreeNode<K, T> cur = node.children.get(idx);
        while (!cur.isLeaf) {
            cur = cur.children.get(cur.keys.size());
        }
        return cur.keys.get(cur.keys.size() - 1);
    }

    // Método para obtener el sucesor de una clave en un nodo
    private OrderAttribute<K, T> getSuccessor(BTreeNode<K, T> node, int idx) {
        BTreeNode<K, T> cur = node.children.get(idx + 1);
        while (!cur.isLeaf) {
            cur = cur.children.get(0);
        }
        return cur.keys.get(0);
    }

    // Método para rellenar un nodo hijo que tiene menos claves que las permitidas
    private void fill(BTreeNode<K, T> node, int idx) {
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

    // Método para tomar prestada una clave del nodo hermano anterior
    private void borrowFromPrev(BTreeNode<K, T> node, int idx) {
        BTreeNode<K, T> child = node.children.get(idx);
        BTreeNode<K, T> sibling = node.children.get(idx - 1);

        child.keys.add(0, node.keys.get(idx - 1));
        node.keys.set(idx - 1, sibling.keys.remove(sibling.keys.size() - 1));

        if (!child.isLeaf) {
            child.children.add(0, sibling.children.remove(sibling.children.size() - 1));
        }
    }

    // Método para tomar prestada una clave del nodo hermano siguiente
    private void borrowFromNext(BTreeNode<K, T> node, int idx) {
        BTreeNode<K, T> child = node.children.get(idx);
        BTreeNode<K, T> sibling = node.children.get(idx + 1);

        child.keys.add(node.keys.get(idx));
        node.keys.set(idx, sibling.keys.remove(0));

        if (!child.isLeaf) {
            child.children.add(sibling.children.remove(0));
        }
    }

    // Método para fusionar un nodo con su nodo hermano
    private void merge(BTreeNode<K, T> node, int idx) {
        BTreeNode<K, T> child = node.children.get(idx);
        BTreeNode<K, T> sibling = node.children.get(idx + 1);

        child.keys.add(node.keys.remove(idx));
        child.keys.addAll(sibling.keys);

        if (!child.isLeaf) {
            child.children.addAll(sibling.children);
        }

        node.children.remove(idx + 1);
    }

    // Método para eliminar una canción específica de un OrderAttribute
    private void removeItemFromOrderAttribute(OrderAttribute<K, T> orderAttribute, T item) {
        List<T> itemList = orderAttribute.getListMusic();
        if (itemList.remove(item)) {
            System.out.println("Item " + item + " eliminado del atributo " + orderAttribute.getAttributeKey());
        } else {
            System.out.println("El item " + item + " no está en la lista del atributo " + orderAttribute.getAttributeKey());
        }
    }

    // Método para recorrer el árbol B y mostrar sus claves
    public void traverse() {
        traverse(root);
    }

    // Método auxiliar para recorrer el árbol B en orden
    private void traverse(BTreeNode<K, T> node) {
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
    private void traverseDescending(BTreeNode<K, T> node) {
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
