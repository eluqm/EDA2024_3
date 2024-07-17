import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainFirstController {

    @FXML
    private ScrollPane myContain;

    @FXML
    private Pane myFirstWindows;

    @FXML
    private VBox myList;

    private LinkedList<Song> playlist = new LinkedList<>();
    private int draggedIndex = -1;
    
    @FXML
    private void initialize() {
        // Añade algunas canciones de ejemplo
        playlist.add(new Song("Song 1", "Artist 1"));
        playlist.add(new Song("Song 2", "Artist 2"));
        playlist.add(new Song("Song 3", "Artist 3"));
        updateUI();
    }

    private void updateUI() {
        myList.getChildren().clear(); // Limpiar los elementos anteriores
        //myList.getChildren().add(titleLabel); // Reagregar el título

        Node<Song> current = playlist.getHead();
        int index = 0;

        while (current != null) {
            VBox songBox = createOrUpdateSongBox(current.data, index);
            myList.getChildren().add(songBox);
            current = current.next;
            index++;
        }
    }

    private VBox createOrUpdateSongBox(Song song, int index) {
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
            boolean success = false;

            if (db.hasString()) {
                int dropIndex = (int) box.getUserData();
                playlist.move(draggedIndex, dropIndex);
                updateUI();
                success = true;
            }

            event.setDropCompleted(success);
            event.consume();
        });

        return box;
    }

}
