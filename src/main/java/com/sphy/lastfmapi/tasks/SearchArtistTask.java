package com.sphy.lastfmapi.tasks;

import com.sphy.lastfmapi.model.Tag;
import com.sphy.lastfmapi.service.LastFMService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class SearchArtistTask extends Task<Integer> {

    private String artistName;
    private ObservableList<String > listNames;

    public SearchArtistTask(String artistName, ObservableList<String> listNames){
        this.artistName = artistName;
        this.listNames = listNames;
    }

    @Override
    protected Integer call() throws Exception {

        // impresión para depurar
        System.out.println("Método call lanzado");

        LastFMService lastFMService = new LastFMService();

        Consumer<Tag> user = (tag) -> {
            Thread.sleep(400);
            Platform.runLater(() ->this.listNames.add(tag.getName()));
        };
        lastFMService.getTags(artistName).subscribe(user);
        return null;
    }


}