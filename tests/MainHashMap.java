package tests;

import estructuras.HashMap;

public class MainHashMap {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        // Añadir elementos al mapa
        map.put("Uno", 1);
        map.put("Dos", 2);
        map.put("Tres", 3);

        // Obtener elementos del mapa
        System.out.println("Valor asociado a 'Uno': " + map.get("Uno")); // 1
        System.out.println("Valor asociado a 'Dos': " + map.get("Dos")); // 2
        System.out.println("Valor asociado a 'Tres': " + map.get("Tres")); // 3

        // Eliminar un elemento del mapa
        boolean removed = map.remove("Dos");
        System.out.println("'Dos' eliminado: " + removed); // true

        // Intentar obtener un elemento eliminado
        System.out.println("Valor asociado a 'Dos' después de eliminar: " + map.get("Dos")); // null

        // Añadir un nuevo elemento
        map.put("Cuatro", 4);
        System.out.println("Valor asociado a 'Cuatro': " + map.get("Cuatro")); // 4

        // Actualizar un valor existente
        map.put("Uno", 10);
        System.out.println("Nuevo valor asociado a 'Uno': " + map.get("Uno")); // 10
    }
}
