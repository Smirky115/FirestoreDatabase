package com.smirky.firestoredatabase.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.smirky.firestoredatabase.model.PlaylistDocument;
import com.smirky.firestoredatabase.model.VideoDocument;
import com.smirky.firestoredatabase.youtubeapi.YoutubeApi;

import java.util.List;

public class DatabaseManipulation {
    private static final String TAG = "DatabaseManipulation";

    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static CollectionReference videosCollectionRef = db.collection("videos");
    static CollectionReference playlistsCollectionRef = db.collection("playlists");

    //Add playlist

    public static void addPlaylist(String playlistId, String title, String[] videoIdArray){
        Log.d(TAG, "addPlaylist: starts");
        PlaylistDocument playlistDocument = new PlaylistDocument();

        playlistDocument.setPlaylistId(playlistId);
        playlistDocument.setTitle(title);
        playlistDocument.setVideoIdsArray(videoIdArray);

        playlistsCollectionRef.document(playlistId)
                .set(playlistDocument, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written! " + playlistDocument);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

        checkAndAddVideo(videoIdArray);
    }

//  Check for missing videos and add
    public static void checkAndAddVideo(String[] videoIdArray){
        Log.d(TAG, "checkAndAddVideo: called");

//      TODO Can make a new missing id array and pass array to getVideo
        for (String videoId:
                videoIdArray) {
            videosCollectionRef.document(videoId).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Log.d(TAG, "Document already exists ");
                                } else {
                                    Log.d(TAG, "No such document, add new: " + document);
                                    addVideos(new String[]{videoId});
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });
        }
    }

//  Add videos
    public static void addVideos(String[] videoIds) {
        Log.d(TAG, "addVideos: starts");
        Log.d(TAG, "addVideos: video id list " + videoIds.toString());

        YoutubeApi.getVideos(new YoutubeApi.OnDataAvailable() {
            @Override
            public void OnDataAvailable(List<VideoDocument> videosList) {
                Log.d(TAG, "OnDataAvailable: starts");
                Log.d(TAG, "OnDataAvailable: video list received " + videosList.toString());
                for (VideoDocument videoItem: videosList) {
                    videosCollectionRef.document(videoItem.getVideoId())
                            .set(videoItem, SetOptions.merge()) //to merge new data if doc exists
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully written! " + videoItem.getVideoId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error writing document", e);
                                }
                            });
                }
            }
        }, videoIds);
    }

    //read all videos
    public static void getAllVideos(){
        videosCollectionRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
