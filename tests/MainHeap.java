package tests;

import estructuras.MinHeap;

public class MainHeap {
    public static void main(String[] args) {
        MinHeap heap = new MinHeap();

        // Añadir elementos al heap
        heap.add(10);
        heap.add(15);
        heap.add(20);
        heap.add(17);
        heap.add(8);

        // Imprimir el elemento mínimo
        System.out.println("El elemento mínimo es: " + heap.peek()); // 8

        // Eliminar el elemento mínimo
        System.out.println("Eliminando el elemento mínimo: " + heap.poll()); // 8

        // Imprimir el nuevo elemento mínimo
        System.out.println("El nuevo elemento mínimo es: " + heap.peek()); // 10

        // Verificar el tamaño del heap
        System.out.println("El tamaño del heap es: " + heap.getSize()); // 4

        // Verificar si el heap está vacío
        System.out.println("El heap está vacío: " + heap.isEmpty()); // false
    }
}
