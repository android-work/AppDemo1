package com.jay.appdemo1.adapter;

import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.modal.PreviewModel;
import com.jay.appdemo1.utils.xutils.BitmapUtil;

import java.util.ArrayList;

public class DraftAdapter extends BaseAdapter {

    private ArrayList<PreviewModel> draft;

    public DraftAdapter(ArrayList<PreviewModel> draft){
        this.draft = draft;
    }
    @Override
    public int getCount() {
        return draft.size();
    }

    @Override
    public PreviewModel getItem(int i) {
        return draft.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view==null){
            view = View.inflate(Mic.getmCtx(),R.layout.preview_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.preview_iv = view.findViewById(R.id.preview_item_iv);
            viewHolder.preview_tv = view.findViewById(R.id.preview_item_tv);

            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();
        String title = getItem(i).getTitle();
        if (title==null || title.equals("")) {
            viewHolder.preview_tv.setText("标题为空");
        }else{
            viewHolder.preview_tv.setText(title);
        }
        BitmapUtil.getInstance().display(viewHolder.preview_iv,getItem(i).getPhoto());
        return view;
    }

    public static class ViewHolder{
        private ImageView preview_iv;
        private TextView preview_tv;
    }
}
