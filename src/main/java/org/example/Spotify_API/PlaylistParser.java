package org.example.Spotify_API;

import java.util.List;

public class PlaylistParser {
    private tracks tracks;

    public PlaylistParser.tracks getTracks() {
        return tracks;
    }

    public void setTracks(PlaylistParser.tracks tracks) {
        this.tracks = tracks;
    }

    public static class Item {

        private Track track;


        public Track getTrack() {
            return track;
        }

        public void setTrack(Track track) {
            this.track = track;
        }

        // Getters and Setters (omitted for brevity)
    }

    public static class Track {

        private String id;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


        // Getters and Setters (omitted for brevity)
    }

    public static class tracks {

        private String href;
        private int limit;
        private String next;
        private int offset;
        private String previous;
        private int total;
        private List<Item> items;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }
    }

    // Getters and Setters (omitted for brevity)
}
