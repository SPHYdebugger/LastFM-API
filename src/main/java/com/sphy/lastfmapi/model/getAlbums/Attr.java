package com.sphy.lastfmapi.model.getAlbums;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attr {

    private String artist;
    private String page;
    private String perPage;
    private String totalPages;
    private String total;
}
