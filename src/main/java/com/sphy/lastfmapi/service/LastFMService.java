package com.sphy.lastfmapi.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sphy.lastfmapi.model.Tag;
import com.sphy.lastfmapi.util.Constants;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class LastFMService {

    private ArtistAPI artistAPI;

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


    public Observable<Tag> getTags(String artistName) {

        // impresión para depurar
        System.out.println("getTags iniciado");
        System.out.println("Artista buscado: "+ artistName);

       /* return this.artistAPI.getInformation(artist, Constants.API_KEY, "json")
                .map(ArtistInformation::getTags)
                .flatMapIterable(Tags::getTag);*/

        return this.artistAPI.getInformation(artistName, Constants.API_KEY, "json")
                .map(information -> information.getArtist())
                .map(artist -> artist.getTags())
                .map(tags -> tags.getTag())
                .flatMapIterable(tag -> tag);
    }

    /*public Observable<Tag> getTags(String artist) {
        return this.artistAPI.getInformation(artist, Constants.API_KEY, "json")
                .map(ArtistInformation::getTags)
                .flatMap(tagsWrapper -> {
                    List<Tag> tagsList = tagsWrapper != null ? tagsWrapper.getTag() : new ArrayList<>();
                    if (!tagsList.isEmpty()) {
                        return Observable.just(tagsList.get(0));
                    } else {
                        return Observable.empty();
                    }
                });
    }*/

}
