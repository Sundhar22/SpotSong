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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlaylistExtractor {
    public static void main(String args[]) throws IOException {

        String accessToken = "BQCx-M3RbVsVecufPs0IH9pez3_wOj2cE0Vcy-WmHk8Wh2IduLo_lSI2Rn9lKtI7751jQfmNs78goI1maNOEfXo2RViQlixj4LdGUryIynqdZIoL0tk";

        String PlaylistTracks = getPlaylistTracks(accessToken, "07WB4eklst5yw4hHYLMDgD");

        PlaylistParser playlist = extractPlaylistInfo(PlaylistTracks);

        List<metaData> metaDataList = new ArrayList<>();

        int trackcount = playlist.getTracks().getTotal();

        int limitcount = playlist.getTracks().getLimit();

        if (trackcount <= limitcount) {
            for (int i = 0; i < trackcount; i++) {

                String trackId = playlist.getTracks().getItems().get(i).getTrack().getId();



                TrackExtractor.getTrackInfo(accessToken, trackId);

                String trackinfo = TrackExtractor.getTrackInfo(accessToken, trackId);

                metaData data =  TrackExtractor.display(TrackExtractor.extractTrackInfo(trackinfo));

                metaDataList.add(data);



            }
        } else {
            for (int i = 0; i < limitcount; i++) {
                String trackId = playlist.getTracks().getItems().get(i).getTrack().getId();



                TrackExtractor.getTrackInfo(accessToken, trackId);

                String trackinfo = TrackExtractor.getTrackInfo(accessToken, trackId);

                metaData data =  TrackExtractor.display(Objects.requireNonNull(TrackExtractor.extractTrackInfo(trackinfo)));

                metaDataList.add(data);


            }

            String nextURL = playlist.getTracks().getNext().toString();

            trackcount = trackcount - limitcount;


            while (trackcount > 0) {


                String nextPlaylistTracks = getNextPlaylistTrack(accessToken, nextURL);

                PlaylistParser.tracks item = extractNextPlaylist(nextPlaylistTracks);

                int newcount;

                newcount = trackcount;

                if (trackcount > limitcount) {
                    newcount = limitcount;
                    nextURL = item.getNext();

                }


                for (int i = 0; i < newcount; i++) {
                    String trackId = item.getItems().get(i).getTrack().getId();



                    TrackExtractor.getTrackInfo(accessToken, trackId);

                    String trackinfo = TrackExtractor.getTrackInfo(accessToken, trackId);

                    metaData data =  TrackExtractor.display(Objects.requireNonNull(TrackExtractor.extractTrackInfo(trackinfo)));

                    metaDataList.add(data);



                }


                trackcount = trackcount - limitcount;


            }


        }
//        System.out.println(metaDataList.size());
        //metaDataList contains metadata

    }

    private static PlaylistParser extractPlaylistInfo(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            PlaylistParser tokenResponse = objectMapper.readValue(responseBody, PlaylistParser.class);
            return tokenResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static PlaylistParser.tracks extractNextPlaylist(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            PlaylistParser.tracks tokenResponse = objectMapper.readValue(responseBody, PlaylistParser.tracks.class);
            return tokenResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getPlaylistTracks(String accessToken, String playListId) {
        String apiUrl = "https://api.spotify.com/v1/playlists/" + playListId;

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

    public static String getNextPlaylistTrack(String accessToken, String url) {
        String apiUrl = url;

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

