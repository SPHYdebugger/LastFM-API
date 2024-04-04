package com.sphy.lastfmapi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

@Data
@NoArgsConstructor
@AllArgsConstructor


public class ArtistInformation {


    String name;
    String mbid;
    String url;
    String image_size_small;
    String image_size_medium;
    String image_size_large;
    int streamable;
    Stats stats;
    Similar similar;
    Tags tags;
    Bio bio;


    public ArtistInformation(String name, Tags tags) {
        this.name = name;
        this.tags = tags;
    }
}
