package com.sphy.lastfmapi.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphy.lastfmapi.model.getAlbums.Album;
import com.sphy.lastfmapi.model.getArtist.Tag;
import com.sphy.lastfmapi.util.Constants;
import io.reactivex.Observable;
import javafx.scene.image.Image;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Optional;

public class LastFMService {

    private ArtistAPI artistAPI;
    Image artistImage;

    public LastFMService() {
        try {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();

            ObjectMapper objectMapper = new ObjectMapper();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            artistAPI = retrofit.create(ArtistAPI.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Observable de objetos Tag
    public Observable<Tag> getTags(String artistName) {

        // impresión para depurar
        System.out.println("getTags iniciado");
        System.out.println("Artista buscado: "+ artistName);

        return this.artistAPI.getArtistInformation(artistName, Constants.API_KEY, "json")
                .map(information -> information.getArtist())
                .map(artist -> artist.getTags())
                .map(tags -> tags.getTag())
                .flatMapIterable(tag -> tag);
    }

    //Observable de objetos Image
    public Observable<com.sphy.lastfmapi.model.getArtist.Image> getImageUrl(String artistName) {

        // impresión para depurar
        System.out.println("getImage iniciado");
        System.out.println("Artista buscado: " + artistName);

        return this.artistAPI.getArtistInformation(artistName, Constants.API_KEY, "json")
                .map(information -> information.getArtist())
                .map(artist -> artist.getImage())
                .flatMapIterable(image -> image);


    }


    //Observabeld de objetos Album
    public Observable<Album> getAlbumsInformation(String artistName) {

        // impresión para depurar
        System.out.println("getAlbumsName iniciado");
        System.out.println("Artista buscado: "+ artistName);

        return this.artistAPI.getAlbumsInformation(artistName, Constants.API_KEY, "json")
                .map(informationAlbums -> informationAlbums.getTopalbums())
                .map(topAlbums -> topAlbums.getAlbum())
                .flatMapIterable(album -> album);

    }

}
