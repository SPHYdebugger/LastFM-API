package com.sphy.lastfmapi.model.getImage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ArtistImageDownloader {

    static String artistName;

    public ArtistImageDownloader(String artistName) {
        this.artistName = artistName;
    }

    public static String getArtistImage(String artistName) {
        System.out.println("entrando en el método getArtistImage");

        String imageUrl = null;
        String apiUrl = "https://www.theaudiodb.com/api/v1/json/2/search.php?s=" + artistName;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            System.out.println("url de la peticion: " + url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("response. " + response);



            /*// Verifica si la URL es válida antes de devolverla
            if (imageUrl == null || imageUrl.isEmpty()) {

                throw new IllegalArgumentException("URL inválida o recurso no encontrado");

            }else {
                imageUrl = parseArtistImage(response.toString());
                System.out.println("la url de la imagen es: " + imageUrl);
            }*/
        } catch (IllegalArgumentException | IOException e) {
            return "fallo";
        }

        return imageUrl;
    }



    public static String parseArtistImage(String json) {
        String imageUrl = null;

        try {
            // Parsea el JSON utilizando Jackson
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);

            // Extrae la URL de la imagen del primer artista encontrado
            JsonNode artistsNode = rootNode.path("artists");
            if (artistsNode.isArray() && artistsNode.size() > 0) {
                JsonNode firstArtistNode = artistsNode.get(0);
                imageUrl = firstArtistNode.path("strArtistThumb").asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "fallo";
        }
        System.out.println("la url es.. " + imageUrl);
        return imageUrl;
    }


}
