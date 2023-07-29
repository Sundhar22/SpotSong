package org.example;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import org.example.Spotify_API.Models.YouTubeApiKey;
import org.example.Spotify_API.Models.metaData;
import org.example.Spotify_API.PlaylistExtractor;
import org.example.Spotify_API.SpotifyApiClient;
import org.example.YT_API.YouTubeApiSearch;
import org.example.YT_downloader.Downloader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;


public class App {
    public static void main(String[] args) throws GeneralSecurityException, IOException, GoogleJsonResponseException, CannotWriteException, CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException {

        String accessToken = new SpotifyApiClient().getAccessToken();
        System.out.println("Pls wait getting song data");
        List<metaData> songs = new PlaylistExtractor().SongsList(accessToken, "37i9dQZF1DWYfvJNWU1bKi");
        YouTube youtubeService = new YouTubeApiSearch().getService();
        YouTube.Search.List request = youtubeService.search()
                .list("snippet");
        List<String> videoIdList = new ArrayList<>();
        System.out.println();
        System.out.println("SuccessFully collected your Songs");
        System.out.println();

        songs.forEach(song -> {
            try {
                SearchListResponse response = request.setKey(new YouTubeApiKey().getDEVELOPER_KEY())
                        .setQ(song.getSongName() + " Artist - " + song.getArtistName())
                        .setType("video")
                        .execute();
                List<SearchResult> items = response.getItems();
                videoIdList.add(items.get(0).getId().getVideoId());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println();
        System.out.println("Lets Download");
        System.out.println();
        for (String s : videoIdList) {
            new Downloader(s, new File("D:\\Songs"), "", "").downloadAudio();

        }
        System.out.println();
        System.out.println("SuccessFully downloaded your Songs");
        System.out.println();

    }
}