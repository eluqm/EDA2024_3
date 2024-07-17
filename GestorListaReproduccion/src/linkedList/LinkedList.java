package linkedList;
public class LinkedList<T> {
    private Node<T> head;

    public LinkedList() {
        this.head = null;
    }
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void move(int fromIndex, int toIndex) {
        if (fromIndex == toIndex) return;
    
        // Inicializar variables
        Node<T> prevFrom = null, fromNode = null;
        Node<T> prevTo = null, toNode = null;
        Node<T> current = head;
        int index = 0;
    
        // Buscar nodos y sus predecesores
        while (current != null) {
            if (index == fromIndex - 1) prevFrom = current;
            if (index == fromIndex) fromNode = current;
            if (index == toIndex - 1) prevTo = current;
            if (index == toIndex) toNode = current;
            current = current.next;
            index++;
        }
    
        // Verificar si los nodos son válidos
        if (fromNode == null) return;
    
        // Eliminar fromNode de su posición actual
        if (prevFrom != null) {
            prevFrom.next = fromNode.next;
        } else {
            head = fromNode.next;
        }
    
        // Mover fromNode a la nueva posición
        if (toIndex == 0) {
            fromNode.next = head;
            head = fromNode;
        } else if (toIndex > fromIndex) {
            if (toNode != null) {
                fromNode.next = toNode.next;
                toNode.next = fromNode;
            }
        } else {
            if (prevTo != null) {
                fromNode.next = prevTo.next;
                prevTo.next = fromNode;
            }
        }
    }

    public Node<T> getHead() {
        return head;
    }
}
