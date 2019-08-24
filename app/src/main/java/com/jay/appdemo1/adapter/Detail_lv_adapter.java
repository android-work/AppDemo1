package com.jay.appdemo1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jay.appdemo1.R;
import com.jay.appdemo1.modal.find.Find_getClass_model;
import com.jay.appdemo1.modal.modalMgr.ModalMgr;
import com.jay.appdemo1.utils.xutils.BitmapUtil;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by liuzhi on 2019/2/13.
 */

public class Detail_lv_adapter extends BaseAdapter {

    private Find_getClass_model.ItemListBeanX itemListBeanX;
    private final List<Find_getClass_model.ItemListBeanX.DataBeanX.ItemListBean> itemList;
    private Context mCtx;

    public Detail_lv_adapter(Find_getClass_model.ItemListBeanX itemListBeanX , Context context){
        this.itemListBeanX = itemListBeanX;
        itemList = itemListBeanX.getData().getItemList();
        mCtx = context;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Find_getClass_model.ItemListBeanX.DataBeanX.ItemListBean getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View detailView = null;
        ViewHolder viewHolder;
        if (convertView==null){

            viewHolder = new ViewHolder();

            convertView = View.inflate(mCtx, R.layout.find_class_activity_lv_item_layout,null);
            viewHolder.tv_des = convertView.findViewById(R.id.find_acitivity_lv_item_des);
            viewHolder.tv_duration = convertView.findViewById(R.id.find_acitivity_lv_item_duration);
            viewHolder.imageView = convertView.findViewById(R.id.find_activity_lv_item_iv);

            int heightPixels = mCtx.getResources().getDisplayMetrics().heightPixels;
            int height = (int) (heightPixels / 2.5);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height);
            viewHolder.imageView.setLayoutParams(layoutParams);

            convertView.setTag(viewHolder);

        }

        detailView = convertView;

        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.tv_des.setText(getItem(position).getData().getTitle());
        int duration = getItem(position).getData().getDuration();
        int mind = duration / 60;
        int second = duration % 60;
        viewHolder.tv_duration.setText("#"+getItem(position).getData().getCategory()+"/"+mind+"'"+second);
        BitmapUtil.getInstance().display(viewHolder.imageView,getItem(position).getData().getCover().getFeed());

        return detailView;
    }


    public static class ViewHolder {
        public ImageView imageView;
        public TextView tv_des;
        public TextView tv_duration;
    }
}
