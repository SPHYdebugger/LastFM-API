module com.sphy.lastfmapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires io.reactivex.rxjava2;
    requires retrofit2;
    requires gson;
    requires okhttp3;
    requires logging.interceptor;
    requires retrofit2.adapter.rxjava2;
    requires retrofit2.converter.gson;

    opens com.sphy.lastfmapi.controller;
    opens com.sphy.lastfmapi to javafx.fxml;
    exports com.sphy.lastfmapi;
}