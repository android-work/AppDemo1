package com.jay.appdemo1.adapter.hot;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jay.appdemo1.R;
import com.jay.appdemo1.modal.home.Rank_model;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.xutils.BitmapUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by liuzhi on 2019/2/16.
 */

public class Rank_Adapter extends BaseAdapter {
    private List<Rank_model.ItemListBean> itemList;
    private Context context;

    public Rank_Adapter(List<Rank_model.ItemListBean> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
        LOGUtils.Log("进入Rank_Fragment中"+itemList.size());
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Rank_model.ItemListBean getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView;
        ViewHolder viewHolder ;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.rank_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.rank_des_tv=convertView.findViewById(R.id.rank_des_tv);
            viewHolder.rank_duration_tv=convertView.findViewById(R.id.rank_duration_tv);
            viewHolder.rank_item_iv=convertView.findViewById(R.id.rank_item_iv);
            int height = (int) (context.getResources().getDisplayMetrics().heightPixels/2.5);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
            viewHolder.rank_item_iv.setLayoutParams(layoutParams);
            viewHolder.rank_item_iv.setScaleType(ImageView.ScaleType.FIT_XY);
            convertView.setTag(viewHolder);
        }
        itemView = convertView;
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.rank_des_tv.setText(getItem(position).getData().getTitle());
        int duration = getItem(position).getData().getDuration();
        int mines = duration / 60;
        int second = duration % 60;
        viewHolder.rank_duration_tv.setText("#"+getItem(position).getData().getCategory()+"/"+mines+"'"+second);
        BitmapUtil.getInstance().display(viewHolder.rank_item_iv,getItem(position).getData().getCover().getFeed());
        return itemView;
    }

    public static class ViewHolder{
        public ImageView rank_item_iv;
        public TextView rank_des_tv,rank_duration_tv;
    }
}
