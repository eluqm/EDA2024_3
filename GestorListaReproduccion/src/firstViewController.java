import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import linkedList.*;

public class firstViewController {

    @FXML
    private ScrollPane myContain; // Contenedor de desplazamiento para la lista de canciones

    @FXML
    private Pane myFirstWindows; // Pane principal de la vista

    @FXML
    private VBox myList; // VBox que contiene la lista de canciones

    private LinkedList<Song> playlist = new LinkedList<>(); // Lista enlazada personalizada de canciones
    private int draggedIndex = -1; // Indice del elemento que se esta arrastrando
    
    @FXML
    private void initialize() {
        // Anade algunas canciones de ejemplo. Mas adelante se leerá el archivo *.csv
        for (int i = 1; i < 10; i++) {
            playlist.add(new Song("Song " + i, "Artist " + i));
        }

        // Actualiza la interfaz de usuario con las canciones
        updateUI();
    }

    private void updateUI() {
        myList.getChildren().clear(); // Limpiar los elementos anteriores del VBox

        Node<Song> current = playlist.getHead(); // Obtener la cabeza de la lista enlazada
        int index = 0;

        // Recorrer la lista enlazada y crear un VBox para cada canción
        while (current != null) {
            VBox songBox = createOrUpdateSongBox(current.data, index); // Crear o actualizar el VBox para la cancion
            myList.getChildren().add(songBox); // Anadir el VBox al VBox principal
            current = current.next; // Avanzar al siguiente nodo
            index++;
        }
    }

    private VBox createOrUpdateSongBox(Song song, int index) {
        VBox box = new VBox(); // Crear un nuevo VBox para la cancion
        box.setUserData(index); // Guardar el índice de la canción en el VBox
        box.getStyleClass().add("custom-list"); // Asignar una clase de estilo CSS al VBox

        Text text = new Text(song.toString()); // Crear un texto con la información de la canción
        box.getChildren().add(text); // Añadir el texto al VBox

        // Evento de inicio de arrastre
        box.setOnDragDetected(event -> {
            Dragboard db = box.startDragAndDrop(TransferMode.MOVE); // Iniciar el arrastre
            ClipboardContent content = new ClipboardContent();
            content.putString(box.toString()); // Poner el contenido en el clipboard
            db.setContent(content);
            draggedIndex = (int) box.getUserData(); // Guardar el índice del VBox arrastrado
            event.consume(); // Consumir el evento
        });

        // Evento cuando se arrastra sobre otro VBox
        box.setOnDragOver(event -> {
            if (event.getGestureSource() != box && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE); // Aceptar el modo de transferencia
            }
            event.consume(); // Consumir el evento
        });

        // Evento cuando se suelta el VBox arrastrado
        box.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString()) {
                int dropIndex = (int) box.getUserData(); // Obtener el índice del VBox sobre el que se solto
                playlist.move(draggedIndex, dropIndex); // Mover la cancion en la lista enlazada
                updateUI(); // Actualizar la interfaz de usuario
                success = true;
            }

            event.setDropCompleted(success); // Indicar que el drop se completo
            event.consume(); // Consumir el evento
        });

        return box; // Devolver el VBox creado
    }
}
