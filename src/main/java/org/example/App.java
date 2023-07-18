package org.example;

import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.downloader.YoutubeProgressCallback;
import com.github.kiulian.downloader.downloader.request.RequestVideoFileDownload;
import com.github.kiulian.downloader.downloader.request.RequestVideoInfo;
import com.github.kiulian.downloader.downloader.response.Response;
import com.github.kiulian.downloader.model.videos.VideoInfo;
import com.github.kiulian.downloader.model.videos.formats.AudioFormat;
import com.github.kiulian.downloader.model.videos.formats.Format;
import com.github.kiulian.downloader.model.videos.formats.VideoFormat;
import com.github.kiulian.downloader.model.videos.formats.VideoWithAudioFormat;

import java.io.File;
import java.util.List;

public class App {
    public static void main(String[] args) {
        YoutubeDownloader downloader = new YoutubeDownloader();


        String videoId = "1F3hm6MfR1k";

        // sync parsing
        RequestVideoInfo request = new RequestVideoInfo(videoId);
        Response<VideoInfo> response = downloader.getVideoInfo(request);

        VideoInfo video = response.data();






        List<VideoWithAudioFormat> videoWithAudioFormats = video.videoWithAudioFormats();



        List<VideoFormat> videoFormats = video.videoFormats();








        File outputDir = new File("my_videos");
        Format format = videoFormats.get(2);


        RequestVideoFileDownload request2 = new RequestVideoFileDownload(format)
                .callback(new YoutubeProgressCallback<File>() {
                    @Override
                    public void onDownloading(int progress) {

                        System.out.printf("Downloaded %d%%\n", progress);
                    }

                    @Override
                    public void onFinished(File videoInfo) {

                        System.out.println("Finished file: " + videoInfo);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("Error: " + throwable.getLocalizedMessage());
                    }
                })
                .saveTo(outputDir)
                .renameTo("video")
                .overwriteIfExists(false)
                .async();
        Response<File> response2 = downloader.downloadVideoFile(request2);
        File data = response2.data(); // will block current thread



        List<AudioFormat> audioFormats = video.audioFormats();

        File audioOutputDir = new File("my_audios");
        Format Aformat = audioFormats.get(1);


        RequestVideoFileDownload request3 = new RequestVideoFileDownload(Aformat)
                .callback(new YoutubeProgressCallback<File>() {
                    @Override
                    public void onDownloading(int progress) {
                        System.out.printf("Downloaded %d%%\n", progress);
                    }

                    @Override
                    public void onFinished(File videoInfo) {

                        System.out.println("Finished file: " + videoInfo);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("Error: " + throwable.getLocalizedMessage());
                    }
                })
                .saveTo(audioOutputDir)
                .renameTo("audio")
                .overwriteIfExists(false)
                .async();
        Response<File> response3 = downloader.downloadVideoFile(request3);
        File data1 = response3.data();


    }
}
