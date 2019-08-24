package com.jay.appdemo1.ui.fragment.find_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.jay.appdemo1.R;
import com.jay.appdemo1.adapter.find.Find_class_Adapter;
import com.jay.appdemo1.modal.find.Find_getClass_model;
import com.jay.appdemo1.modal.home.Home_listview_modal;
import com.jay.appdemo1.modal.modalMgr.ModalMgr;
import com.jay.appdemo1.ui.activity.FindClassActivity;
import com.jay.appdemo1.ui.activity.ItemActivity;
import com.jay.appdemo1.ui.fragment.listener.Click_Gridview_Listener;
import com.jay.appdemo1.utils.LOGUtils;

import java.util.List;

/**
 * Created by liuzhi on 2019/1/2.
 */

public class Find_class extends Fragment implements AdapterView.OnItemClickListener {

    private GridView find_class_gv;
    private Find_getClass_model.ItemListBeanX itemListBeanX;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_class_layout, container, false);

        LOGUtils.Log("执行class fragment"+ModalMgr.getInstance().getClass_model_list().size());

        //找到GridView控件
        find_class_gv = view.findViewById(R.id.find_class_gv);

        //设置适配器
        Find_class_Adapter find_class_adapter  = new Find_class_Adapter(ModalMgr.getInstance().getClass_model_list(), getContext());
        find_class_gv.setAdapter(find_class_adapter);

        //设置gridview的条目点击事件监听
        find_class_gv.setOnItemClickListener(this);

        return view;
    }

    /**=================================================GridView条目点击事件============================================================*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), FindClassActivity.class);
        intent.putExtra("position",position);

        String title = ModalMgr.getInstance().getGetHashMap_title().get(position);
        itemListBeanX = ModalMgr.getInstance().getGetClass_modelHashMap().get(title);
        FindClassActivity.setClick_Gridview_Listener(new Click_Gridview_Listener() {
            @Override
            public Find_getClass_model.ItemListBeanX getObj() {
                return itemListBeanX;
            }

        });

        getContext().startActivity(intent);

    }
}
