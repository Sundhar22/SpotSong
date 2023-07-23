package org.example.Spotify_API.Models;

import java.util.List;

public class TrackParse {
    private Album album;
    private List<Artist> artists;

    private long duration_ms;



    private String name;



    // Constructors, getters, and setters for all the fields

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public long getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(long duration_ms) {
        this.duration_ms = duration_ms;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }






    public static class Album {

        private List<Artist> artists;
        private List<Image> images;
        private String name;
        private String release_date;



        public List<Artist> getArtists() {
            return artists;
        }

        public void setArtists(List<Artist> artists) {
            this.artists = artists;
        }


        public List<Image> getImages() {
            return images;
        }

        public void setImages(List<Image> images) {
            this.images = images;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }


        // Constructors, getters, and setters for all the fields
    }


    public static class Artist {

        private String name;



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }



        // Constructors, getters, and setters for all the fields
    }






    public static class Image {

        private String url;


        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
// Constructors, getters, and setters for the fields
    }

}

