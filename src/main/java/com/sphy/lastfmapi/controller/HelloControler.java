package com.sphy.lastfmapi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class HelloControler {


    @FXML
    private Button BtStart;


    private Stage primaryStage;


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @FXML
    protected void startApp() {
        try {
            //cargar el fxml
            FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            // definir el controlador
            secondLoader.setController(new MainController());
            //cargar la escena
            Scene indexScene = new Scene(secondLoader.load());
            primaryStage.setScene(indexScene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
