package com.jay.appdemo1.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.adapter.SeachAdapter;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.modal.HistoryModal;
import com.jay.appdemo1.modal.SeachModal;
import com.jay.appdemo1.ui.view.AutoButtonView;
import com.jay.appdemo1.ui.view.ConsumeList;
import com.jay.appdemo1.utils.HttpUtil;
import com.jay.appdemo1.utils.LOGUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

/**
 * Created by liuzhi on 2019/2/16.
 */

public class SeachActivity extends Activity implements SearchView.OnQueryTextListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private SearchView seach;
    private TextView seach_cancel;
    private TextView txt_search;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String data = (String) msg.obj;
            LOGUtils.Log("data:"+data);
            switch (msg.what){
                case 0:
                    if (data!=null && !data.equals("")) {
                        seach_result_ll.setVisibility(View.VISIBLE);
                        seach_no_data.setVisibility(View.GONE);
                        final SeachModal seachModal = new Gson().fromJson(data, SeachModal.class);
                        final List<SeachModal.ItemListBean> itemList = seachModal.getItemList();
                        if (itemList==null || itemList.size()==0){
                            seach_result_ll.setVisibility(View.GONE);
                            seach_verlabry_ll.setVisibility(View.GONE);
                            seach_no_data.setVisibility(View.VISIBLE);
                            seach_no_data.setOnClickListener(SeachActivity.this);
                            return;
                        }
                        //更改textview的数据
                        seach_result_tv.setText("-["+text.toString().trim()+"]搜索结果共"+seachModal.getTotal()+"个-");
                        LOGUtils.Log("准备走适配器"+itemList.size());
                        //通过listview将数据展现出来
                        seach_result_lv.setAdapter(new SeachAdapter(itemList));

                        //设置listview下拉刷新的监听
                        seach_result_lv.setOnRefreshListener(new ConsumeList.OnHandlerListener() {
                            @Override
                            public void loadMore() {
                                final String nextPageUrl = seachModal.getNextPageUrl();
                                if (nextPageUrl!=null && !nextPageUrl.equals("")) {
                                    seach_result_lv.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            new HttpUtils().send(HttpRequest.HttpMethod.GET, nextPageUrl, new RequestCallBack<String>() {
                                                @Override
                                                public void onSuccess(ResponseInfo<String> responseInfo) {
                                                    String result = responseInfo.result;
                                                    if (result!=null && !result.equals("")){
                                                        SeachModal seachModal1 = new Gson().fromJson(result, SeachModal.class);
                                                        List<SeachModal.ItemListBean> itemList1 = seachModal1.getItemList();
                                                        SeachAdapter.setData(itemList1);
                                                        seach_result_lv.refreshFinish(0);
                                                    }
                                                }

                                                @Override
                                                public void onFailure(HttpException e, String s) {
                                                    Toast.makeText(Mic.getmCtx(),"没有更多数据",Toast.LENGTH_LONG).show();
                                                    seach_result_lv.refreshFinish(0);
                                                }
                                            });
                                        }
                                    });
                                }else {
                                    Toast.makeText(Mic.getmCtx(),"没有更多数据",Toast.LENGTH_LONG).show();
                                    seach_result_lv.refreshFinish(0);
                                }
                            }
                        });

                    }else{
                        seach_result_ll.setVisibility(View.GONE);
                        seach_verlabry_ll.setVisibility(View.GONE);
                        seach_no_data.setVisibility(View.VISIBLE);
                        seach_no_data.setOnClickListener(SeachActivity.this);
                    }
                    break;
                case -1:
                    break;
            }
        }
    };
    private LinearLayout seach_verlabry_ll;
    private LinearLayout seach_result_ll;
    private ConsumeList seach_result_lv;
    private TextView seach_result_tv;
    private CharSequence text;
    private RelativeLayout seach_no_data;
    private AutoButtonView auto_button_view;
    private String[] buttons = new String[]{"美食","旅行","生活小技巧","健身","汽车","广告","动画","创意灵感","当下乱码","一条","日食记","视知TV"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seach_layout);
        seach_verlabry_ll = findViewById(R.id.seach_verlabry_ll);
        seach_result_ll = findViewById(R.id.seach_result_ll);
        seach_result_lv = findViewById(R.id.seach_result_lv);
        seach_result_tv = findViewById(R.id.seach_result_tv);
        seach_no_data = findViewById(R.id.seach_no_data);
        auto_button_view = findViewById(R.id.auto_button_view);
        seach = findViewById(R.id.seach_);
        txt_search = seach.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        seach_cancel = findViewById(R.id.seach_cancel);
        txt_search.setTextSize(15);
        txt_search.setHintTextColor(0xababab);
        //让键盘的回车键设置成搜索
        seach.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        seach.setOnQueryTextListener(this);
        seach_result_lv.setOnItemClickListener(this);

        for (int i=0;i<buttons.length;i++){
            TextView textView = new TextView(this);
            textView.setText(buttons[i]);
            textView.setTextSize(16);
            auto_button_view.addView(textView);
        }

    }

    /**===============================搜索监听，包括点击搜索和搜索框内容发生改变回调方法==================================*/
    @Override
    public boolean onQueryTextSubmit(String query) {
        //监听点击了搜索按钮
        /**通过用户点击搜索按钮后，获取搜索框中的内容，拼接到搜索地址后面进行搜索
         * 将搜索的结果使用listview展现出来*/
        seach_verlabry_ll.setVisibility(View.GONE);
        seach_no_data.setVisibility(View.GONE);
        text = txt_search.getText();
        //通过搜索拼接链接，获取数据
        HttpUtil.getXUtils(Mic.SEEK_URL+ text.toString().trim(),mHandler);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    /**==============================图片的点击事件==================================*/

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.seach_no_data:
                seach_result_ll.setVisibility(View.INVISIBLE);
                seach_verlabry_ll.setVisibility(View.VISIBLE);
                seach_no_data.setVisibility(View.INVISIBLE);
                break;

            case R.id.seach_cancel:
                finish();
                break;
        }
    }

    /**==============================listView的点击事件==================================*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
        String urls = null;
        SeachModal.ItemListBean object = (SeachModal.ItemListBean) parent.getAdapter().getItem(position);
        urls = object.getData().getPlayUrl();
        if (urls==null || urls.equals("")) {
            urls = object.getData().getWebUrl().getForWeibo();
        }
        Intent intent = new Intent(Mic.getmCtx(), ItemActivity.class);
        intent.putExtra("video_url", urls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Mic.getmCtx().startActivity(intent);

        HistoryModal historyModal = new HistoryModal();

        String forWeibo = object.getData().getPlayUrl();
        if (forWeibo==null || forWeibo.equals("")){
            forWeibo = object.getData().getWebUrl().getRaw();
        }

        String homepage = (String) object.getData().getCover().getHomepage();
        if (homepage == null || homepage.equals("")){
            homepage = object.getData().getCover().getFeed();
        }

        int duration = object.getData().getDuration();
        int min = duration/60;
        int second = duration % 60;
        String category = "#" + object.getData().getCategory() + "/" + min + "'" + second + "'";
        HashMap<String, HistoryModal> viewHistory_map = DbOperation.getInstance().dbQuery_viewHistory();
        for (String key:viewHistory_map.keySet()){
            if (key.equals(object.getData().getTitle())){
                DbOperation.getInstance().dbDelect_viewHistory(key);
                DbOperation.getInstance().dbInsert_viewHistory("",forWeibo,object.getData().getTitle(),category,homepage);
                return;
            }else{
                continue;
            }
        }
        DbOperation.getInstance().dbInsert_viewHistory("",forWeibo,object.getData().getTitle(),category,homepage);

        //记录本次点击的视频链接,如果本地有缓存过的话，就删除原来的数据重新添加
        /*for (int i=0;i<HistoryViewActivity.historyList.size();i++){
            if (HistoryViewActivity.historyList!=null &&
                    HistoryViewActivity.historyList.get(i).getTitle().equals(historyModal.getTitle())){
                LOGUtils.Log("存在相同条目");
                HistoryViewActivity.historyList.remove(i);
            }
        }*/
//        HistoryViewActivity.historyList.add(0,historyModal);
    }


}
