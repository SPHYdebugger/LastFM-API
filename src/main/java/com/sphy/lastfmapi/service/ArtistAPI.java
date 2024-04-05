package com.sphy.lastfmapi.service;


import com.sphy.lastfmapi.model.Artist;
import com.sphy.lastfmapi.model.Information;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ArtistAPI {


    @GET("?method=artist.getinfo")
    Observable<Information> getInformation(@Query("artist") String artist, @Query("api_key") String apiKey, @Query("format") String format);



}
