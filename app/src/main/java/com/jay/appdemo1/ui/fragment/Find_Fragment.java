package com.jay.appdemo1.ui.fragment;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jay.appdemo1.Mic;
import com.jay.appdemo1.adapter.find.Find_viewpager;
import com.jay.appdemo1.modal.find.Find_class_model;
import com.jay.appdemo1.modal.find.Find_focus_model;
import com.jay.appdemo1.modal.find.Find_getClass_model;
import com.jay.appdemo1.modal.modalMgr.ModalMgr;
import com.jay.appdemo1.ui.fragment.BaseFragment;
import com.jay.appdemo1.ui.fragment.find_fragment.Find_class;
import com.jay.appdemo1.ui.fragment.find_fragment.Find_focus;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.LoadData;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import com.jay.appdemo1.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhi on 2018/12/6.
 */

public class Find_Fragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private Handler mHandler;
    private FragmentManager fm;
    private Map<Integer,Fragment> find_fragment_map = new HashMap<>();
    private Fragment fragment;
    private ViewPager find_vp;
    private int i=0;
    private int moveX;
    private int index;
    private int pagerMoveX;
    private ImageView line_lv;
    private TextView find_class_tv;
    private TextView find_focus_tv;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.find_layout, null);
        //找到布局中的viewpager控件
        find_vp = view.findViewById(R.id.find_vp);
        //找到分类/关注控件
        find_focus_tv = view.findViewById(R.id.find_focus_tv);
        find_class_tv = view.findViewById(R.id.find_class_tv);
        //找到偏移量图片
        line_lv = view.findViewById(R.id.find_line_lv);

        //计算偏移图片的宽度，以及屏幕宽度
        int line_width = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_line_bg).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        //把屏幕尺寸信息设置给dm
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        //获取屏幕宽度
        int widthPixels = dm.widthPixels;
        //计算偏移图片的偏移量
        moveX=widthPixels/2;
        LOGUtils.Log(widthPixels+":moveX:"+moveX);

        //初始化fragment
        if (find_fragment_map!=null && find_fragment_map.size()<2){
            Fragment find_focus = new Find_focus();
            Fragment find_class = new Find_class();

            find_fragment_map.put(0,find_focus);
            find_fragment_map.put(1,find_class);

        }

        //设置viewpager的适配器
        /**
         * 此处传getChildFragmentManager对象，而不传getSupportFragmentManager对象，
         * 因为getFragmentManager到的是activity对所包含fragment的Manager，
         * 而如果是fragment嵌套fragment，那么就需要利用getChildFragmentManager()了。
         getFragmentManager()是所在fragment 父容器的碎片管理，
         getChildFragmentManager()是在fragment 里面子容器的碎片管理。
         */
        find_vp.setAdapter(new Find_viewpager(getChildFragmentManager(),find_fragment_map,getContext()));

        //默认设置为0
        find_vp.setCurrentItem(0);

        //设置viewpager的页面监听
        find_vp.setOnPageChangeListener(this);
        //相邻页面的偏移量
        pagerMoveX = moveX*2+line_width;
        LOGUtils.Log("pagerMoveX:"+pagerMoveX);

        find_focus_tv.setTextColor(Color.BLACK);
        find_class_tv.setTextColor(Color.GRAY);
        //设置textveiw的点击事件
        find_class_tv.setOnClickListener(this);
        find_focus_tv.setOnClickListener(this);
        return view;
    }

/**===============================================================加载数据=======================================================================*/

    @Override
    public void loadData(Handler handler) {
        mHandler = handler;

        new LoadData().loadData(handler, Mic.FOCUS_URL);
    }

