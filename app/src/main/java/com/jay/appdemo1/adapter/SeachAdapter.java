package com.jay.appdemo1.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.modal.SeachModal;
import com.jay.appdemo1.utils.xutils.BitmapUtil;

import java.util.List;

public class SeachAdapter extends BaseAdapter {
    private static List<SeachModal.ItemListBean> itemList;

    public SeachAdapter(List<SeachModal.ItemListBean> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public SeachModal.ItemListBean getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder ;
        if (convertView == null){
            convertView = View.inflate(Mic.getmCtx(), R.layout.rank_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.rank_des_tv=convertView.findViewById(R.id.rank_des_tv);
            viewHolder.rank_duration_tv=convertView.findViewById(R.id.rank_duration_tv);
            viewHolder.rank_item_iv=convertView.findViewById(R.id.rank_item_iv);
            int height = (int) (Mic.getmCtx().getResources().getDisplayMetrics().heightPixels/2.5);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
            viewHolder.rank_item_iv.setLayoutParams(layoutParams);
            viewHolder.rank_item_iv.setScaleType(ImageView.ScaleType.FIT_XY);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.rank_des_tv.setText(getItem(position).getData().getTitle());
        int duration = getItem(position).getData().getDuration();
        int mines = duration / 60;
        int second = duration % 60;
        viewHolder.rank_duration_tv.setText("#"+getItem(position).getData().getCategory()+"/"+mines+"'"+second);
        BitmapUtil.getInstance().display(viewHolder.rank_item_iv,getItem(position).getData().getCover().getFeed());
        return convertView;
    }

    public static class ViewHolder{
        public ImageView rank_item_iv;
        public TextView rank_des_tv,rank_duration_tv;
    }

    public static void setData(List<SeachModal.ItemListBean> itemLists){
        for (SeachModal.ItemListBean itemListBean : itemLists){
            itemList.add(itemListBean);
        }
    }
}
