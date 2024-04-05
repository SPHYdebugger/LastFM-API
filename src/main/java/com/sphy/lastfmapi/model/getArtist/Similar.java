package com.sphy.lastfmapi.model.getArtist;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Similar {

    List<Artist> artist;

}
