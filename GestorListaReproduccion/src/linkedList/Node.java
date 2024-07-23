package linkedList;
public class Node<T> {
    
    public Node<T> prev;
    public T data;
    public Node<T> next;

    public Node(T data) {
        this.prev = null;
        this.data = data;
        this.next = null;
    }
}