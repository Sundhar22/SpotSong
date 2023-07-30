package org.example.YT_downloader;

import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.downloader.YoutubeProgressCallback;
import com.github.kiulian.downloader.downloader.request.RequestVideoFileDownload;
import com.github.kiulian.downloader.downloader.request.RequestVideoInfo;
import com.github.kiulian.downloader.downloader.response.Response;
import com.github.kiulian.downloader.model.videos.VideoInfo;
import com.github.kiulian.downloader.model.videos.formats.AudioFormat;
import com.github.kiulian.downloader.model.videos.formats.Format;

import java.io.File;
import java.io.IOException;
import java.util.List;



public class Downloader {

    private final YoutubeDownloader downloader = new YoutubeDownloader();

    public String Id;
    public String Artist;
    public String Album;

    public File audioDic = new File("Audios");

    Format Aformat ;
    private final VideoInfo video ;

    public Downloader(String id, File audioDic,String artist,String album) {
        this.Id = id;
        this.audioDic = audioDic;
        this.Album=album;
        this.Artist=artist;
        RequestVideoInfo request = new RequestVideoInfo(Id);
        Response<VideoInfo> response = downloader.getVideoInfo(request);
        this. video = response.data();
        List<AudioFormat> audioFormats = video.audioFormats();
        this.Aformat = audioFormats.get(1);
    }
    public Downloader(String videoId,String artist,String album) {
        this.Id = videoId;this.Album=album;
        this.Artist=artist;
        this.audioDic = new File("Audios");
        RequestVideoInfo request = new RequestVideoInfo(Id);
        Response<VideoInfo> response = downloader.getVideoInfo(request);
        this. video = response.data();
        List<AudioFormat> audioFormats = video.audioFormats();
        this.Aformat = audioFormats.get(1);
    }


   public void downloadAudio() throws  IOException {
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
               .saveTo(this.audioDic)
               .renameTo(video.details().title())
               .overwriteIfExists(false)
               .async();
       Response<File> response3 = downloader.downloadVideoFile(request3);
       File data = response3.data();
       System.out.println(data);

   }
   public static void main(String[] args) throws IOException {
       new Downloader("RzmZmaQtxls", new File("D:\\Songs"), "", "").downloadAudio();
   }
}
