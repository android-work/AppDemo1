package com.jay.appdemo1.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.modal.HistoryModal;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.xutils.BitmapUtil;

import java.util.ArrayList;

public class HistoryViewAdapter extends BaseAdapter {
    private ArrayList<HistoryModal> historyList;

    //TODO 缓存到本地，处理重复缓存，listview的重用机制要考虑

    public HistoryViewAdapter(ArrayList<HistoryModal> historyList) {

        this.historyList = historyList;
    }

    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public HistoryModal getItem(int i) {
        return historyList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            view = View.inflate(Mic.getmCtx(), R.layout.history_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.history_item_rl = view.findViewById(R.id.history_item_rl);

            /*int widthPixels = Mic.getmCtx().getResources().getDisplayMetrics().widthPixels*(2/5);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, widthPixels / 2);
            viewHolder.history_item_rl.setLayoutParams(layoutParams);*/

            viewHolder.history_item_catergory = view.findViewById(R.id.history_item_category);
            viewHolder.history_item_iv = view.findViewById(R.id.history_item_iv);
            viewHolder.history_item_title = view.findViewById(R.id.history_item_title);
            view.setTag(viewHolder);



        }

        LOGUtils.Log(getItem(i).getCategory()+";;"+getItem(i).getTitle()+":::"+getItem(i).getHomepage());
        viewHolder = (ViewHolder) view.getTag();
        viewHolder.history_item_catergory.setText(getItem(i).getCategory());
        viewHolder.history_item_title.setText(getItem(i).getTitle());
        BitmapUtil.getInstance().display(viewHolder.history_item_iv,getItem(i).getHomepage());
        return view;
    }

    public static class ViewHolder{
        public ImageView history_item_iv;
        public TextView history_item_catergory;
        public TextView history_item_title;
        public LinearLayout history_item_rl;
    }
}
