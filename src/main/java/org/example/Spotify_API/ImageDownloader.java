package org.example.Spotify_API;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ImageDownloader {

    private final String imageUrl;

    private final String destinationFile;

    public ImageDownloader(String imageUrl,String destinationFile){
        this.imageUrl = imageUrl;

        this.destinationFile = destinationFile;

    }

    public ImageDownloader(String imageUrl){
        this.imageUrl = imageUrl;
        this.destinationFile = "D:\\images.jpg";
    }


    public  void downloadImage() throws IOException {
        URL url = new URL(imageUrl);
        URLConnection connection = url.openConnection();
        try (InputStream inputStream = new BufferedInputStream(connection.getInputStream());
             FileOutputStream outputStream = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}

