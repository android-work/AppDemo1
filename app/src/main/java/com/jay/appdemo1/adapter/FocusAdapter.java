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
import java.util.HashMap;
import java.util.Set;

public class FocusAdapter extends RecyclerView.Adapter<FocusAdapter.viewHolder> implements View.OnClickListener {


    private static RVListener rvListener;
    private HashMap<String, Focus_model> focus_models;
    public static ArrayList<Focus_model> focus_list = new ArrayList<>();

    public FocusAdapter(HashMap<String, Focus_model> focus_models) {

        this.focus_models = focus_models;
        for (String key:focus_models.keySet()){

            Focus_model focus_model = focus_models.get(key);
            focus_list.add(focus_model);

        }
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(Mic.getmCtx(), R.layout.focus_item_layout, null);
        view.setOnClickListener(this);
        return new viewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        BitmapUtil.getInstance().display(holder.focus_icon,focus_list.get(position).getHead());
        holder.focus_name.setText(focus_list.get(position).getEditor());
        holder.focus_des.setText(focus_list.get(position).getDes());
        LOGUtils.Log(position+"name:"+focus_list.get(position).getEditor()+"-------des:"+focus_list.get(position).getDes());
    }


    @Override
    public int getItemCount() {
        return focus_models.size();
    }

    /**=====================================================布局的点击事件==================================================*/
    @Override
    public void onClick(View view) {
        rvListener.onItemClick(view);
    }

    public static void setItemOnClickListener(RVListener rvListener){

        FocusAdapter.rvListener = rvListener;
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        private ImageView focus_icon;
        private TextView focus_name;
        private TextView focus_des;

        public viewHolder(View itemView) {
            super(itemView);
            focus_icon = itemView.findViewById(R.id.focus_icon);
            focus_name = itemView.findViewById(R.id.focus_name);
            focus_des = itemView.findViewById(R.id.focus_des);
        }
    }
}
