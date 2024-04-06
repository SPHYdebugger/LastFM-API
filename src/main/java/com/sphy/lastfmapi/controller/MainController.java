package com.sphy.lastfmapi.controller;

import com.sphy.lastfmapi.tasks.SearchArtistTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {



    @FXML
    private TextField searchField;

    @FXML
    private Button buscarButton;
    @FXML
    private Label artistInfoLabel;
    @FXML
    private TabPane search;


    @FXML
    public void searchArtist(ActionEvent event) {
        String artistName = searchField.getText();
        searchField.clear();
        searchField.requestFocus();

        Tab newTab = new Tab();
        newTab.setText(artistName);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
        SearchController searchController = new SearchController(artistName);
        searchController.setTabPane(search);
        loader.setController(searchController);

        try {
            newTab.setContent(loader.load());
            search.getTabs().add(newTab);

            // Seleccionar la nueva pestaña
            search.getSelectionModel().select(newTab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





    }


    @FXML
    public void exitApplication (ActionEvent event){

        Platform.exit();

    }

}
