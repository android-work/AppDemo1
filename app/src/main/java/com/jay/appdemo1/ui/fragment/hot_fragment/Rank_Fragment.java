package com.jay.appdemo1.ui.fragment.hot_fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.adapter.hot.Rank_Adapter;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.modal.HistoryModal;
import com.jay.appdemo1.modal.home.Rank_model;
import com.jay.appdemo1.modal.modalMgr.ModalMgr;
import com.jay.appdemo1.ui.activity.HistoryViewActivity;
import com.jay.appdemo1.ui.activity.ItemActivity;
import com.jay.appdemo1.ui.fragment.BaseFragment;
import com.jay.appdemo1.ui.fragment.Hot_drop_Fragment;
import com.jay.appdemo1.ui.fragment.listener.ValueListener;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.LoadData;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.HashMap;
import java.util.List;

/**
 * Created by liuzhi on 2019/2/16.
 */


public class Rank_Fragment extends Fragment implements AdapterView.OnItemClickListener {
    private static String str;
    private static final String KEY = "url";
    private static final String KEY_1 = "title";
    private ListView rank_lv;
    private List<Rank_model.ItemListBean> itemList;
    private String url;
    private View view;
    private String title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        url = getArguments().getString(KEY);
        title = getArguments().getString(KEY_1);
        LOGUtils.Log(title+":url:"+ url);
        view = inflater.inflate(R.layout.rank_layout, null);
        rank_lv = view.findViewById(R.id.rank_lv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if ((ModalMgr.getInstance().getRank_modelHashMap().get(title)==null) && (url!=null || !url.equals(""))) {
            LOGUtils.Log("rank集合中没有数据");
            //请求网络获取数据
            new HttpUtils().send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String result = responseInfo.result;
                    Rank_model rank_model = new Gson().fromJson(result, Rank_model.class);
                    ModalMgr.getInstance().getRank_modelHashMap().put(title, rank_model);

                    itemList = rank_model.getItemList();
                    rank_lv.setAdapter(new Rank_Adapter(itemList, Mic.getmCtx()));
                }

                @Override
                public void onFailure(HttpException e, String s) {

                }
            });
        }else{
            LOGUtils.Log("rank集合中有数据");
            itemList = ModalMgr.getInstance().getRank_modelHashMap().get(title).getItemList();
            rank_lv.setAdapter(new Rank_Adapter(itemList, Mic.getmCtx()));
        }

        rank_lv.setOnItemClickListener(this);

    }

    public static Rank_Fragment newInstance(String str,String title){
        Rank_Fragment fragment = new Rank_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,str);
        bundle.putString(KEY_1,title);
        fragment.setArguments(bundle);

        return fragment;
    }
/**=============================================================listview条目点击事件=============================================================*/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String urls = null;
        Rank_model.ItemListBean object = (Rank_model.ItemListBean) parent.getAdapter().getItem(position);
        /*if (Hot_drop_Fragment.position==2) {
            urls = object.getData().getWebUrl().getRaw();
        }else{
            urls = object.getData().getWebUrl().getForWeibo();
        }*/
        urls = object.getData().getPlayUrl();
        Intent intent = new Intent(Mic.getmCtx(), ItemActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("video_url", urls);
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
        }
        HistoryViewActivity.historyList.add(0,historyModal);*/
    }
}
