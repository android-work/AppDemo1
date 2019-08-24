package com.jay.appdemo1.photo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.jay.appdemo1.R;
import com.jay.appdemo1.thread.SaveVideoThread;
import com.jay.appdemo1.ui.activity.MainActivity;
import com.jay.appdemo1.utils.LOGUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PhotoActivity extends Activity implements View.OnClickListener {

    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;
    private String url;
    private SurfaceHolder holder;
    private final static int SAVE_PHOTO=0;
    private final static int SAVE_VIDEO=1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            next = findViewById(R.id.next);
            next.setOnClickListener(PhotoActivity.this);
            png_path = (String) msg.obj;

        }
    };
    private String png_path;
    private Button next;
    private String list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        setContentView(R.layout.photo);

        surfaceView = findViewById(R.id.record_surfaceView);


        //获取视频第一帧作为缩略图
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "MyCameraApp");
        String[] lists = file.list();
        String list = null;
        File first = null;
        for (int i=0;i<lists.length;i++){
            if (i==lists.length-1){
                list = lists[i];
                first = new File(file, list);
                LOGUtils.Log("list:"+list);
            }
        }
        Bitmap firstPhoto = getFirstPhoto(first.getAbsolutePath());
        //将图片保存到本地中
        new Thread(new SaveVideoThread(firstPhoto,SAVE_PHOTO,url,handler)).start();

        play(url);

    }

    private Bitmap getFirstPhoto(String url) {
        LOGUtils.Log("url:"+url);
        MediaMetadataRetriever media = new MediaMetadataRetriever();

        media.setDataSource(url);
//        Bitmap  b = ThumbnailUtils.createVideoThumbnail(url,MediaStore.Video.Thumbnails.MICRO_KIND);
        return  media.getFrameAtTime();
    }

    public void play(String url){
        //创建一个MediaPlayer对象
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(this,Uri.parse(url));
            holder = surfaceView.getHolder();
            holder.addCallback(new MyCallback());
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {
        //开启一个新的界面，进行模拟上传视频
        LOGUtils.Log("下一步");
        Intent intent = new Intent(this, IssueActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("photo",png_path);
        startActivity(intent);


    }

    private class MyCallback implements SurfaceHolder.Callback{

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            mediaPlayer.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LOGUtils.Log("onResume");
        play(url);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer!=null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            LOGUtils.Log("issue onstop playing");
        }mediaPlayer = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null) {
            mediaPlayer.reset();
            mediaPlayer = null;
        }
        LOGUtils.Log("issue ondestroy");
    }
}
