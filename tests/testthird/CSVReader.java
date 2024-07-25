import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    // Método para leer el archivo CSV y transformar sus líneas en objetos Song e insertarlos en el BTree
    public void readCSV(String filePath, BTree<Short, Song> bTree) {
        String line;
        String csvSplitBy = ",";
        
        long a = System.nanoTime();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Leer la primera línea (encabezados)
            br.readLine();

            // Leer las líneas del archivo CSV
            while ((line = br.readLine()) != null) {
                String[] songData = line.split(csvSplitBy);

                // Asegurarse de que hay suficientes datos
                if (songData.length < 19) {
                    continue;
                }

                try {
                    // Crear un objeto Song con los datos leídos
                    Song song = new Song(
                        songData[1], // artistName
                        songData[2], // trackName
                        songData[3], // trackID
                        Integer.parseInt(songData[4]), // popularity
                        Short.parseShort(songData[5]), // year
                        songData[6], // genre
                        songData[7], // danceability
                        songData[8], // energy
                        songData[9], // key
                        songData[10], // loudness
                        songData[11], // mode
                        songData[12], // speechiness
                        songData[13], // acousticness
                        songData[14], // instrumentalness
                        songData[15], // liveness
                        songData[16], // valence
                        songData[17], // tempo
                        Integer.parseInt(songData[18]), // durationms
                        songData[19] // timeSignature
                    );
                    // Insertar la canción en el árbol B
                    bTree.insert(song, song.getYear());
                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear los datos de la canción: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
         long b = System.nanoTime();
         System.out.println(b-a);
    }
    
    // Método principal para probar la lectura del CSV y la inserción en el BTree
    public static void main(String[] args) {
        BTree<Short, Song> bTree = new BTree<>();
        CSVReader csvReader = new CSVReader();
        
        // Reemplaza "ruta/al/archivo.csv" con la ruta real de tu archivo CSV
        csvReader.readCSV("songs.csv", bTree);
        
        // Recorrer y mostrar el árbol B
        bTree.traverse();

      
    }

}
