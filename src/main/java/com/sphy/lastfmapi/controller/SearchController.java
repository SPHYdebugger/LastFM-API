package com.sphy.lastfmapi.controller;

import com.sphy.lastfmapi.tasks.SearchArtistTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

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

    public SearchController(String artistName) {
        this.artistName = artistName;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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



}
