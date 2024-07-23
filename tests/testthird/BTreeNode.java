import java.util.ArrayList;
import java.util.List;

class BTreeNode<T extends Comparable<T>> {
    List<OrderYear<T>> keys;
    List<BTreeNode<T>> children;
    boolean isLeaf;

    public BTreeNode(boolean isLeaf) {
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
        this.isLeaf = isLeaf;
    }
}