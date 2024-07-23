package linkedList;
public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail; 

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void move(int fromIndex, int toIndex) {
        if (fromIndex == toIndex) return;

        Node<T> current = head;
        Node<T> fromNode = null, toNode = null;
        Node<T> prevFrom = null, prevTo = null;

        // Buscar fromNode y toNode
        for (int index = 0; current != null; index++) {
            if (index == fromIndex) {
                fromNode = current;
                prevFrom = current.prev;
            }
            if (index == toIndex) {
                toNode = current;
                prevTo = current.prev;
            }
            current = current.next;
        }

        if (fromNode == null) return;

        // Eliminar fromNode de su posición actual
        if (prevFrom != null) {
            prevFrom.next = fromNode.next;
        } else {
            head = fromNode.next;
        }
        if (fromNode.next != null) {
            fromNode.next.prev = prevFrom;
        }

        // Insertar fromNode en la nueva posición
        if (toIndex == 0) {
            fromNode.next = head;
            if (head != null) {
                head.prev = fromNode;
            }
            head = fromNode;
            fromNode.prev = null;
        } else if (toNode != null) {
            fromNode.next = toNode;
            fromNode.prev = prevTo;
            if (prevTo != null) {
                prevTo.next = fromNode;
            }
            toNode.prev = fromNode;
        } else {
            current = head;
            for (int index = 0; index < toIndex && current.next != null; index++) {
                current = current.next;
            }
            fromNode.next = current.next;
            fromNode.prev = current;
            if (current.next != null) {
                current.next.prev = fromNode;
            }
            current.next = fromNode;
        }
    }

    public Node<T> getHead() {
        return head;
    }
}
