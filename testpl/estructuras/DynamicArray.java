package estructuras;

public class DynamicArray<T> {
    private Object[] data;
    private int size;
    private int capacity;

    public DynamicArray() {
        capacity = 10;
        data = new Object[capacity];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return (T) data[index];
    }

    public void add(T element) {
        ensureCapacity();
        data[size++] = element;
    }

    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        ensureCapacity();
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            Object[] newData = new Object[capacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }
}
