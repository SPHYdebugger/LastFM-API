package com.sphy.lastfmapi.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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

        // crear nueva pestaña
        Tab newTab = new Tab();
        newTab.setText(artistName);

        //cargar la pestaña y su controlador
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
