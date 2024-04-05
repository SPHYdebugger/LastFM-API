package com.sphy.lastfmapi.model.getAlbums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    private String name;
    private String mbid;
    private String url;
}
