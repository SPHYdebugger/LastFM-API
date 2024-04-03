package com.sphy.lastfmapi.tasks;

import com.sphy.lastfmapi.model.ArtistInformation;
import com.sphy.lastfmapi.model.Tag;
import com.sphy.lastfmapi.service.LastFMService;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.util.List;

public class SearchArtistTask extends Task<Integer> {

    private String artistName;
    private ObservableList<String> tags;

    public SearchArtistTask(String artistName, ObservableList<String> tags){
        this.artistName = artistName;
        this.tags = tags;
    }

    @Override
    protected Integer call() {
        try {
            LastFMService lastFMService = new LastFMService();
            Observable<ArtistInformation> observable = lastFMService.getInformation(artistName);
            observable.subscribe(
                    artistInformation -> {
                        System.out.println(artistInformation);
                    },
                    error -> {
                        System.err.println("Error al obtener la información: " + error.getMessage());
                    },
                    () -> {
                        System.out.println("El proceso ha finalizado.");
                    }
            );
            Observer<ArtistInformation> observer = new Observer<>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(ArtistInformation artistInformation) {
                    System.out.println("call");

                    for (Tag tag : artistInformation.getTags()) {
                        Platform.runLater(() -> tags.add(tag.getName()));
                    }
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onComplete() {

                }
            };
            lastFMService.getInformation(artistName).subscribe(observer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
