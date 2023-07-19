package org.example;

import org.example.YT_downloader.YT_Downloader_Main;

import java.io.File;

public class App {
    public static void main(String[] args) {
        var audio = new YT_Downloader_Main("3sDLMktfqFM", new File("C:\\Users\\smoha\\Desktop"));
        audio.downloadAudio();
    }
}