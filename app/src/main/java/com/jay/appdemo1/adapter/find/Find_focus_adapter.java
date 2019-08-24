package com.jay.appdemo1.adapter.find;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.jay.appdemo1.R;
import com.jay.appdemo1.interface_.NotifyListener;
import com.jay.appdemo1.modal.find.Find_focus_model;
import com.jay.appdemo1.ui.view.CricleImageView;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.ToastUtils;
import com.jay.appdemo1.utils.xutils.BitmapUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuzhi on 2019/1/3.
 */

public class Find_focus_adapter extends BaseAdapter implements ViewPager.OnPageChangeListener {
    private static NotifyListener notifyListener;
    private ArrayList<Find_focus_model.ItemListBeanX> itemListBeanX;
    private Context mCtx;
    private ViewHolder viewHolder=null;
    private int position;
    private boolean isFocus = true;
    private ItemBtnLintener itemBtnLintener;
    public static HashMap<Integer,View> viewHashMap = new HashMap<>();

    public Find_focus_adapter(ArrayList<Find_focus_model.ItemListBeanX> itemListBeanX, FragmentActivity activity) {
        this.itemListBeanX = itemListBeanX;
        mCtx = activity.getApplicationContext();
    }

    @Override
    public int getCount() {
        return itemListBeanX.size();
    }

    @Override
    public Find_focus_model.ItemListBeanX getItem(int i) {
        return itemListBeanX.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        position=i;
        View itemView=null;
        if (view==null){
            view = View.inflate(mCtx, R.layout.find_focus_item_layout, null);

            viewHolder= new ViewHolder();

            viewHolder.find_item_icon=view.findViewById(R.id.find_item_icon);
            viewHolder.find_item_name=view.findViewById(R.id.find_item_name);
            viewHolder.find_item_des=view.findViewById(R.id.find_item_des);
            viewHolder.find_focus_tv_btn=view.findViewById(R.id.find_focus_tv_btn);
            viewHolder.find_focus_vp=view.findViewById(R.id.find_focus_vp);

            viewHolder.find_focus_vp.setOnPageChangeListener(this);
                        view.setTag(viewHolder);
        }

        itemView=view;
        viewHolder = (ViewHolder) view.getTag();

        //获取集合中的对象，设置初始字段
        Find_focus_model.ItemListBeanX item = getItem(i);
        int textState = item.getText();
        if (textState==0){
            //显示关注
            viewHolder.find_focus_tv_btn.setText("+ 关注");
        }else{
            //显示取消关注
            viewHolder.find_focus_tv_btn.setText("取消关注");
        }

        BitmapUtil.getInstance().display(viewHolder.find_item_icon,itemListBeanX.get(i).getData().getHeader().getIcon());//头像
        viewHolder.find_item_name.setText(itemListBeanX.get(i).getData().getHeader().getTitle());//名称
        viewHolder.find_item_des.setText(itemListBeanX.get(i).getData().getHeader().getDescription());//描述

        LOGUtils.Log("描述:"+itemListBeanX.get(i).getData().getHeader().getDescription());

        //让每一个条目的按钮都有自己的标记
        viewHolder.find_focus_tv_btn.setTag(R.id.find_focus_tv_btn,i);
        viewHolder.find_focus_tv_btn.setOnClickListener(new ItemBtnLintener(i));
        viewHolder.find_focus_vp.setAdapter(new Find_focus_vp_adapter(itemListBeanX.get(i).getData().getItemList(),mCtx));


        return itemView;
    }

/**=================================================条目关注按钮的点击事件============================================================*/

    private class ItemBtnLintener implements View.OnClickListener {

    private int position;

    public ItemBtnLintener(int position){

        this.position = position;
    }

        @Override
        public void onClick(View view) {
            int i = (int) viewHolder.find_focus_tv_btn.getTag(R.id.find_focus_tv_btn);
            LOGUtils.Log("i="+i+",position="+position);
            notifyListener.onClick(position,i);



        }


    }

    /**==================================================自定义listview条目的点击事件监听=====================================*/

    public static void setNotifyListener(NotifyListener notifyListener){

        Find_focus_adapter.notifyListener = notifyListener;
    }

    /**=================================================ViewHolder============================================================*/

    public static class ViewHolder {

        public CricleImageView find_item_icon;
        public TextView find_item_name;
        public TextView find_item_des;
        public TextView find_focus_tv_btn;
        public ViewPager find_focus_vp;
    }

/**=================================================Viewpager页面监听============================================================*/

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

/**=================================================更新listview的数据集合============================================================
 * @param list*/

    public void setLVlist(List<Find_focus_model.ItemListBeanX> list){

        if (list!=null && list.size()>0){
            itemListBeanX.addAll(list);
        }
    }
}
