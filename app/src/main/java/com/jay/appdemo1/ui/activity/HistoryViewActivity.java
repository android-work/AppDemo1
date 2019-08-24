package com.jay.appdemo1.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.jay.appdemo1.R;
import com.jay.appdemo1.adapter.HistoryViewAdapter;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.modal.HistoryModal;
import com.jay.appdemo1.utils.LOGUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class HistoryViewActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView history_back;
    private ListView history_lv;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            history_back = findViewById(R.id.history_back);
            history_lv = findViewById(R.id.history_lv);

            history_back.setOnClickListener(HistoryViewActivity.this);
            historyViewAdapter = new HistoryViewAdapter(historyModals);
            history_lv.setAdapter(historyViewAdapter);

            history_lv.setOnItemClickListener(HistoryViewActivity.this);
        }
    };
    public ArrayList<HistoryModal> historyModals;
    private HistoryViewAdapter historyViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);

       getData();

    }

    public void getData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                historyModals = DbOperation.getInstance().dbQuery_viewHistorys();
                if (historyModals ==null || historyModals.size()==0){
                    return;
                }else{
                    handler.sendEmptyMessage(1);
                }
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {

        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position , long l) {

        HistoryModal item = (HistoryModal) adapterView.getAdapter().getItem(position);
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra("video_url",item.getForWeibo());
        startActivity(intent);

        HashMap<String, HistoryModal> viewHistory_map = DbOperation.getInstance().dbQuery_viewHistory();
        for (String key:viewHistory_map.keySet()){
            if (key.equals(item.getTitle())){
                LOGUtils.Log("存在相同数据");
                DbOperation.getInstance().dbDelect_viewHistory(key);
                DbOperation.getInstance().dbInsert_viewHistory("",item.getForWeibo(),item.getTitle(),item.getCategory(),item.getHomepage());
                getData();
                historyViewAdapter.notifyDataSetChanged();
                return;
            }else{
                continue;
            }
        }
        DbOperation.getInstance().dbInsert_viewHistory("",item.getForWeibo(),item.getTitle(),item.getCategory(),item.getHomepage());
    }
}
