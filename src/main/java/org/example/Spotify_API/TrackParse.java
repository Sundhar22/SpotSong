package org.example.Spotify_API;

import java.util.List;

public class TrackParse {
    private Album album;
    private List<Artist> artists;

    private long duration_ms;

    private String id;
    private boolean is_local;
    private String name;
    private int popularity;

    private int track_number;


    // Constructors, getters, and setters for all the fields

    public static class Album {
        private String album_type;
        private List<Artist> artists;
        private List<Image> images;
        private String name;
        private String release_date;
        private String release_date_precision;
        private int total_tracks;
        private String type;
        private String uri;

        public String getAlbum_type() {
            return album_type;
        }

        public void setAlbum_type(String album_type) {
            this.album_type = album_type;
        }

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

        public String getRelease_date_precision() {
            return release_date_precision;
        }

        public void setRelease_date_precision(String release_date_precision) {
            this.release_date_precision = release_date_precision;
        }

        public int getTotal_tracks() {
            return total_tracks;
        }

        public void setTotal_tracks(int total_tracks) {
            this.total_tracks = total_tracks;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        // Constructors, getters, and setters for all the fields
    }

    public static class Artist {

        private String name;
        private String type;
        private String uri;




        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        // Constructors, getters, and setters for all the fields
    }

    public static class ExternalIds {
        private String isrc;

        // Constructors, getters, and setters for the field
    }

    public static class ExternalUrls {
        private String spotify;

        // Constructors, getters, and setters for the field
    }

    public static class Image {
        private int height;
        private String url;
        private int width;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
// Constructors, getters, and setters for the fields
    }

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



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIs_local() {
        return is_local;
    }

    public void setIs_local(boolean is_local) {
        this.is_local = is_local;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }



    public int getTrack_number() {
        return track_number;
    }

    public void setTrack_number(int track_number) {
        this.track_number = track_number;
    }


}

