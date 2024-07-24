import java.util.List;
import java.util.ArrayList;

class OrderAttribute<K extends Comparable<K>, T> implements Comparable<OrderAttribute<K, T>>  {
    private K attributeKey;
    private List<T> listMusic;

    public OrderAttribute(K attributeKey) {
        this.attributeKey = attributeKey;
        this.listMusic = new ArrayList<>();
    }

    public K getAttributeKey() {
        return attributeKey;
    }

    public List<T> getListMusic() {
        return listMusic;
    }

    public void addMusic(T song) {
        listMusic.add(song);
    }

    public void removeMusic(T song) {
        listMusic.remove(song);
    }

    @Override
    public String toString() {
        return "Filtro : " + attributeKey + "\n" + listMusic;
    }

    @Override
    public int compareTo(OrderAttribute<K, T> otherAttribute) {
        return this.attributeKey.compareTo(otherAttribute.attributeKey);
    }
}