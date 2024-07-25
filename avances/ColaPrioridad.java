public class ColaPrioridad<K extends Comparable<K>, V> {
    private Node<K, V> primero;
    private Node<K, V> ultimo;

    public ColaPrioridad() {
        this.primero = this.ultimo = null;
    }

    public void encolar(K clave, V valor) {
        Node<K, V> nuevoNodo = new Node<>(clave, valor);
        if (primero == null) {
            this.primero = nuevoNodo;
            this.ultimo = nuevoNodo;
        } else if (primero.getKey().compareTo(clave) > 0) {
            nuevoNodo.setNext(primero);
            primero.setPrev(nuevoNodo);
            this.primero = nuevoNodo;
        } else {
            Node<K, V> aux = primero;
            while (aux.getNext() != null && aux.getNext().getKey().compareTo(clave) < 0) {
                aux = aux.getNext();
            }
            if (aux.getNext() == null) {
                this.ultimo = nuevoNodo;
            } else {
                aux.getNext().setPrev(nuevoNodo);
            }
            nuevoNodo.setNext(aux.getNext());
            nuevoNodo.setPrev(aux);
            aux.setNext(nuevoNodo);
        }
    }

    public Node<K, V> desencolar() {
        if (primero == null) {
            return null;
        } else {
            Node<K, V> nodo = primero;
            primero = primero.getNext();
            if (primero == null) {
                ultimo = null;
            } else {
                primero.setPrev(null);
            }
            return nodo;
        }
    }

    public V frente() {
        if (primero == null) {
            return null;
        }
        return primero.getValue();
    }

    public V finalDeCola() {
        if (ultimo == null) {
            return null;
        }
        return ultimo.getValue();
    }

    public boolean estaVacia() {
        return primero == null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Node<K, V> i = primero; i != null; i = i.getNext()) {
            str.append(i).append("\n");
        }
        return str.toString();
    }
}