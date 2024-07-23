import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SongDatabase database = new SongDatabase();
        database.loadSongsFromFile("songs.csv"); // Ruta del archivo CSV

        SwingUtilities.invokeLater(() -> {
            new Player(database).setVisible(true);
        });
    }
}
