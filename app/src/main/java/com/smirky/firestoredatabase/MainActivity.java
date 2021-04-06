package com.smirky.firestoredatabase;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.smirky.firestoredatabase.database.DatabaseManipulation;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    String[] idVideosToAdd = new String[15];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Add playlist
        idVideosToAdd[0] = "mPCDQ34S8Rs";
        idVideosToAdd[1] = "xLpZZScqgKo";
        idVideosToAdd[2] = "Ho4RK6z2CIM";
        idVideosToAdd[3] = "HQILxqS831E";
        idVideosToAdd[4] = "zHD6kxU45BY";
        idVideosToAdd[4] = "dtaJzUbQS7E";
        DatabaseManipulation.addPlaylist("5","Test title 5", idVideosToAdd);



////       Add to videos collection
//        idVideosToAdd[0] = "mPCDQ34S8Rs";
//        Log.d(TAG, "onCreate: idVideos list" + idVideosToAdd.toString());
//        DatabaseManipulation.addVideos(idVideosToAdd);

//      Get all videos from collection
        DatabaseManipulation.getAllVideos();

    }
}