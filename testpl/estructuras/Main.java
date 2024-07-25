import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String csvFile = "songs.csv";
        String line;
        String cvsSplitBy = ",";
        BTree<Song> bTree = new BTree<>(3);  // Grado mínimo t = 3

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // Leer y descartar la línea de encabezado

            while ((line = br.readLine()) != null) {
                String[] songData = line.split(cvsSplitBy);
                Song song = new Song(
                        songData[0], // artistName
                        songData[1], // trackName
                        songData[2], // trackID
                        Integer.parseInt(songData[3]), // popularity
                        Short.parseShort(songData[4]), // year
                        songData[5], // genre
                        Float.parseFloat(songData[6]), // danceability
                        Float.parseFloat(songData[7]), // energy
                        Integer.parseInt(songData[8]), // key
                        Float.parseFloat(songData[9]), // loudness
                        Integer.parseInt(songData[10]), // mode
                        Float.parseFloat(songData[11]), // speechiness
                        Float.parseFloat(songData[12]), // acousticness
                        Float.parseFloat(songData[13]), // instrumentalness
                        Float.parseFloat(songData[14]), // liveness
                        Float.parseFloat(songData[15]), // valence
                        Float.parseFloat(songData[16]), // tempo
                        Integer.parseInt(songData[17]), // durationms
                        Integer.parseInt(songData[18])  // timeSignature
                );
                bTree.insert(song);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Recorrer el B-Tree
        bTree.traverse();

        // Imprimir los nombres de todas las canciones en el B-Tree
        System.out.println("\nNombres de todas las canciones:");
        printAllSongs(bTree);
    }

    public static void printAllSongs(BTree<Song> bTree) {
        if (bTree.root == null) {
            return;
        }

        MyQueue<BTreeNode<Song>> queue = new MyQueue<>();
        queue.enqueue(bTree.root);

        while (!queue.isEmpty()) {
            BTreeNode<Song> current = queue.dequeue();

            for (int i = 0; i < current.n; i++) {
                System.out.println(current.keys.get(i).getTrackName());
            }

            if (!current.leaf) {
                for (int i = 0; i <= current.n; i++) {
                    queue.enqueue(current.children.get(i));
                }
            }
        }
    }
}