/**===============================================================解析数据=======================================================================*/

    @Override
    protected void parseData(String data) {

        //解析关注数据
        Find_focus_model find_focus_model = new Gson().fromJson(data, Find_focus_model.class);
        ModalMgr.getInstance().setmFind_focus_model(find_focus_model);

        //下载并解析分类数据
        new HttpUtils().send(HttpRequest.HttpMethod.GET, Mic.CLASS_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                try {
                    JSONArray classArray = new JSONArray(result);

                    for (int i=0;i<classArray.length();i++){
                        Find_class_model find_class_model = new Find_class_model();

                        JSONObject classJson = classArray.getJSONObject(i);
                        find_class_model.id = classJson.getInt("id");
                        find_class_model.name = classJson.getString("name");
                        find_class_model.alias = classJson.getString("alias");
                        find_class_model.description = classJson.getString("description");
                        find_class_model.bgPicture = classJson.getString("bgPicture");
                        find_class_model.bgColor = classJson.getString("bgColor");
                        find_class_model.headerImage = classJson.getString("headerImage");
                        find_class_model.defaultAuthorId = classJson.getInt("defaultAuthorId");

                        ModalMgr.getInstance().getClass_model_list().add(find_class_model);

                        ModalMgr.getInstance().getGetHashMap_title().put(i,find_class_model.name);

                    }

                    for (int i=0;i<ModalMgr.getInstance().getClass_model_list().size();i++){
                        LOGUtils.Log("getClass_model_list:"+ModalMgr.getInstance().getClass_model_list().get(i).name);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }/*finally {
                    mHandler.sendEmptyMessage(2);
                }
*/
            }

            @Override
            public void onFailure(HttpException e, String s) {
                mHandler.sendEmptyMessage(2);
            }
        });

        //请求分类的视频数据
        new HttpUtils().send(HttpRequest.HttpMethod.GET, Mic.GET_CLASS_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Find_getClass_model find_getClass_model = new Gson().fromJson(result, Find_getClass_model.class);

                for (int i=0;i<find_getClass_model.getItemList().size();i++){
                    String title = find_getClass_model.getItemList().get(i).getData().getHeader().getTitle();
                    ModalMgr.getInstance().getGetClass_modelHashMap().put(title,find_getClass_model.getItemList().get(i));//根据title键存放对应的key值
                }

                if (!find_getClass_model.getNextPageUrl().equals("") &&
                        find_getClass_model.getNextPageUrl() !=null){
                    LOGUtils.Log("getNextPageUrl:"+find_getClass_model.getNextPageUrl());
                    getResult(find_getClass_model.getNextPageUrl());
                }

                LOGUtils.Log("getClass_model_HashMap"+ModalMgr.getInstance().getGetClass_modelHashMap());

                mHandler.sendEmptyMessage(2);

            }

            @Override
            public void onFailure(HttpException e, String s) {
                mHandler.sendEmptyMessage(2);
            }
        });

    }

    public void getResult(String url){
        i++;
        //请求分类的视频数据
        new HttpUtils().send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;

                Find_getClass_model find_getClass_model = new Gson().fromJson(result, Find_getClass_model.class);

                for (int i=0;i<find_getClass_model.getItemList().size();i++){
                    String title = find_getClass_model.getItemList().get(i).getData().getHeader().getTitle();
                    ModalMgr.getInstance().getGetClass_modelHashMap().put(title,find_getClass_model.getItemList().get(i));//根据title键存放对应的key值
                }

                if (find_getClass_model.getNextPageUrl() !=null&&
                        !find_getClass_model.getNextPageUrl().equals("") ){
                    LOGUtils.Log("getNextPageUrl:"+find_getClass_model.getNextPageUrl());
                    getResult(find_getClass_model.getNextPageUrl());
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }

/**===============================================================分类/关注的点击事件=======================================================================*/

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.find_class_tv:
                //设置viewpager展示当前对应的页面
                find_vp.setCurrentItem(1);
                find_class_tv.setTextColor(Color.BLACK);
                find_focus_tv.setTextColor(Color.GRAY);
                break;
            case R.id.find_focus_tv:
                find_vp.setCurrentItem(0);
                find_focus_tv.setTextColor(Color.BLACK);
                find_class_tv.setTextColor(Color.GRAY);
                break;
        }
    }

/**===============================================================viewpager页面滚动监听=======================================================================*/

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        //创建一个移动动画
        TranslateAnimation tm = new TranslateAnimation(index * moveX + 0, position * moveX + 0, 0, 0);
        index=position;
        tm.setDuration(300);
        tm.setFillAfter(true);
        line_lv.startAnimation(tm);

        if (position==1){
            find_class_tv.setTextColor(Color.BLACK);
            find_focus_tv.setTextColor(Color.GRAY);
        }else {
            find_focus_tv.setTextColor(Color.BLACK);
            find_class_tv.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
