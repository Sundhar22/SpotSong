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
import org.example.Spotify_API.Models.PlaylistParser;
import org.example.Spotify_API.Models.metaData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlaylistExtractor {

    public List<metaData> SongsList(String accessToken, String playListId) throws IOException {

        String PlaylistTracks = getPlaylistTracks(accessToken, playListId);

        PlaylistParser playlist = extractPlaylistInfo(PlaylistTracks);

        List<metaData> metaDataList = new ArrayList<>();

        assert playlist != null;
        int trackCount = playlist.getTracks().getTotal();

        int limitCount = playlist.getTracks().getLimit();

        if (trackCount <= limitCount) {
            for (int i = 0; i < trackCount; i++) {
                String trackId = playlist.getTracks().getItems().get(i).getTrack().getId();


                String trackInfo = new TrackExtractor().getTrackInfo(accessToken, trackId);

                metaData data =new TrackExtractor().getMetaData(Objects.requireNonNull(new TrackExtractor().extractTrackInfo(trackInfo)));

                metaDataList.add(data);


            }
        } else {
            for (int i = 0; i < limitCount; i++) {
                String trackId = playlist.getTracks().getItems().get(i).getTrack().getId();


                String trackInfo = new TrackExtractor().getTrackInfo(accessToken, trackId);

                metaData data = new TrackExtractor().getMetaData(Objects.requireNonNull(new TrackExtractor().extractTrackInfo(trackInfo)));

                metaDataList.add(data);


            }

            String nextURL = playlist.getTracks().getNext();

            trackCount = trackCount - limitCount;


            while (trackCount > 0) {


                String nextPlaylistTracks = getNextPlaylistTrack(accessToken, nextURL);

                PlaylistParser.tracks item = extractNextPlaylist(nextPlaylistTracks);

                int newCount;

                newCount = trackCount;

                if (trackCount > limitCount) {
                    newCount = limitCount;
                    assert item != null;
                    nextURL = item.getNext();

                }


                for (int i = 0; i < newCount; i++) {
                    assert item != null;
                    String trackId = item.getItems().get(i).getTrack().getId();


                    String trackInfo = new TrackExtractor().getTrackInfo(accessToken, trackId);

                    metaData data = new TrackExtractor().getMetaData(Objects.requireNonNull(new TrackExtractor().extractTrackInfo(trackInfo)));

                    metaDataList.add(data);


                }


                trackCount = trackCount - limitCount;


            }


        }
        return metaDataList;

    }

    private PlaylistParser extractPlaylistInfo(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(responseBody, PlaylistParser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private PlaylistParser.tracks extractNextPlaylist(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(responseBody, PlaylistParser.tracks.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getPlaylistTracks(String accessToken, String playListId) {
        String apiUrl = "https://api.spotify.com/v1/playlists/" + playListId;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(apiUrl);

            // Set the Authorization header with the access token
            httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

            HttpResponse response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getNextPlaylistTrack(String accessToken, String url) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);

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
