package com.sphy.lastfmapi.tasks;

import com.sphy.lastfmapi.model.getAlbums.Album;
import com.sphy.lastfmapi.model.getArtist.Image;
import com.sphy.lastfmapi.model.getArtist.Tag;
import com.sphy.lastfmapi.service.LastFMService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class SearchArtistTask extends Task<Integer> {

    private String artistName;
    private ObservableList<String> listTagNames;
    private ObservableList<String> listAlbumsNames;
    private ObservableList<Image> artistImageUrl;
    int progress = 0;


    public SearchArtistTask(String artistName, ObservableList<String> listNames, ObservableList<String> listAlbumsNames, ObservableList<Image> artistImageUrl){
        this.artistName = artistName;
        this.listTagNames = listNames;
        this.listAlbumsNames = listAlbumsNames;
        this.artistImageUrl = artistImageUrl;
    }

    @Override
    protected Integer call() throws Exception {

        // impresión para depurar
        System.out.println("Método call lanzado");

        LastFMService lastFMService = new LastFMService();

        //Consumidor de observable de Tag
        Consumer<Tag> user = (tag) -> {
            Thread.sleep(400);
            Platform.runLater(() ->this.listTagNames.add(tag.getName()));
        };

        //Consumidor de observable de Album
        Consumer<Album> user2 = (album) -> {
            Thread.sleep(400);
            Platform.runLater(() ->this.listAlbumsNames.add(album.getName()));
            progress++;
            updateProgress(progress + 1, 60);
        };

        //Consumidor de observable de Image
        Consumer<Image> user3 = imageUrl -> {
                Platform.runLater(() -> {
                    this.artistImageUrl.add(imageUrl);
                    System.out.println(imageUrl);
                });
        };

        //Los consumidores se subscriben a los observables
        lastFMService.getImageUrl(artistName).subscribe(user3);
        lastFMService.getTags(artistName).subscribe(user);
        lastFMService.getAlbumsInformation(artistName).subscribe(user2);

        //actualizar la barra de progreso
        updateProgress(100, 60);

        return null;
    }


}