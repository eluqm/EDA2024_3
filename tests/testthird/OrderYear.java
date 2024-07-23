import java.util.List;
import java.util.ArrayList;

class OrderYear<T extends Comparable<T>> implements Comparable<OrderYear<T>> {
    private short year;
    private List<T> listMusic;

    public OrderYear(short year) {
        this.year = year;
        this.listMusic = new ArrayList<>();
    }

    public short getYear() {
        return year;
    }

    public List<T> getListMusic() {
        return listMusic;
    }

    public void addMusic(T song) {
        listMusic.add(song);
    }

    public void removeMusic(String artistName, String trackName, short year) {
        listMusic.removeIf(song -> ((Song) song).getArtistName().equals(artistName) && ((Song) song).getTrackName().equals(trackName) && ((Song) song).getYear() == year);
    }

    @Override
    public String toString() {
        return "Year: " + year + "\n" + listMusic;
    }

    @Override
    public int compareTo(OrderYear<T> orderYear) {
        return Short.compare(this.year, orderYear.year);
    }
}