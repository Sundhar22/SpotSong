package org.example;


import org.example.Spotify_API.Models.MetaData;
import org.example.Spotify_API.PlaylistExtractor;
import org.example.Spotify_API.SpotifyApiClient;

import org.example.YT.YoutubeSearch;
import org.example.YT_downloader.Downloader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class App {
    public static void main(String[] args) throws IOException, CannotWriteException, CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException {

        String accessToken = new SpotifyApiClient().getAccessToken();
        List<MetaData> songs = new PlaylistExtractor().SongsList(accessToken, "37i9dQZF1DWYfvJNWU1bKi");


        songs.forEach(song -> {
            YoutubeSearch searcher = new YoutubeSearch(song);
            searcher.setDownloadId();
        });

        for (MetaData song : songs) {
            if (song.getDownloadId().isEmpty()) {
                continue;
            }
            new Downloader(song, new File("D:\\Songs")).downloadAudio();
        }


    }
}