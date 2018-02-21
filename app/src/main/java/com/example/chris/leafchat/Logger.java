package com.example.chris.leafchat;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Chris on 2/20/18.
 */

public class Logger {
    public static void log(String tag, String message) {
        Log.d(tag, message);
    }
}
