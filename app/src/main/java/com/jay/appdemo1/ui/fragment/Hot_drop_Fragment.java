package com.jay.appdemo1.ui.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.google.gson.Gson;
import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.adapter.hot.Hot_Adapter;
import com.jay.appdemo1.modal.Hot_model;
import com.jay.appdemo1.modal.modalMgr.ModalMgr;
import com.jay.appdemo1.ui.fragment.hot_fragment.Rank_Fragment;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.LoadData;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuzhi on 2018/12/6.
 */

public class Hot_drop_Fragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    private Handler mHandler;
    private TabLayout hot_tab;
    private ViewPager hot_vp;
    private View hot_line;
    private HashMap<Integer,Rank_Fragment> fragmentHashMap = new HashMap<>();
    public static List<Hot_model.TabInfoBean.TabListBean> tabList;
    public static int position;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.hot_layout, null);
        hot_tab = view.findViewById(R.id.hot_tab);
        hot_vp = view.findViewById(R.id.hot_vp);
        hot_line = view.findViewById(R.id.hot_line);

        hot_tab.setSelectedTabIndicatorColor(Color.BLACK);

        //将textview控件放置到linearLayout中
        Hot_model hot_model = ModalMgr.getInstance().getmHot_model();
        tabList = hot_model.getTabInfo().getTabList();
        for (int i = 0; i< tabList.size(); i++){
            Rank_Fragment rank_fragment = Rank_Fragment.newInstance(tabList.get(i).getApiUrl(), tabList.get(i).getName());
            hot_tab.addTab(hot_tab.newTab().setText(tabList.get(i).getName()));
            fragmentHashMap.put(i,rank_fragment);
        }

        //给viewpager添加fragment内容
        hot_vp.setAdapter(new Hot_Adapter(getChildFragmentManager(),fragmentHashMap));

        /**将tablayout与viewpager之间进行绑定*/
        hot_tab.setupWithViewPager(hot_vp);
        hot_vp.setCurrentItem(0);
        /**tablayout添加点击事件监听*/

        /**viewpager页面滚动事件监听*/
        hot_vp.setOnPageChangeListener(this);
        return view;
    }

    @Override
    public void loadData(Handler handler) {
        mHandler = handler;

        new LoadData().loadData(handler, Mic.RANK_URL);
    }

    @Override
    protected void parseData(String data) {
        Hot_model hot_model = new Gson().fromJson(data, Hot_model.class);
        ModalMgr.getInstance().setmHot_model(hot_model);
        mHandler.sendEmptyMessage(2);

    }

/**==================================================================tablayout选中监听的回调方法==========================================================*/


/**==================================================================tablayout选中监听的回调方法==========================================================*/
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        this.position = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
