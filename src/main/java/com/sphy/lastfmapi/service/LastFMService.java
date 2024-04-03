package com.sphy.lastfmapi.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sphy.lastfmapi.model.ArtistInformation;
import com.sphy.lastfmapi.model.Tag;
import io.reactivex.Observable;
import com.sphy.lastfmapi.util.Constants;
import javafx.scene.image.Image;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;
import java.util.stream.Collectors;

public class LastFMService {

    private ArtistAPI artistAPI;

    public LastFMService() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gsonParser = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gsonParser))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        artistAPI = retrofit.create(ArtistAPI.class);
    }

    /*public Observable<List<ArtistInformation>> getArtistInformation(String artist) {
        return this.artistAPI.getInformation(artist)
                .subscribeOn(Schedulers.io());
    }*/

    /*public Observable<String> getTagNames(String artist) {
        return this.artistAPI.getInformation(artist)
                .flatMapIterable(artistInformation -> artistInformation)
                .flatMap(artistInformation -> Observable.fromIterable(artistInformation.getTags()))
                .map(tag -> tag.getName());
    }*/

    /*public Observable<ArtistInformation> getInformation(String artist) {
        return this.artistAPI.getInformation(artist)
                .flatMapIterable(artistInformation -> artistInformation)
                .flatMap(artistInformation -> {
                    //int idArtist = artistInformation.getIdArtist();
                    String name = artistInformation.getName();
                    //Image image = artistInformation.getImage_size_small();
                    List<Tag> tags = artistInformation.getTags();
                    //String summary = artistInformation.getSummary();

                    ArtistInformation artistData = new ArtistInformation(name, tags);
                    return Observable.just(artistData);
                });
    }*/


    public Observable<ArtistInformation> getInformation(String artist) {
        return this.artistAPI.getInformation(artist)
                .flatMapIterable(artistInformation -> artistInformation)
                .flatMap(artistInformation -> {
                    //int idArtist = artistInformation.getIdArtist();
                    String name = artistInformation.getName();
                    //Image image = artistInformation.getImage_size_small();
                    List<Tag> tags = artistInformation.getTags();
                    //String summary = artistInformation.getSummary();

                    // Crear un objeto ArtistData con la información recolectada
                    ArtistInformation artistData = new ArtistInformation(name, tags);

                    // Emitir el objeto ArtistData
                    return Observable.just(artistData);
                });
    }

}
