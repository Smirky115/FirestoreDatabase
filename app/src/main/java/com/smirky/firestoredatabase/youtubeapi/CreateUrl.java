package com.smirky.firestoredatabase.youtubeapi;

import android.util.Log;

public class CreateUrl {
    private static final String TAG = "Util";
    public static final String YOUTUBE_URL =" https://www.googleapis.com/youtube/v3/videos";
    public static final String YOUTUBE_KEY = "AIzaSyDVYeZ2Oi-B0qIumRqUbF9ZBGZSMBgISWw";
    public static final String YOUTUBE_PART = "snippet,statistics";
    public static final String YOUTUBE_FIELDS = "(snippet(channelTitle,title,thumbnails/default/url),statistics(viewCount))";

    public static String createUrl(String id) {
        Log.d(TAG, "createUri starts");

        return String.format("%1$s?key=%2$s&id=%3$s&part=%4$s&fields=items%5$s",
                YOUTUBE_URL,
                YOUTUBE_KEY,
                id,
                YOUTUBE_PART,
                YOUTUBE_FIELDS);


    }
}


//https://www.googleapis.com/youtube/v3/videos?key=AIzaSyDVYeZ2Oi-B0qIumRqUbF9ZBGZSMBgISWw&id=mPCDQ34S8Rs&part=snippet,statistics&fields=items(snippet(channelId,title,thumbnails/default/url),statistics(viewCount))

//Uri builder doesnt work with (), characters