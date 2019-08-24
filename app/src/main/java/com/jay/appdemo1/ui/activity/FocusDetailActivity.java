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

import com.jay.appdemo1.R;
import com.jay.appdemo1.adapter.FocusAdapter;
import com.jay.appdemo1.adapter.FocusDetailAdapter;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.modal.HistoryModal;
import com.jay.appdemo1.modal.find.Focus_model;
import com.jay.appdemo1.modal.home.Home_listview_modal;
import com.jay.appdemo1.ui.fragment.listener.RVListener;
import com.jay.appdemo1.utils.LOGUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class FocusDetailActivity extends Activity {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            detail_rv = findViewById(R.id.detail_rv);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FocusDetailActivity.this);
            detail_rv.setLayoutManager(linearLayoutManager);
            detail_rv.setAdapter(new FocusDetailAdapter(focus_models));
        }
    };

    private RecyclerView detail_rv;
    private ArrayList<Focus_model> focus_models;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        setContentView(R.layout.focus_detail_layout);
        FocusDetailAdapter.setOnItemClick(new RVListener() {
            @Override
            public void onItemClick(View view) {
                int position = detail_rv.getChildAdapterPosition(view);
                Intent intent = new Intent(FocusDetailActivity.this, ItemActivity.class);
                LOGUtils.Log("position:"+position+"--------list:"+focus_models);
                intent.putExtra("position",position);
                String playUrl = focus_models.get(position).getPlayUrl();
                intent.putExtra("video_url",playUrl);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                HistoryModal historyModal = new HistoryModal();

                HashMap<String, HistoryModal> viewHistory_map = DbOperation.getInstance().dbQuery_viewHistory();
                for (String key:viewHistory_map.keySet()){
                    if (key.equals(focus_models.get(position).getTitle())){
                        DbOperation.getInstance().dbDelect_viewHistory(key);
                        DbOperation.getInstance().dbInsert_viewHistory("",playUrl,focus_models.get(position).getTitle(),focus_models.get(position).getCatgory(),focus_models.get(position).getIcon());
                        return;
                    }else{
                        continue;
                    }
                }
                DbOperation.getInstance().dbInsert_viewHistory("",playUrl,focus_models.get(position).getTitle(),focus_models.get(position).getCatgory(),focus_models.get(position).getIcon());

//                HistoryModal historyModal = new HistoryModal();

               /* historyModal.setForWeibo(playUrl);

                historyModal.setHomepage();
                historyModal.setTitle();
                historyModal.setCategory();

                //记录本次点击的视频链接,如果本地有缓存过的话，就删除原来的数据重新添加
                for (int i=0;i<HistoryViewActivity.historyList.size();i++){
                    if (HistoryViewActivity.historyList!=null &&
                            HistoryViewActivity.historyList.get(i).getTitle().equals(historyModal.getTitle())){
                        LOGUtils.Log("存在相同条目");
                        HistoryViewActivity.historyList.remove(i);
                    }
                }*/

                LOGUtils.Log("不存在相同条目"+historyModal);
//                HistoryViewActivity.historyList.add(0,historyModal);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                focus_models = DbOperation.getInstance().query_Focus_info(name);
                if (focus_models!=null && focus_models.size()>0){
                    handler.sendEmptyMessage(1);
                }

            }
        }).start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
