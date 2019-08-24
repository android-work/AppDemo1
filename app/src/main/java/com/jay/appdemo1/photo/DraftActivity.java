package com.jay.appdemo1.photo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.jay.appdemo1.R;
import com.jay.appdemo1.adapter.DraftAdapter;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.modal.PreviewModel;
import com.jay.appdemo1.utils.LOGUtils;

import java.util.ArrayList;

public class DraftActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView draft_back;
    private ListView draft_lv;
    private ArrayList<PreviewModel> draft;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            draft_lv = findViewById(R.id.draft_lv);

            draft_back.setOnClickListener(DraftActivity.this);

            draft_lv.setAdapter(new DraftAdapter(draft));

            draft_lv.setOnItemClickListener(DraftActivity.this);


        }
    };
    private String video_path;
    private String table;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.work_layout);

        draft_back = findViewById(R.id.draft_back);


        new Thread(new Runnable() {
            @Override
            public void run() {
                //读取草稿箱数据库，展示数据
                draft = DbOperation.getInstance().dbQuery_draft("draft");
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.draft_back:
                finish();
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        PreviewModel previewModel = (PreviewModel) adapterView.getAdapter().getItem(i);
        String title = previewModel.getTitle();
        String photo = previewModel.getPhoto();
        video_path = previewModel.getVideo_path();
        Intent intent = new Intent(this, IssueActivity.class);
        intent.putExtra("photo",photo);
        intent.putExtra("title",title);
        intent.putExtra("url", video_path);
        LOGUtils.Log("url_draft:"+video_path);
        startActivity(intent);
        finish();

    }
}
