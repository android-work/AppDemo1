package com.jay.appdemo1.utils;

import android.util.Log;

/**
 * Created by liuzhi on 2018/12/7.
 */

public class LOGUtils {
    private static final String TAG="tag";
    private static final boolean is= true;
    public static void Log(String str){
        if (is){
            Log.e(TAG,str);
        }
    }
}
