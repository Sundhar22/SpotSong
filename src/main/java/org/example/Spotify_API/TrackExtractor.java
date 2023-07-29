package org.example.Spotify_API;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.Spotify_API.Models.TrackParse;
import org.example.Spotify_API.Models.metaData;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class TrackExtractor {

    public  TrackParse extractTrackInfo(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return objectMapper.readValue(responseBody, TrackParse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public  metaData getMetaData(TrackParse track) {



        metaData data = new metaData();
       AtomicReference<String> artists= new AtomicReference<>(" ");
       track.getAlbum().getArtists().forEach(artist -> {
           artists.set(artists+" "+artist.getName());
       });
        data.setArtistName(artists.get());
        data.setSongName(track.getName());


        return data;
//        ImageDownloader image = new ImageDownloader(images.get(0).getUrl());

//        image.downloadImage();
    }



    public  String getTrackInfo(String accessToken, String trackId) {
        String apiUrl = "https://api.spotify.com/v1/tracks/" + trackId;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(apiUrl);

            // Set the Authorization header with the access token
            httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

            HttpResponse response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();

            if (entity != null) {

                //System.out.println(responseBody);
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
