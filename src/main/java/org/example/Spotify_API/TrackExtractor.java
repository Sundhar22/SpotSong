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
import org.example.Spotify_API.Downloader.ImageDownloader;
import org.example.Spotify_API.Models.MetaData;
import org.example.Spotify_API.Models.TrackParse;

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

    public MetaData setMetaData(TrackParse track) {


        AtomicReference<String> artists= new AtomicReference<>("");


       track.getAlbum().getArtists().forEach(artist -> {
           artists.set(artists.get().isEmpty()?artist.getName():artists+","+artist.getName());
       });

        return new MetaData(track.getName(),artists.get(),new ImageDownloader(track.getAlbum().getImages().get(0).getUrl()),track.getAlbum().getName());

    }



    public  String getTrackInfo(String accessToken, String trackId) {
        String apiUrl = "https://api.spotify.com/v1/tracks/" + trackId;

        return responseBody(accessToken, apiUrl);
    }

     String responseBody(String accessToken, String apiUrl) {
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
