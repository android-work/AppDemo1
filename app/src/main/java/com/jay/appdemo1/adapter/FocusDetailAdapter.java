package com.jay.appdemo1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.modal.find.Focus_model;
import com.jay.appdemo1.ui.fragment.listener.RVListener;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.xutils.BitmapUtil;

import java.util.ArrayList;

public class FocusDetailAdapter extends RecyclerView.Adapter<FocusDetailAdapter.FocusviewHolder> implements View.OnClickListener {
    private static RVListener rvListener;
    private ArrayList<Focus_model> focus_models;

    public FocusDetailAdapter(ArrayList<Focus_model> focus_models) {

        this.focus_models = focus_models;
        LOGUtils.Log("---------focus_models:"+focus_models);
    }

    @Override
    public FocusviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = View.inflate(Mic.getmCtx(), R.layout.focus_detail_item_layout, null);
        inflate.setOnClickListener(this);
        return new FocusviewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(FocusviewHolder holder, int position) {
        BitmapUtil.getInstance().display(holder.icon,focus_models.get(position).getHead());
        holder.name.setText(focus_models.get(position).getEditor());
        holder.des.setText(focus_models.get(position).getDes());
        holder.title.setText(focus_models.get(position).getTitle());
        holder.category.setText(focus_models.get(position).getCatgory());
        BitmapUtil.getInstance().display(holder.icon,focus_models.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return focus_models.size();
    }

    @Override
    public void onClick(View view) {
        rvListener.onItemClick(view);
    }

    public static void setOnItemClick(RVListener rvListener){

        FocusDetailAdapter.rvListener = rvListener;
    }

    public class FocusviewHolder extends RecyclerView.ViewHolder{

        private TextView btn;
        private ImageView head;
        private TextView title;
        private TextView name;
        private TextView category;
        private TextView des;
        private ImageView icon;


        public FocusviewHolder(View itemView) {
            super(itemView);

            btn = itemView.findViewById(R.id.focus_detail_item_btn);
            head = itemView.findViewById(R.id.focus_detail_item_head);
            name = itemView.findViewById(R.id.focus_detail_item_name);
            des = itemView.findViewById(R.id.focus_detail_item_des);
            title = itemView.findViewById(R.id.focus_detail_item_title);
            category = itemView.findViewById(R.id.focus_detail_item_category);
            icon = itemView.findViewById(R.id.focus_detail_item_icon);
        }
    }
}
