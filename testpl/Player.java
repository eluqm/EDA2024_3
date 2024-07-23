import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends JFrame {
    private SongDatabase database;
    private DefaultListModel<Song> listModel;

    public Player(SongDatabase database) {
        this.database = database;
        this.listModel = new DefaultListModel<>();

        setTitle("Reproductor de Música");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JList<Song> songList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(songList);

        JPanel controlPanel = new JPanel(new GridLayout(1, 4));
        JButton addButton = new JButton("Agregar Canción");
        JButton removeButton = new JButton("Eliminar Canción");
        JButton shuffleButton = new JButton("Reproducción Aleatoria");
        JButton sortByPopularityButton = new JButton("Ordenar por Popularidad");
        JButton sortByYearButton = new JButton("Ordenar por Año");
        JButton sortByDurationButton = new JButton("Ordenar por Duración");
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Buscar");

        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(shuffleButton);
        controlPanel.add(sortByPopularityButton);
        controlPanel.add(sortByYearButton);
        controlPanel.add(sortByDurationButton);
        controlPanel.add(searchField);
        controlPanel.add(searchButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Diálogo para agregar nueva canción
                JTextField artistField = new JTextField();
                JTextField trackField = new JTextField();
                JTextField trackIdField = new JTextField();
                JTextField popularityField = new JTextField();
                JTextField yearField = new JTextField();
                JTextField genreField = new JTextField();
                JTextField danceabilityField = new JTextField();
                JTextField energyField = new JTextField();
                JTextField keyField = new JTextField();
                JTextField loudnessField = new JTextField();
                JTextField modeField = new JTextField();
                JTextField speechinessField = new JTextField();
                JTextField acousticnessField = new JTextField();
                JTextField instrumentalnessField = new JTextField();
                JTextField livenessField = new JTextField();
                JTextField valenceField = new JTextField();
                JTextField tempoField = new JTextField();
                JTextField durationField = new JTextField();
                JTextField timeSignatureField = new JTextField();

                Object[] fields = {
                    "Artista:", artistField,
                    "Nombre de Canción:", trackField,
                    "ID de Canción:", trackIdField,
                    "Popularidad:", popularityField,
                    "Año:", yearField,
                    "Género:", genreField,
                    "Danceability:", danceabilityField,
                    "Energy:", energyField,
                    "Key:", keyField,
                    "Loudness:", loudnessField,
                    "Mode:", modeField,
                    "Speechiness:", speechinessField,
                    "Acousticness:", acousticnessField,
                    "Instrumentalness:", instrumentalnessField,
                    "Liveness:", livenessField,
                    "Valence:", valenceField,
                    "Tempo:", tempoField,
                    "Duración (ms):", durationField,
                    "Time Signature:", timeSignatureField
                };

                int result = JOptionPane.showConfirmDialog(null, fields, "Agregar Canción", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String artist = artistField.getText();
                    String track = trackField.getText();
                    String trackId = trackIdField.getText();
                    int popularity = Integer.parseInt(popularityField.getText());
                    int year = Integer.parseInt(yearField.getText());
                    String genre = genreField.getText();
                    double danceability = Double.parseDouble(danceabilityField.getText());
                    double energy = Double.parseDouble(energyField.getText());
                    int key = Integer.parseInt(keyField.getText());
                    double loudness = Double.parseDouble(loudnessField.getText());
                    int mode = Integer.parseInt(modeField.getText());
                    double speechiness = Double.parseDouble(speechinessField.getText());
                    double acousticness = Double.parseDouble(acousticnessField.getText());
                    double instrumentalness = Double.parseDouble(instrumentalnessField.getText());
                    double liveness = Double.parseDouble(livenessField.getText());
                    double valence = Double.parseDouble(valenceField.getText());
                    double tempo = Double.parseDouble(tempoField.getText());
                    int durationMs = Integer.parseInt(durationField.getText());
                    int timeSignature = Integer.parseInt(timeSignatureField.getText());

                    Song newSong = new Song(artist, track, trackId, popularity, year, genre, danceability, energy, key, loudness, mode, speechiness, acousticness, instrumentalness, liveness, valence, tempo, durationMs, timeSignature);
                    database.addSong(newSong);
                    listModel.addElement(newSong);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Song selectedSong = songList.getSelectedValue();
                if (selectedSong != null) {
                    database.removeSong(selectedSong);
                    listModel.removeElement(selectedSong);
                }
            }
        });

        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.clear();
                for (Song song : database.getShuffledSongs()) {
                    listModel.addElement(song);
                }
            }
        });

        sortByPopularityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.clear();
                for (Song song : database.getSongsByPopularity(true)) {
                    listModel.addElement(song);
                }
            }
        });

        sortByYearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.clear();
                for (Song song : database.getSongsByYear(true)) {
                    listModel.addElement(song);
                }
            }
        });

        sortByDurationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.clear();
                for (Song song : database.getSongsByDuration(true)) {
                    listModel.addElement(song);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchField.getText();
                if (!searchQuery.isEmpty()) {
                    Song result = database.searchSong(searchQuery);
                    if (result != null) {
                        listModel.clear();
                        listModel.addElement(result);
                    } else {
                        JOptionPane.showMessageDialog(null, "Canción no encontrada.");
                    }
                }
            }
        });
    }
}
