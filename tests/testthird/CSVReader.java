import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    // Método para leer el archivo CSV y transformar sus líneas en objetos Song e insertarlos en el BTree
    public void readCSV(String filePath, BTree<Short, Song> bTree) {
        String line;
        String csvSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Leer la primera línea (encabezados)
            br.readLine();

            // Leer las líneas del archivo CSV
            while ((line = br.readLine()) != null) {
                String[] songData = line.split(csvSplitBy);

                // Crear un objeto Song con los datos leídos
                Song song = new Song(
                    songData[0], // artistName
                    songData[1], // trackName
                    songData[2], // trackID
                    Integer.parseInt(songData[3]), // popularity
                    Short.parseShort(songData[4]), // year
                    songData[5], // genre
                    songData[6], // danceability
                    songData[7], // energy
                    songData[8], // key
                    songData[9], // loudness
                    songData[10], // mode
                    songData[11], // speechiness
                    songData[12], // acousticness
                    songData[13], // instrumentalness
                    songData[14], // liveness
                    songData[15], // valence
                    songData[16], // tempo
                    Integer.parseInt(songData[17]), // durationms
                    songData[18] // timeSignature
                );

                // Insertar la canción en el árbol B
                bTree.insert(song, song.getYear());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método principal para probar la lectura del CSV y la inserción en el BTree
    public static void main(String[] args) {
        BTree<Short, Song> bTree = new BTree<>();
        CSVReader csvReader = new CSVReader();
        
        // Reemplaza "ruta/al/archivo.csv" con la ruta real de tu archivo CSV
        csvReader.readCSV(songs.csv, bTree);
        
        // Recorrer y mostrar el árbol B
        bTree.traverse();
    }
}
