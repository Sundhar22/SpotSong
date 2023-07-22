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

import java.io.IOException;
import java.util.List;

public class TrackExtractor {
    public static void main(String[] args) throws IOException {
        String accessToken = "BQB3-ZGMyvr3bWT_INbqybkq1agUjyjp_1sqI33D5jmf7f3BPaCLuI-q5mO6UcL_u4k7fUs3za2J9fD7FMsFDMTDwY_Vg-Lh6xo_MgWC4yfk557yajE";// Replace with the actual access token
        String trackInfo = getTrackInfo(accessToken, "1W0QTc0qGWVhMOzxgoBgAQ");


        TrackParse track = extractTrackInfo(trackInfo);


        System.out.println("Name : " + track.getName());

        System.out.println("Album :" + track.getAlbum().getName());


        System.out.println("Album Composer : " + track.getAlbum().getArtists().get(0).getName());

        List<TrackParse.Artist> artists = track.getArtists();

        int artistsLen = artists.size();

        System.out.println("Artist : ");

        for (int i = 0; i < artistsLen; i++) {

            System.out.println(track.getArtists().get(i).getName());
        }

        List<TrackParse.Image> images = track.getAlbum().getImages();


        ImageDownloader image = new ImageDownloader(images.get(0).getUrl());

        image.downloadImage();
    }

    private static TrackParse extractTrackInfo(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            TrackParse tokenResponse = objectMapper.readValue(responseBody, TrackParse.class);
            return tokenResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String getTrackInfo(String accessToken, String trackId) {
        String apiUrl = "https://api.spotify.com/v1/tracks/" + trackId;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(apiUrl);

            // Set the Authorization header with the access token
            httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

            HttpResponse response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String responseBody = EntityUtils.toString(entity);
                //System.out.println(responseBody);
                return responseBody;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
