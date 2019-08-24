package com.jay.appdemo1.photo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.ui.fragment.Mine_Fragment;
import com.jay.appdemo1.utils.LOGUtils;

import java.io.IOException;
import java.util.HashMap;

public class SurfaceViewActivity extends Activity {

    private SurfaceView surface;
    private TextView user;
    private MediaPlayer mediaPlayer;
    private String url;
    private SurfaceHolder holder;
    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        setContentView(R.layout.surface_layout);

        surface = findViewById(R.id.surface_play);
        user = findViewById(R.id.user);

        if (Mic.user_name!=null){
            user.setText("@"+Mic.user_name);
        }else if (Mine_Fragment.user_name!=null){
            user.setText(Mine_Fragment.user_name);
        }else{
            HashMap<String, String> user_map = DbOperation.getInstance().dbQuery_user();
            String user_name = user_map.get("user_name");
            user.setText(user_name);
        }

        play();
    }

    public void play(){
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(this, Uri.parse(url));
            mediaPlayer.prepare();
            holder = surface.getHolder();
            holder.addCallback(new MyCallback());
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

    private class MyCallback implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            mediaPlayer.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (mediaPlayer!=null) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer = null;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LOGUtils.Log("onResume");
        play();
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
