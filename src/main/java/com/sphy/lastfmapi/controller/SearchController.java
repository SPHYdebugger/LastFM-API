package com.sphy.lastfmapi.controller;


import com.sphy.lastfmapi.tasks.SearchArtistTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SearchController implements Initializable {

    String artistName;

    private TabPane tabPane;


    private SearchArtistTask searchArtistTask;
    private ObservableList<String> listNames;
    private ObservableList<String> listAlbumsNames;


    @FXML
    private ListView<String> tagsListView;
    @FXML
    private ListView<String> albumsListView;
    @FXML
    private ProgressBar progress;
    @FXML
    private Label status;
    @FXML
    private CheckBox filterCheckBox;
    @FXML
    private ImageView artistImageView;
    @FXML
    private TextField filterTextField;

    public SearchController(String artistName) {
        this.artistName = artistName;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        filterTextField.disableProperty().bind(filterCheckBox.selectedProperty().not());
        filterTextField.textProperty().addListener((observable, oldValue, newValue) -> filterAlbums(newValue));

        this.listNames = FXCollections.observableArrayList();
        this.listAlbumsNames = FXCollections.observableArrayList();

        this.tagsListView.setItems(this.listNames);
        this.albumsListView.setItems(this.listAlbumsNames);

        searchArtistTask = new SearchArtistTask(artistName, this.listNames, this.listAlbumsNames);

        // Mostrar la barra de estado y la barra de progreso

        progress.setVisible(true);

        progress.progressProperty().bind(searchArtistTask.progressProperty());

        new Thread(searchArtistTask).start();




    }

    private void filterAlbums(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            albumsListView.setItems(listAlbumsNames);
        } else {
            ObservableList<String> filteredAlbums = listAlbumsNames.stream()
                    .filter(album -> album.toLowerCase().contains(filterText.toLowerCase()))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            albumsListView.setItems(filteredAlbums);
        }
    }



}
