package com.jay.appdemo1.adapter.find;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jay.appdemo1.R;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.modal.HistoryModal;
import com.jay.appdemo1.modal.find.Find_focus_model;
import com.jay.appdemo1.ui.activity.HistoryViewActivity;
import com.jay.appdemo1.ui.activity.ItemActivity;
import com.jay.appdemo1.ui.fragment.listener.ClickListener;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.xutils.BitmapUtil;

import org.w3c.dom.Text;

import java.security.spec.EllipticCurve;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuzhi on 2019/1/4.
 */

public class Find_focus_vp_adapter extends PagerAdapter implements ClickListener{
    private List<Find_focus_model.ItemListBeanX.DataBeanX.ItemListBean> itemList;
    private Context mCtx;
//    private final View view;

    public Find_focus_vp_adapter(List<Find_focus_model.ItemListBeanX.DataBeanX.ItemListBean> itemList, Context mCtx) {

        this.itemList = itemList;
        this.mCtx = mCtx;
//        view = View.inflate(mCtx, R.layout.find_focus_item_sign_title, null);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View view = View.inflate(mCtx, R.layout.find_focus_item_sign_title, null);
        TextView sign = view.findViewById(R.id.find_item_sign);
        TextView title = view.findViewById(R.id.find_item_title);
        ImageView iv = view.findViewById(R.id.find_item_iv);

        BitmapUtil.getInstance().display(iv,itemList.get(position).getData().getCover().getFeed());
        title.setText(itemList.get(position).getData().getTitle());
//        sign.setText("#"+itemList.get(position).getData().getTags().get(0).getName());

        /*ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }*/

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack(position);
            }
        });

        container.addView(view);
        return view;
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
        LOGUtils.Log("viewpagerURL:"+itemList.get(position).getData()/*.getWebUrl().getRaw()*/);
        Find_focus_model.ItemListBeanX.DataBeanX.ItemListBean object = itemList.get(position);
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
