package com.sphy.lastfmapi.model.getArtist;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Artist {


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



}
