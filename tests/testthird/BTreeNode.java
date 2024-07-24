import java.util.ArrayList;
import java.util.List;

class BTreeNode<K extends Comparable<K>, T> {
    List<OrderAttribute<K, T>> keys;
    List<BTreeNode<K, T>> children;
    boolean isLeaf;

    public BTreeNode(boolean isLeaf) {
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
        this.isLeaf = isLeaf;
    }
}
