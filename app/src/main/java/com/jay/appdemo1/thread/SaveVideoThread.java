package com.jay.appdemo1.thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import com.jay.appdemo1.db.DbOperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveVideoThread implements Runnable {

    private String text;
    private String url;
    private Bitmap firstPhoto;
    private int type;
    private Handler handler;

    public SaveVideoThread(Bitmap firstPhoto, int type, String url, Handler handler) {

        this.firstPhoto = firstPhoto;
        this.type = type;
        this.url = url;
        this.handler = handler;
    }

    @Override
    public void run() {

        String path = null;

        if (type == 0){
            //保存图片
            File photo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "MyCameraPhoto");
            if (!photo.exists()) {
                photo.mkdirs();
            }

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File mediaFile = new File(photo.getPath() + File.separator + "VID_" + timeStamp + ".png");
            if (!mediaFile.exists()){
                try {
                    mediaFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //将bitmap对象保存到本地图片
            try {
                FileOutputStream fos = new FileOutputStream(mediaFile);
                firstPhoto.compress(Bitmap.CompressFormat.PNG,100,fos);
                fos.flush();
                fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            //将图片保存到草稿箱数据库
            DbOperation.getInstance().dbInstert_draft("","",mediaFile.getAbsolutePath(),url);

            Message msg = Message.obtain();
            msg.obj = mediaFile.getAbsolutePath();
            handler.sendMessage(msg);

        }else{
            //保存视频
        }
    }
}
