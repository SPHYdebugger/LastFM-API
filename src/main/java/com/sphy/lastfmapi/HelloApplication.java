package com.sphy.lastfmapi;

import com.sphy.lastfmapi.controller.HelloControler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class HelloApplication extends Application {



    @Override
    public void init() throws Exception {

        // impresión para depurar
        System.out.println("Initializing Application");

        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Cargar el fxml y definir el controlador
        FXMLLoader uiLoader = new FXMLLoader(getClass().getResource("controller/hello-view.fxml"));
        uiLoader.setController(new HelloControler());
        //Mostrar la escena
        Scene helloScene = new Scene(uiLoader.load());
        primaryStage.getIcons().add(new Image(getClass().getResource("images/Diseno-sin-texto.png").openStream()));
        primaryStage.setTitle("LastFM API V1.0");
        primaryStage.setScene(helloScene);
        primaryStage.show();

        // Establecer el Stage en el controlador (primaryDtage)
        HelloControler helloController = uiLoader.getController();
        helloController.setPrimaryStage(primaryStage);
    }



    @Override
    public void stop() throws Exception {
        System.out.println("Application finished");
        super.stop();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }


}