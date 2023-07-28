package org.example.YT_API;

import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.downloader.request.RequestSearchResult;
import com.github.kiulian.downloader.model.search.SearchResult;
import com.github.kiulian.downloader.model.search.SearchResultItem;
import com.github.kiulian.downloader.model.search.SearchResultVideoDetails;
import com.github.kiulian.downloader.model.search.field.*;

import java.util.List;

public class YoutubeSearch {

    private String name;

    private String artist;



    YoutubeDownloader downloader = new YoutubeDownloader();
    public  YoutubeSearch(String name,String artist) {



        this.name = name;

        this.artist = artist;

    }

    public String getId(){



        RequestSearchResult request = new RequestSearchResult(name+" "+artist)
                .type(TypeField.VIDEO);

        SearchResult result = downloader.search(request).data();



        List<SearchResultItem> items = result.items();
        if(items.size()==0){
            return " ";
        }
        SearchResultItem item = items.get(0);
        switch (item.type()) {
            case VIDEO:

                return item.asVideo().videoId();


            case SHELF:
                for (SearchResultVideoDetails video : item.asShelf().videos()) {

                    return video.videoId();
                }
                break;

        }
        return "none";
    }


}
