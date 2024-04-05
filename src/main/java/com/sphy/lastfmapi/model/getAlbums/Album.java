package com.sphy.lastfmapi.model.getAlbums;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    private String name;
    private int playcount;
    private String mbid;
    private String url;
    private Artist artist;
    private List<Image> image;
}
