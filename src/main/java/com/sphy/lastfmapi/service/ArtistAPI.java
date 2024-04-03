package com.sphy.lastfmapi.service;


import com.sphy.lastfmapi.model.ArtistInformation;
import io.reactivex.Observable;
import javafx.collections.ObservableList;
import retrofit2.http.GET;
import retrofit2.http.Path;
import com.sphy.lastfmapi.util.Constants;
import java.util.List;


public interface ArtistAPI {

    /*@GET("/2.0/?method=artist.getinfo&artist={artist}&api_key=875c7d914c654e588d1a6670988b9680&format=json")
    Observable<ArtistInformation> getInformation(@Path("artist") String artist);*/

    @GET("/2.0/?method=artist.getinfo&artist={artist}&api_key="+Constants.API_KEY+"&format=json")
    Observable<List<ArtistInformation>> getInformation(@Path("artist") String artist);
}
