package com.smirky.firestoredatabase.model;

import com.google.firebase.firestore.DocumentId;

public class PlaylistDocument {
    @DocumentId
    private String playlistId;

    private String title;

    private String[] videoIdsArray;

    public PlaylistDocument() {
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getVideoIdsArray() {
        return videoIdsArray;
    }

    public void setVideoIdsArray(String[] videoIdsArray) {
        this.videoIdsArray = videoIdsArray;
    }

//    @Override
//    public String toString() {
//        return "PlaylistDocument{" +
//                "playlistId='" + playlistId + '\'' +
//                ", title='" + title + '\'' +
//                ", videoIdsArray Size =" + videoIdsArray.length +
//                '}';
//    }
}
