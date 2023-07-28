package org.example;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import org.example.Spotify_API.Models.metaData;
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


public class App {
    public static void main(String[] args) throws GeneralSecurityException, IOException, GoogleJsonResponseException, CannotWriteException, CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException {

        String accessToken = new SpotifyApiClient().getAccessToken();
        System.out.println("Pls wait getting song data");
        List<metaData> songs = new PlaylistExtractor().SongsList(accessToken, "37i9dQZF1DWYfvJNWU1bKi");
        System.out.println(songs.size());

        List<String> videoIdList = new ArrayList<>();
        System.out.println();
        System.out.println("SuccessFully collected your Songs");
        System.out.println();



        songs.forEach(song -> {

            YoutubeSearch searcher = new YoutubeSearch(song.getSongName(),song.getArtistName());

            if(searcher.getId() != " "){
            videoIdList.add(searcher.getId());
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