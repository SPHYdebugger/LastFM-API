package com.sphy.lastfmapi.tasks;

import com.sphy.lastfmapi.model.getAlbums.Album;
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
    int progress = 0;

    public SearchArtistTask(String artistName, ObservableList<String> listNames, ObservableList<String> listAlbumsNames){
        this.artistName = artistName;
        this.listTagNames = listNames;
        this.listAlbumsNames = listAlbumsNames;
    }

    @Override
    protected Integer call() throws Exception {

        // impresión para depurar
        System.out.println("Método call lanzado");

        LastFMService lastFMService = new LastFMService();

        Consumer<Tag> user = (tag) -> {
            Thread.sleep(400);
            Platform.runLater(() ->this.listTagNames.add(tag.getName()));
        };
        Consumer<Album> user2 = (album) -> {
            Thread.sleep(400);
            Platform.runLater(() ->this.listAlbumsNames.add(album.getName()));
            progress++;
            updateProgress(progress + 1, 60);

        };

        lastFMService.getTags(artistName).subscribe(user);
        lastFMService.getAlbumsInformation(artistName).subscribe(user2);
        updateProgress(100, 60);


        return null;
    }


}