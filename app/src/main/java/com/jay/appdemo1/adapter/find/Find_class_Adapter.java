package com.jay.appdemo1.adapter.find;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jay.appdemo1.R;
import com.jay.appdemo1.modal.find.Find_class_model;
import com.jay.appdemo1.modal.find.Find_getClass_model;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.xutils.BitmapUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by liuzhi on 2019/1/5.
 */

public class Find_class_Adapter extends BaseAdapter {

    private ArrayList<Find_class_model> class_model_list;
    private Context mCtx;


    public Find_class_Adapter(ArrayList<Find_class_model> class_model_list, Context applicationContext) {

        this.class_model_list = class_model_list;
        mCtx = applicationContext;

    }
    @Override
    public int getCount() {
        return class_model_list.size();
    }

    @Override
    public Find_class_model getItem(int i) {
        return class_model_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HoldView holdView=null;
        View gridView;
        if (view==null){

            holdView=new HoldView();
            view = View.inflate(mCtx, R.layout.find_class_gv_item_layout,null);
            holdView.gv_iv = view.findViewById(R.id.find_class_gv_iv);
            holdView.gv_name = view.findViewById(R.id.find_class_gv_name);

            //设置图片大小
            int widthPixels = mCtx.getResources().getDisplayMetrics().widthPixels;
            int width = widthPixels / 2;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, width);
            holdView.gv_iv.setLayoutParams(layoutParams);

            view.setTag(holdView);
        }
        gridView=view;
        holdView = (HoldView) view.getTag();

        BitmapUtil.getInstance().display(holdView.gv_iv,class_model_list.get(i).bgPicture);
        holdView.gv_name.setText("#"+class_model_list.get(i).name);

        LOGUtils.Log("gv_name:"+class_model_list.get(i).name);

        return gridView;
    }

    public static class HoldView {
        public ImageView gv_iv;
        public TextView gv_name;
    }
}
