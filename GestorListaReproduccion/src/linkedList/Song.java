package linkedList;
// Esta clase solo es de ejemplo, mas adelante se hara una clase de acuerdo al archivo *.csv
public class Song {
    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return title + " by " + artist;
    }
}
