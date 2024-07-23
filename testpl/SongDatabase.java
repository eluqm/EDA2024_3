import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import estructuras.*;

public class SongDatabase {
    private BTree<Song> songBTree;

    public SongDatabase() {
        this.songBTree = new BTree<>(3);  // Inicialmente el grado del B-Tree es 3
    }

    public void loadSongsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Saltar la primera línea (encabezados)
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Song song = new Song(
                    values[1], values[2], values[3], Integer.parseInt(values[4]), Integer.parseInt(values[5]), 
                    values[6], Double.parseDouble(values[7]), Double.parseDouble(values[8]), Integer.parseInt(values[9]), 
                    Double.parseDouble(values[10]), Integer.parseInt(values[11]), Double.parseDouble(values[12]), 
                    Double.parseDouble(values[13]), Double.parseDouble(values[14]), Double.parseDouble(values[15]), 
                    Double.parseDouble(values[16]), Double.parseDouble(values[17]), Integer.parseInt(values[18]), 
                    Integer.parseInt(values[19])
                );
                addSong(song);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSong(Song song) {
        songBTree.insert(song);
    }

    public void removeSong(Song song) {
        songBTree.delete(song);
    }

    public List<Song> getSongsByPopularity(boolean ascending) {
        // Implementación de obtención de canciones por popularidad
        return null;  // Temporalmente
    }

    public List<Song> getSongsByYear(boolean ascending) {
        // Implementación de obtención de canciones por año
        return null;  // Temporalmente
    }

    public List<Song> getSongsByDuration(boolean ascending) {
        // Implementación de obtención de canciones por duración
        return null;  // Temporalmente
    }

    public Song searchSong(String trackId) {
        // Implementación de búsqueda de canción
        return null;  // Temporalmente
    }

    public List<Song> getShuffledSongs() {
        // Implementación de obtención de canciones aleatorias
        return null;  // Temporalmente
    }
}
