package com.sphy.lastfmapi.controller;

import com.sphy.lastfmapi.tasks.SearchArtistTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private SearchArtistTask searchArtistTask;
    private ObservableList<String> listNames;
    private ObservableList<String> listAlbumsNames;

    @FXML
    private TextField searchField;

    @FXML
    private Button buscarButton;
    @FXML
    private Label artistInfoLabel;
    @FXML
    private ListView<String> tagsListView;
    @FXML
    private ListView<String> albumsListView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void searchArtist(ActionEvent event) {
        this.listNames = FXCollections.observableArrayList();
        this.listAlbumsNames = FXCollections.observableArrayList();
        String artistName = searchField.getText();
        searchField.clear();
        searchField.requestFocus();
        this.tagsListView.setItems(this.listNames);
        this.albumsListView.setItems(this.listAlbumsNames);

        // impresiones para depurar
        System.out.println("ejecuta SearchArtistTask.");

        searchArtistTask = new SearchArtistTask(artistName, this.listNames, this.listAlbumsNames);
        new Thread(searchArtistTask).start();
    }
    @FXML
    public void exitApplication (ActionEvent event){

        Platform.exit();

    }

}
