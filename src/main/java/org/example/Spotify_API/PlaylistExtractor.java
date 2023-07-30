package org.example.Spotify_API;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Spotify_API.Models.PlaylistParser;
import org.example.Spotify_API.Models.MetaData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlaylistExtractor {

    public List<MetaData> SongsList(String accessToken, String playListId) throws IOException {

        String PlaylistTracks = getPlaylistTracks(accessToken, playListId);

        PlaylistParser playlist = extractPlaylistInfo(PlaylistTracks);

        List<MetaData> metaDataList = new ArrayList<>();

        assert playlist != null;
        int trackCount = playlist.getTracks().getTotal();

        int limitCount = playlist.getTracks().getLimit();

        if (trackCount <= limitCount) {
            addMetaData(accessToken, playlist, metaDataList, trackCount);
        } else {
            addMetaData(accessToken, playlist, metaDataList, limitCount);

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

                    try {
                        String trackId = item.getItems().get(i).getTrack().getId();

                        String trackInfo = new TrackExtractor().getTrackInfo(accessToken, trackId);

                        MetaData data = new TrackExtractor().setMetaData(Objects.requireNonNull(new TrackExtractor().extractTrackInfo(trackInfo)));

                        metaDataList.add(data);
                    }
                    catch (NullPointerException e){
                        metaDataList.add(new MetaData("none","none"));
                    }





                }


                trackCount = trackCount - limitCount;


            }


        }
        return metaDataList;

    }

    private void addMetaData(String accessToken, PlaylistParser playlist, List<MetaData> metaDataList, int trackCount) {
        for (int i = 0; i < trackCount; i++) {
            try {
                String trackId = playlist.getTracks().getItems().get(i).getTrack().getId();


                String trackInfo = new TrackExtractor().getTrackInfo(accessToken, trackId);

                MetaData data =new TrackExtractor().setMetaData(Objects.requireNonNull(new TrackExtractor().extractTrackInfo(trackInfo)));

                metaDataList.add(data);
            }
            catch (NullPointerException e){
                metaDataList.add(new MetaData("none","none"));
            }


        }
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

        return new TrackExtractor().responseBody(accessToken, apiUrl);
    }

    private String getNextPlaylistTrack(String accessToken, String url) {

        return new TrackExtractor().responseBody(accessToken, url);
    }

}