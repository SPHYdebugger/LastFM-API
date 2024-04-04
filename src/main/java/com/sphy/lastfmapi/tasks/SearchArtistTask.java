package com.sphy.lastfmapi.tasks;

import com.sphy.lastfmapi.model.Tag;
import com.sphy.lastfmapi.service.LastFMService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class SearchArtistTask extends Task<Integer> {

    private String artistName;
    private ObservableList<String > tags;

    public SearchArtistTask(String artistName, ObservableList<String> tags){
        this.artistName = artistName;
        this.tags = tags;
    }

    @Override
    protected Integer call() throws Exception {

        LastFMService lastFMService = new LastFMService();

        Consumer<Tag> user = (tag) -> {
            Thread.sleep(400);
            Platform.runLater(() ->this.tags.add(tag.getName()));
        };
        lastFMService.getTags(artistName).subscribe(user);
        return null;
    }


}