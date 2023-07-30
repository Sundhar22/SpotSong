package org.example.Spotify_API.Downloader;


import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;

import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.StandardArtwork;

import java.io.File;
import java.io.IOException;

public class SongDetailsAdder {
    private final File audioFile;


    public SongDetailsAdder(File audioFile, File imageFile, String artist, String album) {
        this.audioFile = audioFile;
        this.artist = artist;
        this.album = album;
        this.imageFile = imageFile;
    }

    private final String artist;
    private final String album;
    private final File imageFile;

    public void add() throws  IOException, CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, CannotWriteException, TagException {
//        AudioFile audio = AudioFileIO.read(audioFile);
//
//        // Create an artwork object with the image file
//        Artwork artwork = new StandardArtwork();
//        artwork.setFromFile(imageFile);
//        File m4aFile = new File("path/to/your/file.m4a");

        AudioFile audioFile = AudioFileIO.read(this.audioFile);
        Tag tag = audioFile.getTagOrCreateAndSetDefault();
        tag.setField(FieldKey.ARTIST, this.artist);
        tag.setField(FieldKey.ALBUM, this.album);

        Artwork artwork = new StandardArtwork();
        artwork.setFromFile(this.imageFile);
        tag.setField(artwork);
        try {
            audioFile.commit();
        }catch (Exception ignored){

        }

    }


}


