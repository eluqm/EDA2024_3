package tests;

import estructuras.Trie;

public class MainTrie {
    public static void main(String[] args) {
        Trie trie = new Trie();

        // Insertar palabras en el Trie
        trie.insert("hola");
        trie.insert("mundo");
        trie.insert("trie");
        trie.insert("estructura");
        trie.insert("datos");

        // Buscar palabras en el Trie
        System.out.println("Buscar 'hola': " + trie.search("hola")); // true
        System.out.println("Buscar 'mundo': " + trie.search("mundo")); // true
        System.out.println("Buscar 'trie': " + trie.search("trie")); // true
        System.out.println("Buscar 'estructura': " + trie.search("estructura")); // true
        System.out.println("Buscar 'datos': " + trie.search("datos")); // true
        System.out.println("Buscar 'algoritmo': " + trie.search("algoritmo")); // false

        // Comprobar prefijos en el Trie
        System.out.println("Empieza con 'ho': " + trie.startsWith("ho")); // true
        System.out.println("Empieza con 'mun': " + trie.startsWith("mun")); // true
        System.out.println("Empieza con 'tr': " + trie.startsWith("tr")); // true
        System.out.println("Empieza con 'est': " + trie.startsWith("est")); // true
        System.out.println("Empieza con 'da': " + trie.startsWith("da")); // true
        System.out.println("Empieza con 'alg': " + trie.startsWith("alg")); // false
    }
}
