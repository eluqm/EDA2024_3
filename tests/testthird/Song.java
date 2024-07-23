public class Song implements Comparable<Song> {
    
    private String artistName;
    private String trackName;
    private String trackID;
    private int popularity;
    private short year;
    private String genre;
    private String danceability;
    private String energy;
    private String key;
    private String loudness;
    private String mode;
    private String speechiness;
    private String acousticness;
    private String instrumentalness;
    private String liveness;
    private String valence;
    private String tempo;
    private float duration;
    private String timeSignature;

    //artist_name,track_name,track_id,popularity,year,genre,danceability,energy,key,loudness,mode,speechiness,acousticness,instrumentalness,liveness,valence,tempo,duration_ms,time_signature

    public Song(String artistName, String trackName, String trackID, int popularity, short year, String genre, String danceability, String energy, String key, String loudness, String mode, String speechiness, String acousticness, String instrumentalness, String liveness, String valence, String tempo, int durationms, String timeSignature) {
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
        this.duration = (float) (durationms/60000.0);
        this.timeSignature = timeSignature;
    }

    // Para el usuario
    public Song(String artistName, String trackName, short year) {
        this.artistName = artistName;
        this.trackName = trackName;
        //this.popularity = popularity;
        this.year = year;
        //this.duration = (float) (durationms/60000.0);
    }

    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public int getPopularity() {
        return popularity;
    }
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public short getYear() {
        return year;
    }
    public void setYear(short year) {
        this.year = year;
    }

    public float getDuration() {
        return duration;
    }
    public void setDuration(int durationms) {
        this.duration = (float) (durationms/60000.0);
    }
    public void setDuration(float duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "<" + artistName + "> Compuso: <" + trackName + ">";
    }

    @Override
    public int compareTo(Song other) {
        return Short.compare(this.year, other.year);
    }
}