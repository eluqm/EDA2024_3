public class ListEnlazadaSimple<T> {

    private static class Nodo<T> {
        private T dato;
        private Nodo<T> siguiente;

        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo<T> cabeza;
    private int tamaño;

    public ListEnlazadaSimple() {
        cabeza = null;
        tamaño = 0;
    }

    // Método para agregar un elemento al inicio de la lista
    public void insertarInicio(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
        tamaño++;
    }

    // Método para agregar un elemento al final de la lista
    public void insertarFinal(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
    }

    // Método para eliminar el primer elemento de la lista
    public void eliminarPrimero(){
        if (cabeza == null) {
            System.out.println("La lista esta vacia");
        }
        cabeza = cabeza.siguiente;
        tamaño--;
    }

    // Método para obtener el primer elemento de la lista
    public T obtenerPrimero(){
        if (cabeza == null) {
            System.out.println("La lista esta vacia");
        }
        return cabeza.dato;
    }

    // Método para verificar si la lista está vacía
    public boolean estaVacia() {
        return cabeza == null;
    }

    // Método para obtener el tamaño de la lista
    public int obtenerTamaño() {
        return tamaño;
    }

    // Método para imprimir la lista
    public void imprimirLista() {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }
}