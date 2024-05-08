package com.sphy.lastfmapi.controller;


import com.sphy.lastfmapi.service.LastFMService;
import com.sphy.lastfmapi.tasks.SearchArtistTask;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
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
    private ObservableList<com.sphy.lastfmapi.model.getArtist.Image> listImageUrl;

    private Image artistImage;


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

    @FXML
    private ImageView background;

    @FXML
    private TableView<com.sphy.lastfmapi.model.getArtist.Image> artistImageTable;
    @FXML
    private TableColumn<com.sphy.lastfmapi.model.getArtist.Image, String> sizeColumn;
    @FXML
    private TableColumn<com.sphy.lastfmapi.model.getArtist.Image, ImageView> pngColumn;


    public SearchController(String artistName) {
        this.artistName = artistName;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //artistImage = new Image("C:\\Users\\sanph\\LastfmApi\\src\\main\\resources\\com\\sphy\\lastfmapi\\image\\no-disponible.jpg");

        filterTextField.disableProperty().bind(filterCheckBox.selectedProperty().not());
        filterTextField.textProperty().addListener((observable, oldValue, newValue) -> filterAlbums(newValue));

        this.listNames = FXCollections.observableArrayList();
        this.listAlbumsNames = FXCollections.observableArrayList();
        this.listImageUrl = FXCollections.observableArrayList();

        System.out.println();

        this.tagsListView.setItems(this.listNames);
        this.albumsListView.setItems(this.listAlbumsNames);
        //artistImageView.setImage(artistImage);

        //añadimos la lista al tableView
        artistImageTable.setItems(listImageUrl);

        //añadir valores a las columnas del tableView
        sizeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSize()));

        //añadimos la imagen a la columna del blasón
        pngColumn.setCellValueFactory(param -> {
            ImageView imageView = new ImageView();
            imageView.setFitHeight(50); // Establecer el alto de la imagen
            imageView.setFitWidth(50); // Establecer el ancho de la imagen
            com.sphy.lastfmapi.model.getArtist.Image imageObject = param.getValue();
            Image image = new Image(imageObject.getText(), true);
            imageView.setImage(image);
            return new javafx.beans.property.SimpleObjectProperty<>(imageView);
        });

        searchArtistTask = new SearchArtistTask(artistName, this.listNames, this.listAlbumsNames, this.listImageUrl);

        // Mostrar la barra de estado y la barra de progreso

        progress.setVisible(true);

        progress.progressProperty().bind(searchArtistTask.progressProperty());

        /*searchArtistTask.setOnSucceeded(event -> {

            String urlImage = listImageUrl.get(0);
            Image image = new Image(urlImage);
            artistImageView.setImage(image);
        });*/

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
