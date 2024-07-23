public class Song {
    private String artistName;
    private String trackName;
    private String trackId;
    private int popularity;
    private int year;
    private String genre;
    private double danceability;
    private double energy;
    private int key;
    private double loudness;
    private int mode;
    private double speechiness;
    private double acousticness;
    private double instrumentalness;
    private double liveness;
    private double valence;
    private double tempo;
    private int durationMs;
    private int timeSignature;

    public Song(String artistName, String trackName, String trackId, int popularity, int year, String genre,
                double danceability, double energy, int key, double loudness, int mode, double speechiness,
                double acousticness, double instrumentalness, double liveness, double valence, double tempo,
                int durationMs, int timeSignature) {
        this.artistName = artistName;
        this.trackName = trackName;
        this.trackId = trackId;
        this.popularity = popularity;
        this.year = year;
        this.genre = genre;
        this.danceability = danceability;
        this.energy = energy;
        this.key = key;
        this.loudness = loudness;
        this.mode = mode;
        this.speechiness = speechiness;
        this.acousticness = acousticness;
        this.instrumentalness = instrumentalness;
        this.liveness = liveness;
        this.valence = valence;
        this.tempo = tempo;
        this.durationMs = durationMs;
        this.timeSignature = timeSignature;
    }

    // Getters y setters para todos los campos

    @Override
    public String toString() {
        return trackName + " - " + artistName;
    }
}
