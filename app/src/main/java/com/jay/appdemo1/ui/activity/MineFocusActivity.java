package com.jay.appdemo1.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.adapter.FocusAdapter;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.modal.find.Focus_model;
import com.jay.appdemo1.ui.fragment.listener.RVListener;
import com.jay.appdemo1.utils.LOGUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class MineFocusActivity extends Activity implements RVListener {

    private RecyclerView focus_rv;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case 1:
                    focus_rv = findViewById(R.id.focus_rv);
                    LinearLayoutManager lm = new LinearLayoutManager(Mic.getmCtx());
                    focus_rv.setLayoutManager(lm);
                    focus_rv.setAdapter(new FocusAdapter(focus_models));
                    FocusAdapter.setItemOnClickListener(MineFocusActivity.this);
                    break;
                case 0:
                    focus_other.setImageResource(R.mipmap.ic_nodata);
                    break;
            }
        }
    };
    private HashMap<String,Focus_model> focus_models;
    private FrameLayout focus_fl;
    private ImageView focus_other;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_focus_activity_layout);

        focus_fl = findViewById(R.id.focus_loading);
        focus_other = findViewById(R.id.focus_other);
        focus_fl.setVisibility(View.VISIBLE);
        focus_other.setImageResource(R.drawable.loading_pb);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求数据库获取关注数据
                focus_models = DbOperation.getInstance().query_Focus_info();
                if (focus_models==null || focus_models.size()==0){
                    handler.sendEmptyMessage(0);
                    return;
                }
                handler.sendEmptyMessage(1);
                focus_fl.setVisibility(View.GONE);
            }
        }).start();

    }

    /**================================================rv条目的点击事件============================================*/
    @Override
    public void onItemClick(View view) {
        int position = focus_rv.getChildAdapterPosition(view);
        Intent intent = new Intent(this, FocusDetailActivity.class);
        LOGUtils.Log("position:"+position+"--------list:"+focus_models);
        intent.putExtra("name",FocusAdapter.focus_list.get(position).getEditor());
        startActivity(intent);
    }
}
