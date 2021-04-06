package com.smirky.firestoredatabase.youtubeapi;

import android.app.Application;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppControllerVolley extends Application {
    private static final String TAG = "AppControllerVolley";
    private static AppControllerVolley instance;
    private RequestQueue requestQueue;


    public static synchronized AppControllerVolley getInstance() {
        Log.d(TAG, "getInstance: called");
        Log.d(TAG, "getInstance: instance" + instance.toString());
        return instance;
    }


    public RequestQueue getRequestQueue() {
        Log.d(TAG, "getRequestQueue: called");
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        Log.d(TAG, "getRequestQueue: requestqueue" + requestQueue.toString());
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        Log.d(TAG, "addToRequestQueue: called");
        getRequestQueue().add(req);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
