package com.jay.appdemo1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jay.appdemo1.utils.LOGUtils;

public class DBHelp extends SQLiteOpenHelper {

    public DBHelp(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        LOGUtils.Log("创建数据库");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("create table user (uid char(200)" +
                ",user_name char(200) not null,password char(200) not null);");

        sqLiteDatabase.execSQL("create table user_info(head char(1000)" +
                ",uid char(200),sex char(20),sign char(1000),address char(1000));");

        sqLiteDatabase.execSQL("create table focus (uid char(200)" +
                ",head char(200),editor char(200),des char(200),icon char(200)," +
                "title char(200),category char(200),playUrl char(200))");

        sqLiteDatabase.execSQL("create table viewHistory (uid char(200),playUrl char(200)" +
                ",icon char(200),title char(200),category char(200))");

        //草稿数据库
        sqLiteDatabase.execSQL("create table draft (uid char(200)" +
                ",title char(65),photo char(200),video_path char(200))");

        //保存本地数据库
        sqLiteDatabase.execSQL("create table save_local (uid char(200)," +
                "title char(65),photo char(200),video_path char(200))");

        //保存发布数据库
        sqLiteDatabase.execSQL("create table preview (uid char(200)," +
                "title char(65),photo char(200),video_path char(200))");

   //提交意见反馈数据库
        sqLiteDatabase.execSQL("create table issue (uid char(200)," +
                "people_issue char(200))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
