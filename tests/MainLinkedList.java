package tests;

import estructuras.LinkedList;

public class MainLinkedList {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        // Comprobar si la lista está vacía
        System.out.println("La lista está vacía: " + list.isEmpty()); // true

        // Añadir elementos a la lista
        list.add("Canción 1");
        list.add("Canción 2");
        list.add("Canción 3");

        // Imprimir la lista
        System.out.print("Contenido de la lista: ");
        list.printList(); // Canción 1 Canción 2 Canción 3

        // Comprobar el tamaño de la lista
        System.out.println("Tamaño de la lista: " + list.size()); // 3

        // Obtener el primer elemento
        System.out.println("Primer elemento: " + list.get(0)); // Canción 1

        // Obtener el segundo elemento
        System.out.println("Segundo elemento: " + list.get(1)); // Canción 2

        // Eliminar un elemento
        boolean removed = list.remove("Canción 2");
        System.out.println("Canción 2 eliminada: " + removed); // true

        // Imprimir la lista nuevamente
        System.out.print("Contenido de la lista después de eliminar Canción 2: ");
        list.printList(); // Canción 1 Canción 3

        // Intentar eliminar un elemento que no está en la lista
        removed = list.remove("Canción 4");
        System.out.println("Canción 4 eliminada: " + removed); // false

        // Comprobar si la lista está vacía
        System.out.println("La lista está vacía: " + list.isEmpty()); // false

        // Obtener el tamaño de la lista después de las eliminaciones
        System.out.println("Tamaño de la lista: " + list.size()); // 2
    }
}
