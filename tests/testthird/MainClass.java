public class MainClass {
    public static void main(String[] args) {
        BTree<String, Song> bTree = new BTree<>();

        // Crear algunas canciones
        Song song1 = new Song("Artist A", "Track 1", (short) 2020);
        Song song2 = new Song("Artist B", "Track 2", (short) 2021);
        Song song3 = new Song("Artist C", "Track 3", (short) 2020);
        Song song4 = new Song("Artist D", "Track 4", (short) 2022);
        Song song5 = new Song("Artist E", "Track 5", (short) 2021);
        Song song6 = new Song("Artist F", "Track 6", (short) 2022);

        // Insertar canciones en el árbol B
        bTree.insert(song1, song1.getArtistName());
        bTree.insert(song2, song2.getArtistName());
        bTree.insert(song3, song3.getArtistName());
        bTree.insert(song4, song4.getArtistName());
        bTree.insert(song5, song5.getArtistName());
        bTree.insert(song6, song6.getArtistName());

        // Recorrer y mostrar el árbol B en orden ascendente
        System.out.println("Recorrido del árbol B en orden ascendente:");
        bTree.traverse();
        System.out.println();

        // Recorrer y mostrar el árbol B en orden descendente
        System.out.println("Recorrido del árbol B en orden descendente:");
        bTree.traverseDescending();
        System.out.println();

        Song song = new Song("Artist A", "Track 1", (short) 2020);

        // Eliminar una canción del árbol B
        bTree.delete(song2, song2.getArtistName());
        System.out.println("Recorrido del árbol B en orden ascendente:");
        bTree.traverse();
    }
}
