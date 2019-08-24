package com.jay.appdemo1.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.modal.HistoryModal;
import com.jay.appdemo1.modal.home.Home_viewpagr_modal;
import com.jay.appdemo1.modal.modalMgr.ModalMgr;
import com.jay.appdemo1.ui.activity.HistoryViewActivity;
import com.jay.appdemo1.ui.activity.ItemActivity;
import com.jay.appdemo1.ui.fragment.listener.ClickListener;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.xutils.BitmapUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by liuzhi on 2018/12/14.
 */

public class Home_listview_vp_adapter extends PagerAdapter implements ClickListener{

    private int size;
    private ArrayList<Home_viewpagr_modal.home1.home2> viewPaList;
    private Context mCtx;
    private int count=0;

    public Home_listview_vp_adapter(Context context){
        mCtx = context;
        viewPaList=ModalMgr.getInstance().getHome_modal().getIssueList().get(0).getItemList();
        for (int i=0;!(viewPaList.get(i).getType().equals("video"));i++){
            LOGUtils.Log("head:"+viewPaList.get(i).getType());
            count++;
        }

        size=viewPaList.size()-count;
        LOGUtils.Log("size:"+size);

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        position%=size;

        ImageView vp_iv=new ImageView(mCtx);

        //设置viewpager的页面点击事件
        final int finalPosition = position;
        vp_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LOGUtils.Log("finalPosition:"+finalPosition);
                callBack(finalPosition);
            }
        });

        vp_iv.setScaleType(ImageView.ScaleType.FIT_XY);
        BitmapUtil.getInstance().display(vp_iv,viewPaList.get(position+count).getData().getCover().getFeed());
        container.addView(vp_iv);

        return vp_iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void callBack(int position) {
        Intent intent = new Intent(mCtx, ItemActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("position",position);
        LOGUtils.Log("viewpagerURL:"+viewPaList.get(position+count).getData());
        Home_viewpagr_modal.home1.home2 object = viewPaList.get(position + count);
        intent.putExtra("video_url",object.getData().getPlayUrl());
        mCtx.startActivity(intent);

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
    }
}
