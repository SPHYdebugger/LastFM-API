package com.sphy.lastfmapi.service;


import com.sphy.lastfmapi.model.ArtistInformation;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ArtistAPI {


    @GET("?method=artist.getinfo")
    Observable<ArtistInformation> getInformation(@Query("artist") String artist, @Query("api_key") String apiKey, @Query("format") String format);



}
