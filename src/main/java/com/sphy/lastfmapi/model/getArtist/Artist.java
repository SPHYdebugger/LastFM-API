package com.sphy.lastfmapi.model.getArtist;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Artist {


    String name;
    String mbid;
    String url;
    List<Image> image;
    int streamable;
    Stats stats;
    Similar similar;
    Tags tags;
    Bio bio;



}
