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
import java.util.List;

public class YT_Downloader_Main {

    private final YoutubeDownloader downloader = new YoutubeDownloader();
    public String Id;

    public YT_Downloader_Main(String id, File audioDic) {
        this.Id = id;
        this.audioDic = audioDic;
        RequestVideoInfo request = new RequestVideoInfo(Id);
        Response<VideoInfo> response = downloader.getVideoInfo(request);
        this. video = response.data();
        List<AudioFormat> audioFormats = video.audioFormats();
        this.Aformat = audioFormats.get(1);
    }
    public File audioDic =new File("Audios");


    private final VideoInfo video ;

    public YT_Downloader_Main(String videoId) {
        this.Id = videoId;
        this.audioDic = new File("Audios");
        RequestVideoInfo request = new RequestVideoInfo(Id);
        Response<VideoInfo> response = downloader.getVideoInfo(request);
        this. video = response.data();
        List<AudioFormat> audioFormats = video.audioFormats();
        this.Aformat = audioFormats.get(1);
    }


    Format Aformat ;


   public void downloadAudio(){
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
   }
}
