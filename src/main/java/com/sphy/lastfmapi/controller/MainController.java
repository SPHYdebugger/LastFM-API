package com.sphy.lastfmapi.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private MenuItem selectImage;
    @FXML
    private MenuItem selectImages;
    @FXML
    private Button selectOne;
    @FXML
    private Button selectMulti;

    @FXML
    private CheckBox bAndW;
    @FXML
    private CheckBox invertColors;
    @FXML
    private CheckBox shineUp;
    @FXML
    private CheckBox applyBlurred;
    @FXML
    private CheckBox InvertH;
    @FXML
    private CheckBox InvertV;
    @FXML
    private TabPane tpEditImage;
    @FXML
    private Button applyMulti;
    @FXML
    private Button applyDirectory;

    private Stage primaryStage;
    private Image imagenOriginal;
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }



    @FXML
    public void exitApplication (ActionEvent event){
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //que se puedan cerrar las pestañas
        this.tpEditImage.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
    }
}
