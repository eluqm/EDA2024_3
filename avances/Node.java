public class Node <K extends Comparable <K>,V>{
    private Node <K,V> next;
    private Node <K,V> prev;
    private K key;
    private V value;
    
    public Node (Node <K,V> prev, K key, V value, Node <K,V> next){
        this.next = next;
        this.value = value;
        this.key = key;
        this.prev = prev;
    }
    
    public Node (K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }

    public Node<K, V> getPrev() {
        return prev;
    }

    public void setPrev(Node<K, V> prev) {
        this.prev = prev;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }
    @Override
    public String toString(){
        return "(" +this.key + ")" + this.value + ", ";
    }
}
