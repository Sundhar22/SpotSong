package org.example;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import org.example.Spotify_API.Models.MetaData;
import org.example.Spotify_API.PlaylistExtractor;
import org.example.Spotify_API.SpotifyApiClient;

import org.example.YT_API.YoutubeSearch;
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
import java.util.Objects;


public class App {
    public static void main(String[] args) throws GeneralSecurityException, IOException, GoogleJsonResponseException, CannotWriteException, CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException {

        String accessToken = new SpotifyApiClient().getAccessToken();
        List<String> videoIdList = new ArrayList<>();
        List<MetaData> songs = new PlaylistExtractor().SongsList(accessToken, "37i9dQZF1DWYfvJNWU1bKi");




        songs.forEach(song -> {
            YoutubeSearch searcher = new YoutubeSearch(song);
            searcher.setDownloadId();
        });

        for (MetaData song : songs) {
            if (song.getDownloadId().isEmpty()) {
                continue;
            }
            new Downloader( song,new File("D:\\Songs")).downloadAudio();

        }





    }
}