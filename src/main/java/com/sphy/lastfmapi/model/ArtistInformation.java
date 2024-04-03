package com.sphy.lastfmapi.model;


import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class ArtistInformation {


    String name;
    String mbid;
    String url;
    Image image_size_small;
    Image image_size_medium;
    Image image_size_large;
    int streamable;
    Stats stats;
    Similar similar;
    List<Tag> tags;
    Bio bio;


    public ArtistInformation(String name, List<Tag> tags) {
        this.name = name;
        this.tags = tags;
    }
}
