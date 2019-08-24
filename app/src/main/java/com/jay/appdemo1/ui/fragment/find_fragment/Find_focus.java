package com.jay.appdemo1.ui.fragment.find_fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jay.appdemo1.R;
import com.jay.appdemo1.adapter.find.Find_class_Adapter;
import com.jay.appdemo1.adapter.find.Find_focus_adapter;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.interface_.NotifyListener;
import com.jay.appdemo1.modal.find.Find_focus_model;
import com.jay.appdemo1.modal.modalMgr.ModalMgr;
import com.jay.appdemo1.ui.view.ConsumeList;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.ToastUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuzhi on 2019/1/2.
 */

public class Find_focus extends Fragment implements AdapterView.OnItemClickListener {

    private ArrayList<Find_focus_model.ItemListBeanX> itemListBeanX;
    private Handler mHandler = new Handler();
    private ConsumeList find_focus_lv;
    private Find_focus_adapter find_focus_adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //加载focus的页面
        final View view = inflater.inflate(R.layout.find_focus_layout, container, false);
        //找到listview
        find_focus_lv = view.findViewById(R.id.find_focus_lv);

        //设置Listview的条目点击事件
        find_focus_lv.setOnItemClickListener(this);

        //初始化数据集合
        itemListBeanX = (ArrayList<Find_focus_model.ItemListBeanX>) ModalMgr.getInstance().getmFind_focus_model().getItemList();
        //设置listview的适配器
        find_focus_adapter = new Find_focus_adapter(itemListBeanX, getActivity());
        find_focus_lv.setAdapter(find_focus_adapter);

        //listview条目按钮的点击事件监听
        Find_focus_adapter.setNotifyListener(new NotifyListener() {
            @Override
            public void onClick(int position,int i) {
//                if (position == i){
                //关注之前需要检查用户是否登录
                HashMap<String, String> user_map = DbOperation.getInstance().dbQuery_user();
                if (user_map==null || user_map.size()==0 ||user_map.get("uid")==null || user_map.get("uid").equals("")) {
                    ToastUtils.show("请用户登录");
                    return;
                }
                //判断当前按钮的状态
                if (find_focus_adapter.getItem(position).getText()==0){
                    find_focus_adapter.getItem(position).setText(1);
                    ToastUtils.show(":关注成功");
                    //将关注的信息添加到,uid head editor icon title categroy
                    Find_focus_model.ItemListBeanX itemListBeanx = itemListBeanX.get(position);
                    Find_focus_model.ItemListBeanX.DataBeanX.HeaderBean header = itemListBeanx.getData().getHeader();
                    String icon = header.getIcon();
                    String name = header.getTitle();
                    String description = header.getDescription();
                    List<Find_focus_model.ItemListBeanX.DataBeanX.ItemListBean> itemList = itemListBeanx.getData().getItemList();
                    for (Find_focus_model.ItemListBeanX.DataBeanX.ItemListBean bean : itemList){
                        String feed = bean.getData().getCover().getFeed();//缩略图
                        int duration = bean.getData().getDuration();
                        String category = bean.getData().getCategory()+"/0"+duration/60+"'"+duration%60;
                        String title = bean.getData().getTitle();
                        String playUrl = bean.getData().getPlayUrl();

                        LOGUtils.Log("icon:"+icon+"-------name:"+name+"-------------des:"+description+"---------title:"+title+"----------feed:"+feed+"-----------category:"+category+"------playurl:"+playUrl);
                        DbOperation.getInstance().dbinsert_focus("",icon,name,feed,description,title,category,playUrl);
                    }
                }else if (find_focus_adapter.getItem(position).getText()==1){
                    find_focus_adapter.getItem(position).setText(0);
                    ToastUtils.show("取消关注");
                    //从数据库中移除数据
                    Find_focus_model.ItemListBeanX itemListBeanx = itemListBeanX.get(position);
                    Find_focus_model.ItemListBeanX.DataBeanX.HeaderBean header = itemListBeanx.getData().getHeader();
                    String name = header.getTitle();
                    DbOperation.getInstance().dbDelect_foces_info(name);

                }
                //通知适配器进行刷新界面
                find_focus_adapter.notifyDataSetChanged();

            }
        });

        //设置 上拉加载更多/下拉刷新 监听回调
        find_focus_lv.setOnRefreshListener(new ConsumeList.OnHandlerListener() {
            @Override
            public void loadMore() {

                String nextPageUrl = ModalMgr.getInstance().getmFind_focus_model().getNextPageUrl();

                LOGUtils.Log("nextPagerUrl:"+nextPageUrl);

                if (nextPageUrl==null || nextPageUrl.equals("")){
                    Toast.makeText(getContext(),"没有更多数据",Toast.LENGTH_LONG).show();
                    find_focus_lv.refreshFinish(0);
                    return;
                }

                new HttpUtils().send(HttpRequest.HttpMethod.GET, nextPageUrl, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result;
                        Find_focus_model find_focus_model = new Gson().fromJson(result, Find_focus_model.class);
                        ModalMgr.getInstance().setmFind_focus_model(find_focus_model);

                        List<Find_focus_model.ItemListBeanX> itemList = find_focus_model.getItemList();
                        find_focus_adapter.setLVlist(itemList);
                        find_focus_lv.refreshFinish(0);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(getContext(),"没有更多数据",Toast.LENGTH_LONG).show();
                    }
                });
            }

        });


        return view;
    }
/**===================================================Listview条目的点击事件的回调监听================================================*/
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
