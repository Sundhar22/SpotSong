package org.example.Spotify_API;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TrackExtractor {



    public static void main(String[] args) throws IOException {

        //BQB2NB7VJ0P_k-EO8b_ioX7aPmNjyhRK7LFS_Xd5BFPhkT8mRuS63G4O59-G7EVcdtqI_9jtIlf2KP4A5NahOuCo9SuDlbFiqIlqokewS0oihnIMh_Q



        String accessToken = "BQAmm2MIOFEsn3YKyXNxZro_-AciO4fWLh_neq0pxBwRHNGaOGJtHEjOFa0MRTyhNySppcaXDH5Jpj7oKYPVHWKsDTQFiZkjmsaR9c_NBs5cHWBO7ZE";
        // System.out.println(accessToken);
        //BQAUQMYsV77XbXjF0IqH8f05UB44HXgceKFdAPbs35xZV-rdjLNuSnhq2-6OL1rgllmyiEyBPcz8pbJMwYA5HubRlU5BdI5FqFyEGymzmB30fV7UmVk
        String trackInfo = getTrackInfo(accessToken, "1W0QTc0qGWVhMOzxgoBgAQ");
        TrackParse track = extractTrackInfo(trackInfo);
        metaData data = display(track);


        List<metaData> metaDataList  = new ArrayList<>();

        metaDataList.add(data);

    }

    public static TrackParse extractTrackInfo(String responseBody) {
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

    public static metaData display(TrackParse track) throws IOException {

//        System.out.println("Name : " + track.getName());
//
//        System.out.println("Album : " + track.getAlbum().getName());
//
//
//
//
//        System.out.println("Album Composer : " + track.getAlbum().getArtists().get(0).getName());
//
//        List<TrackParse.Artist> artists = track.getArtists();
//
//        int artistsLen = artists.size();
//
//        System.out.println("Artist : ");
//
//        for (int i=0 ; i < artistsLen ; i++){
//
//            System.out.println(track.getArtists().get(i).getName());
//        }
//
//        System.out.println("Release Date : " + track.getAlbum().getRelease_date());
//
//        System.out.println("Duration : " + track.getDuration_ms() + "ms");
//
//        List<TrackParse.Image> images = track.getAlbum().getImages();




        metaData data = new metaData();
        data.setArtistName(track.getAlbum().getArtists().get(0).getName());
        data.setSongName(track.getName());


        return data;
//        ImageDownloader image = new ImageDownloader(images.get(0).getUrl());
//
//        image.downloadImage();
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

