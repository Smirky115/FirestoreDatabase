package com.smirky.firestoredatabase.youtubeapi;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.smirky.firestoredatabase.model.VideoDocument;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class YoutubeApi {
    private static final String TAG = "YoutubeApi";

    public interface OnDataAvailable {
        void OnDataAvailable(List<VideoDocument> videosList);
    }
    public static void getVideos(final OnDataAvailable callback , String[] ids){
        Log.d(TAG, "getVideos: called");
        Log.d(TAG, "getVideos: ids received = " + ids.toString());
        List<VideoDocument> videosList = new ArrayList<>();
        StringBuilder idWithCommas = new StringBuilder(100);

        if(ids.length == 0){
            Log.d(TAG, "getVideos: empty list");
        }
        if (ids.length == 1){
            Log.d(TAG, "getVideos: only one id, no need to add commmas");
            idWithCommas.append(ids[0]);
            }
        else {
            for (int i = 0; i < (ids.length-1) ; i++) {
                idWithCommas.append(ids[i] + ",");
            }
            idWithCommas.append(ids[ids.length-1]);
        }


//      String url = CreateUrl.createUrl("mPCDQ34S8Rs,xLpZZScqgKo,Ho4RK6z2CIM,HQILxqS831E,zHD6kxU45BY,dtaJzUbQS7E");
        String url = CreateUrl.createUrl(idWithCommas.toString());
        Log.d(TAG, "getVideoList: url" + url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            Log.d(TAG, "getVideoList: response received");
            try {
                JSONArray jsonArray = response.getJSONArray("items");
                for (int i = 0; i < jsonArray.length(); i++) {
                    VideoDocument video = new VideoDocument();

                    video.setVideoId(ids[i]);

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    JSONObject snippet = jsonObject.getJSONObject("snippet");
                    video.setChannelTitle(snippet.getString("channelTitle"));
                    video.setTitle(snippet.getString("title"));
                    video.setUrlThumbnail(snippet.getJSONObject("thumbnails").getJSONObject("default").getString("url"));

                    JSONObject statistics = jsonObject.getJSONObject("statistics");
                    video.setViewCount(statistics.getString("viewCount"));

                    videosList.add(video);
                }
                if (null != callback) {
                    callback.OnDataAvailable(videosList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            error.printStackTrace();
        });
        AppControllerVolley.getInstance().addToRequestQueue(jsonObjectRequest);
    }

}



