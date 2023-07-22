package org.example;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
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


import static org.example.YT_API.YouTubeApiSearch.getService;
import static org.example.YT_API.YouTubeApiSearch.DEVELOPER_KEY;

public class App {
    public static void main(String[] args) throws GeneralSecurityException, IOException, GoogleJsonResponseException, CannotWriteException, CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException {
        YouTube youtubeService = getService();
        // Define and execute the API request
        YouTube.Search.List request = youtubeService.search()
                .list("snippet");
        SearchListResponse response = request.setKey(DEVELOPER_KEY)
                .setQ("Onnume Aagala")
                .setType("Song|Audio")
                .execute();

        List<SearchResult> items = response.getItems();
        List<String> videoIdList = new ArrayList<>();
        for (SearchResult item : items) {
            videoIdList.add(item.getId().getVideoId());
        }
        new Downloader(videoIdList.get(0), new File("C:\\Users\\smoha\\Desktop"),"","").downloadAudio();

    }
}