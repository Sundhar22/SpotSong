package org.example.Spotify_API.Models;

import org.example.Spotify_API.Downloader.ImageDownloader;

public class MetaData {
    private  String songName;

    private  String artistName;

    private ImageDownloader imageUrl;

    public MetaData(String songName, String artistName, ImageDownloader imageUrl) {
        this.songName = songName;
        this.artistName = artistName;
        this.imageUrl = imageUrl;
        
    }

    public ImageDownloader getImageUrl() {
        return imageUrl;
    }

    public void setDownloadId(String downloadId) {
        this.downloadId = downloadId;
    }

    public MetaData(String songName, String artistName) {
        this.songName = songName;
        this.artistName = artistName;
    }

    public String getDownloadId() {
        return downloadId;
    }

    private  String downloadId;


    public String getSongName() {
        return songName;
    }



    public String getArtistName() {
        return artistName;
    }

}
