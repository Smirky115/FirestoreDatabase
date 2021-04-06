package com.smirky.firestoredatabase.model;

import com.google.firebase.firestore.DocumentId;

public class VideoDocument {

    @DocumentId
    private  String videoId;

    private  String title;
    private  String urlThumbnail;
    private  String channelTitle;
    private  String viewCount;

    public VideoDocument() {
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public String toString() {
        return "VideoDocument{" +
                "videoId='" + videoId + '\'' +
                ", title='" + title + '\'' +
                ", urlThumbnail='" + urlThumbnail + '\'' +
                ", channelTitle='" + channelTitle + '\'' +
                ", viewCount='" + viewCount + '\'' +
                '}';
    }
}
