package com.jay.appdemo1;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.jay.appdemo1.db.DBHelp;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.modal.HistoryModal;
import com.jay.appdemo1.ui.activity.HistoryViewActivity;
import com.jay.appdemo1.utils.LOGUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by liuzhi on 2018/12/7.
 */

public class Mic extends Application {

    private static Context mCtx;
    public static final String HOME_URL="http://baobab.kaiyanapp.com/api/v2/feed?";
    public static final String VIDEO_URL="http://baobab.kaiyanapp.com/api/v4/video/related?id=36";
    public static final String CLASS_URL="http://baobab.kaiyanapp.com/api/v4/categories";
    public static final String GET_CLASS_URL="http://baobab.kaiyanapp.com/api/v4/discovery/category"/*"http://baobab.kaiyanapp.com/api/v4/categories/videoList?id=xxx&udid=xxx"*/;
    public static final String RANK_URL="http://baobab.kaiyanapp.com/api/v4/rankList";
    public static final String SEEK_URL="http://baobab.kaiyanapp.com/api/v1/search?&num=10&start=10&query==";
    public static final String HOT_URL="http://baobab.kaiyanapp.com/api/v3/queries/hot";
    public static final String FOCUS_URL="http://baobab.kaiyanapp.com/api/v4/tabs/follow";
    public static String uid;
    public static String user_name;


    public static Context getmCtx() {
        return mCtx;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LOGUtils.Log("Mic的application");
        mCtx = getApplicationContext();
        HashMap<String, String> user_map = DbOperation.getInstance().dbQuery_user();
        uid = user_map.get("uid");
        user_name = user_map.get("user_name");
    }

}
