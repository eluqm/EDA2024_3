package estructuras;

public class Song implements Comparable<Song> {
    private String artistName;
    private String trackName;
    private String trackID;
    private int popularity;
    private short year;
    private String genre;
    private float danceability;
    private float energy;
    private int key;
    private float loudness;
    private int mode;
    private float speechiness;
    private float acousticness;
    private float instrumentalness;
    private float liveness;
    private float valence;
    private float tempo;
    private int durationms;
    private int timeSignature;

    public Song(String artistName, String trackName, String trackID, int popularity, short year, String genre,
                float danceability, float energy, int key, float loudness, int mode, float speechiness,
                float acousticness, float instrumentalness, float liveness, float valence, float tempo,
                int durationms, int timeSignature) {
        this.artistName = artistName;
        this.trackName = trackName;
        this.trackID = trackID;
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
        this.durationms = durationms;
        this.timeSignature = timeSignature;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getTrackID() {
        return trackID;
    }

    public int getPopularity() {
        return popularity;
    }

    public short getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public float getDanceability() {
        return danceability;
    }

    public float getEnergy() {
        return energy;
    }

    public int getKey() {
        return key;
    }

    public float getLoudness() {
        return loudness;
    }

    public int getMode() {
        return mode;
    }

    public float getSpeechiness() {
        return speechiness;
    }

    public float getAcousticness() {
        return acousticness;
    }

    public float getInstrumentalness() {
        return instrumentalness;
    }

    public float getLiveness() {
        return liveness;
    }

    public float getValence() {
        return valence;
    }

    public float getTempo() {
        return tempo;
    }

    public int getDurationms() {
        return durationms;
    }

    public int getTimeSignature() {
        return timeSignature;
    }

    @Override
    public int compareTo(Song o) {
        return this.trackID.compareTo(o.trackID);
    }
}
