package org.example.Spotify_API.Downloader;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.StandardArtwork;
import org.jaudiotagger.tag.mp4.Mp4Tag;

import java.io.File;
public class SongDetailsAdder {
    private final File audioFile ;

    public SongDetailsAdder(File audioFile, File imageFile) {
        this.audioFile = audioFile;
        this.imageFile = imageFile;
    }

  private final   File imageFile ;
    public void add()  {
        try {
//            String audioFilePath = "path/to/your/audio.m4a";
//            String imageFilePath = "path/to/your/image.jpg";
//
//            File audioFile = new File(audioFilePath);
//            File imageFile = new File(imageFilePath);

            // Load the audio file
            AudioFile audio = AudioFileIO.read(audioFile);

            // Create an artwork object with the image file
            Artwork artwork = new StandardArtwork();
            artwork.setFromFile(imageFile);

            // Get the MP4 tag from the audio file
            Mp4Tag mp4Tag = (Mp4Tag) audio.getTag();

            // Set the artwork to the MP4 tag
            mp4Tag.setField(artwork);

            // Save the changes to the audio file
            audio.commit();

            System.out.println("Image added to the m4a audio file successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

