module com.sphy.lastfmapi {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sphy.lastfmapi.controller;
    opens com.sphy.lastfmapi to javafx.fxml;
    exports com.sphy.lastfmapi;
}