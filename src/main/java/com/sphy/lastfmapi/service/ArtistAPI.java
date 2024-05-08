package com.sphy.lastfmapi.service;


import com.sphy.lastfmapi.model.getAlbums.InformationAlbums;
import com.sphy.lastfmapi.model.getArtist.InformationArtist;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ArtistAPI {

    //Hacer la petición para obtener la informacion del artista
    @GET("?method=artist.getinfo")
    Observable<InformationArtist> getArtistInformation(@Query("artist") String artist, @Query("api_key") String apiKey, @Query("format") String format);

    //Hacer la petición para obtener la información de los álbumes del artista
    @GET("?method=artist.gettopalbums")
    Observable<InformationAlbums> getAlbumsInformation(@Query("artist") String artist, @Query("api_key") String apiKey, @Query("format") String format);



}
