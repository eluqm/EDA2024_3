package estructuras;

public class MinHeap {
    private DynamicArray<Integer> heap;

    public MinHeap() {
        heap = new DynamicArray<>();
    }

    private int getParentIndex(int index) { return (index - 1) / 2; }
    private int getLeftChildIndex(int index) { return 2 * index + 1; }
    private int getRightChildIndex(int index) { return 2 * index + 2; }

    private boolean hasParent(int index) { return getParentIndex(index) >= 0; }
    private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < heap.getSize(); }
    private boolean hasRightChild(int index) { return getRightChildIndex(index) < heap.getSize(); }

    private int parent(int index) { return heap.get(getParentIndex(index)); }
    private int leftChild(int index) { return heap.get(getLeftChildIndex(index)); }
    private int rightChild(int index) { return heap.get(getRightChildIndex(index)); }

    private void swap(int indexOne, int indexTwo) {
        int temp = heap.get(indexOne);
        heap.set(indexOne, heap.get(indexTwo));
        heap.set(indexTwo, temp);
    }

    public void add(int item) {
        heap.add(item);
        heapifyUp();
    }

    public int peek() {
        if (heap.getSize() == 0) throw new IllegalStateException("Heap is empty");
        return heap.get(0);
    }

    public int poll() {
        if (heap.getSize() == 0) throw new IllegalStateException("Heap is empty");
        int item = heap.get(0);
        heap.set(0, heap.get(heap.getSize() - 1));
        heap.remove(heap.getSize() - 1);
        heapifyDown();
        return item;
    }

    private void heapifyUp() {
        int index = heap.getSize() - 1;
        while (hasParent(index) && parent(index) > heap.get(index)) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }
            if (heap.get(index) < heap.get(smallerChildIndex)) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

    public int getSize() {
        return heap.getSize();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
