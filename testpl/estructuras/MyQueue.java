package estructuras;

public class MyQueue<T> {
    private Node<T> front, rear;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public MyQueue() {
        this.front = this.rear = null;
    }

    public void enqueue(T item) {
        Node<T> temp = new Node<>(item);

        if (this.rear == null) {
            this.front = this.rear = temp;
            return;
        }

        this.rear.next = temp;
        this.rear = temp;
    }

    public T dequeue() {
        if (this.front == null) {
            return null;
        }

        T item = this.front.data;
        this.front = this.front.next;

        if (this.front == null) {
            this.rear = null;
        }

        return item;
    }

    public boolean isEmpty() {
        return this.front == null;
    }
}