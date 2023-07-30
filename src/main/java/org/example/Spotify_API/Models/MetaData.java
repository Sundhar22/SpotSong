package org.example.Spotify_API.Models;

import org.example.Spotify_API.Downloader.ImageDownloader;

public class MetaData {
    private final String songName;


    private final String artistName;

    public String getAlbumName() {
        return albumName;
    }

    private  String albumName;

    private ImageDownloader imageUrl;

    public MetaData(String songName, String artistName, ImageDownloader imageUrl,String albumName) {
        this.songName = songName;
        this.artistName = artistName;
        this.imageUrl = imageUrl;this.albumName=albumName;

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
