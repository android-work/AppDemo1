package com.jay.appdemo1.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jay.appdemo1.Mic;
import com.jay.appdemo1.modal.HistoryModal;
import com.jay.appdemo1.modal.find.Focus_model;
import com.jay.appdemo1.modal.PreviewModel;
import com.jay.appdemo1.utils.LOGUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class DbOperation {

    private HashMap<String, String> mUser_info;

    private DbOperation(){
        if (mDbHelp==null){
            mDbHelp = new DBHelp(Mic.getmCtx(),"DB.db",null,1);
        }
    }
    private static DbOperation mDbOperation;
    private static DBHelp mDbHelp;
    private static HashMap<String,String> mUserMap ;

    public static DbOperation getInstance(){

        if (mDbOperation==null){
            mDbOperation=new DbOperation();
        }
        return mDbOperation;
    }

    //增，功能用来表示注册
    public long dbInsert_user(String name, String password){
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name",name);
        contentValues.put("password",password);
        LOGUtils.Log("注册用户");
        long user = db.insert("user", null, contentValues);
        return user;
    }

    //增，往user_info表中写入uid
    public void dbInsert_info(String uid){
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid",uid);
        db.insert("user_info",null,contentValues);
    }

    //往关注表中添加数据
    public void dbinsert_focus( String uid, String head, String editor, String icon, String des, String title, String category,String playUrl){
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid",uid);
        contentValues.put("head",head);
        contentValues.put("editor",editor);
        contentValues.put("icon",icon);
        contentValues.put("des",des);
        contentValues.put("title",title);
        contentValues.put("category",category);
        contentValues.put("playUrl",playUrl);
        db.insert("focus",null,contentValues);
    }

    //添加历史观看记录存入数据库
    public void dbInsert_viewHistory(String uid,String playUrl,String title,String category,String icon){
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid",uid);
        contentValues.put("playUrl",playUrl);
        contentValues.put("title",title);
        contentValues.put("category",category);
        contentValues.put("icon",icon);
        db.insert("viewHistory",null,contentValues);
    }

    //添加草稿数据
    public void dbInstert_draft(String uid,String title,String photo,String video_path){
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid",uid);
        contentValues.put("title",title);
        contentValues.put("photo",photo);
        contentValues.put("video_path",video_path);
        db.insert("draft",null,contentValues);
    }

    //模拟视频发布成功
    public void dbInsert_preview(String table,String uid,String title,String photo,String video_path){
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid",uid);
        contentValues.put("title",title);
        contentValues.put("photo",photo);
        contentValues.put("video_path",video_path);
        db.insert(table,null,contentValues);
    }


    //删
    public void dbDelect_foces_info(String name){
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        db.delete("focus","editor=?",new String[]{name});
    }

    //根据title删除对应的历史记录
    public void dbDelect_viewHistory(String title){
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        db.delete("viewHistory","title=?",new String[]{title});
    }

    //发布成功，根据视频连接删除草稿箱里的数据
    public void dbDelect_draft(String url){
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        db.delete("draft","video_path=?",new String[]{url});
    }

    //改,进行用户登录/退出登录
    public void dbUpdate_user(String uid,String name){
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid",uid);
        db.update("user",contentValues,"user_name=?",new String[]{name});
    }

    //改，进行用户字段的更改，用户信息的更改
    public void dbUpdate_user_info(String uid,String upName,String value,String upTable){
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(upName,value);
        db.update(upTable,contentValues,"uid=?",new String[]{uid});
    }

    //根据视频/图片地址进行更新表
    public void dbUpData_draft(String video_path,String title){
        LOGUtils.Log("video_path:"+video_path+";;;;title:"+title);
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        db.update("draft",contentValues,"video_path=?",new String[]{video_path});
    }

    //查,检测用户是否注册、登录等
    public HashMap<String ,String > dbQuery_user(){
        mUserMap = new HashMap<>();
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        Cursor query = db.query("user", null, null, null, null, null, null);
        if (query!=null && query.getCount()>0){
            while(query.moveToNext()){
                String uid = query.getString(0);
                String user_name = query.getString(1);
                String user_password = query.getString(2);

                mUserMap.put("uid",uid);
                mUserMap.put("user_name",user_name);
                mUserMap.put("password",user_password);
            }
        }
        return mUserMap;
    }

    //查，查找用户个人信息
    public HashMap<String ,String > dbQuery_user_info(){
        mUser_info = new HashMap<>();
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        Cursor query = db.query("user_info", null, null, null, null, null, null);
        if (query!=null && query.getCount()>0){
            while (query.moveToNext()){
                String head = query.getString(0);
                String uid = query.getString(1);
                String sex = query.getString(2);
                String sign = query.getString(3);
                String address = query.getString(4);

                mUser_info.put("head",head);
                mUser_info.put("uid",uid);
                mUser_info.put("sex",sex);
                mUser_info.put("sign",sign);
                mUser_info.put("address",address);
            }
        }
        return mUser_info;
    }

    //查，根据uid查user表中的用户名
    public String db_query_user_name(String uid){
        String name = null;
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        Cursor query = db.query("user", null, "uid=?", new String[]{uid}, null, null, null);
        if (query!=null && query.getCount()>0){
            while (query.moveToNext()){
                name = query.getString(1);
            }
        }
        return name;
    }

    //查找关注的信息
    public ArrayList<Focus_model> query_Focus_info(String name){
            ArrayList<Focus_model> focus_models = new ArrayList<>();
            SQLiteDatabase db = mDbHelp.getReadableDatabase();
            Cursor query = db.query("focus", null, "editor = ?", new String[]{name}, null, null, null);
            if (query!=null){
                while(query.moveToNext()){
                    String uid = query.getString(0);
                    String head = query.getString(1);
                    String editor = query.getString(2);
                    String des = query.getString(3);
                    String icon = query.getString(4);
                    String title = query.getString(5);
                    String category = query.getString(6);
                    String playUrl = query.getString(7);

                    Focus_model focus_model = new Focus_model();
                    focus_model.setCatgory(category);
                    focus_model.setDes(des);
                    focus_model.setEditor(editor);
                    focus_model.setHead(head);
                    focus_model.setIcon(icon);
                    focus_model.setTitle(title);
                    focus_model.setUid(uid);
                    focus_model.setPlayUrl(playUrl);

                    LOGUtils.Log("查询结果：head:"+head+"-------name:"+editor+"-------------des:"+des+"---------title:"+title+"----------icon:"+icon+"-----------category:"+category+"-------playurl:"+playUrl);

                    focus_models.add(focus_model);
                }
            }
            return focus_models;
    }

    public HashMap<String ,Focus_model> query_Focus_info() {
        HashMap<String,Focus_model> focus_models = new HashMap<>();
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        Cursor query = db.query("focus", null, null,null, null, null, null);
        if (query != null) {
            while (query.moveToNext()) {
                String uid = query.getString(0);
                String head = query.getString(1);
                String editor = query.getString(2);
                String des = query.getString(3);
                String icon = query.getString(4);
                String title = query.getString(5);
                String category = query.getString(6);
                String playUrl = query.getString(7);

                Focus_model focus_model = new Focus_model();
                focus_model.setCatgory(category);
                focus_model.setDes(des);
                focus_model.setEditor(editor);
                focus_model.setHead(head);
                focus_model.setIcon(icon);
                focus_model.setTitle(title);
                focus_model.setUid(uid);
                focus_model.setPlayUrl(playUrl);

                LOGUtils.Log("查询结果：head:" + head + "-------name:" + editor + "-------------des:" + des + "---------title:" + title + "----------icon:" + icon + "-----------category:" + category+"------playurl:"+playUrl);

                focus_models.put(editor,focus_model);
                LOGUtils.Log("focus_models:"+focus_models);
            }
        }
        return focus_models;
    }

    //查询历史缓存视频数据查重视频
    public HashMap<String ,HistoryModal> dbQuery_viewHistory(){
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        HashMap<String, HistoryModal> viewHistory_Map = new HashMap<>();
        Cursor query = db.query("viewHistory", null, null, null, null, null, null);
        if (query!=null){
            while(query.moveToNext()){
                String uid = query.getString(0);
                String playUrl = query.getString(1);
                String icon = query.getString(2);
                String title = query.getString(3);
                String category = query.getString(4);

                HistoryModal historyModal = new HistoryModal();
                historyModal.setCategory(category);
                historyModal.setForWeibo(playUrl);
                historyModal.setHomepage(icon);
                historyModal.setTitle(title);
                historyModal.setUid(uid);

                viewHistory_Map.put(title,historyModal);
            }
        }
        return viewHistory_Map;
    }

    //查询历史缓存视频数据排序视频
    public ArrayList<HistoryModal> dbQuery_viewHistorys(){
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        ArrayList<HistoryModal> viewHistory_Map = new ArrayList<>();
        Cursor query = db.query("viewHistory", null, null, null, null, null, null);
        if (query!=null){
            while(query.moveToNext()){
                String uid = query.getString(0);
                String playUrl = query.getString(1);
                String icon = query.getString(2);
                String title = query.getString(3);
                String category = query.getString(4);

                HistoryModal historyModal = new HistoryModal();
                historyModal.setCategory(category);
                historyModal.setForWeibo(playUrl);
                historyModal.setHomepage(icon);
                historyModal.setTitle(title);
                historyModal.setUid(uid);

                viewHistory_Map.add(0,historyModal);
            }
        }
        return viewHistory_Map;
    }

    //查询本地草稿箱数据库
    public ArrayList<PreviewModel> dbQuery_draft(String table){
        ArrayList<PreviewModel> preview_List = new ArrayList<>();
        SQLiteDatabase db = mDbHelp.getReadableDatabase();
        Cursor cursor = db.query(table, null, null, null, null, null, null);
        if (cursor!=null){
            while(cursor.moveToNext()){
                String uid = cursor.getString(0);
                String title = cursor.getString(1);
                String photo = cursor.getString(2);
                String video_path = cursor.getString(3);

                PreviewModel previewModel = new PreviewModel();
                previewModel.setPhoto(photo);
                previewModel.setTitle(title);
                previewModel.setUid(uid);
                previewModel.setVideo_path(video_path);

                preview_List.add(previewModel);

            }
        }
        return preview_List;
    }

}
