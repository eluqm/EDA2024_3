package tests;

import estructuras.BTree;

public class MainBTree {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(3);

        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(6);
        tree.insert(12);
        tree.insert(30);
        tree.insert(7);
        tree.insert(17);

        System.out.println("Traversal of the constructed tree is:");
        tree.traverse();

        int key = 6;
        if (tree.search(key) != null) {
            System.out.println("\nKey " + key + " is present in the tree");
        } else {
            System.out.println("\nKey " + key + " is not present in the tree");
        }
    }
}
