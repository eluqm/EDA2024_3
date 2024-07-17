import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class MainApp extends Application {
    private LinkedList<Song> playlist = new LinkedList<>();
    private VBox songContainer = new VBox();
    private int draggedIndex = -1;

    @Override
    public void start(Stage primaryStage) {
        playlist.add(new Song("Song 1", "Artist 1"));
        playlist.add(new Song("Song 2", "Artist 2"));
        playlist.add(new Song("Song 3", "Artist 3"));

        updateUI();

        Scene scene = new Scene(songContainer, 300, 250);
        primaryStage.setTitle("Playlist");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateUI() {
        songContainer.getChildren().clear();
        Node<Song> current = playlist.getHead();
        int index = 0;

        while (current != null) {
            System.out.println((current.data));
            VBox songBox = createSongBox(current.data, index);
            songContainer.getChildren().add(songBox);
            current = current.next;
            index++;
        }
    }

    private VBox createSongBox(Song song, int index) {
        VBox box = new VBox();
        box.setUserData(index);
        box.setStyle("-fx-border-color: black; -fx-padding: 10;");
        Text text = new Text(song.toString());
        box.getChildren().add(text);

        box.setOnDragDetected(event -> {
            Dragboard db = box.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(box.toString());
            db.setContent(content);
            draggedIndex = (int) box.getUserData();
            event.consume();
        });

        box.setOnDragOver(event -> {
            if (event.getGestureSource() != box && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        box.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                int newIndex = (int) box.getUserData();
                playlist.move(draggedIndex, newIndex);
                updateUI();
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });

        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}


