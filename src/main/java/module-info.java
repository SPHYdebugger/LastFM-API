module com.sphy.lastfmapi {

    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires io.reactivex.rxjava3;
    requires retrofit2;
    requires gson;
    requires okhttp3;
    requires logging.interceptor;
    requires retrofit2.adapter.rxjava2;
    requires retrofit2.converter.gson;
    requires rxjava;
    requires com.fasterxml.jackson.databind;
    requires retrofit2.converter.jackson;

    opens com.sphy.lastfmapi.controller;
    opens com.sphy.lastfmapi to javafx.fxml;
    opens com.sphy.lastfmapi.model.getArtist;
    opens com.sphy.lastfmapi.model.getAlbums;




    exports com.sphy.lastfmapi;



}