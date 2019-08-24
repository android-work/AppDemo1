package com.jay.appdemo1.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.adapter.home.Home_fragment_adapter;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.modal.HistoryModal;
import com.jay.appdemo1.modal.home.Home_viewpagr_modal;
import com.jay.appdemo1.modal.home.Home_listview_modal;
import com.jay.appdemo1.modal.modalMgr.ModalMgr;
import com.jay.appdemo1.ui.activity.HistoryViewActivity;
import com.jay.appdemo1.ui.activity.ItemActivity;
import com.jay.appdemo1.ui.activity.SeachActivity;
import com.jay.appdemo1.ui.view.ConsumeList;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.LoadData;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by liuzhi on 2018/12/6.
 */

public class Home_Fragment extends BaseFragment implements AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener/*, Animator.AnimatorListener*/, Animation.AnimationListener {


    private final int UP_UI = 2;
    private Handler mHandler;
    private ArrayList<Home_viewpagr_modal.home1.home2> home2List;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    private TextView susupend_tv;
    private Home_fragment_adapter home_fragment_adapter;
    private ArrayList<Home_listview_modal.home1_listview.home2_listview> nextList;
    private ConsumeList home_listview;
    private static int size=0;
    private ImageView seach_iv;
    private SwipeRefreshLayout home_swip;
    private RelativeLayout home_rl;
    private RelativeLayout home_fl;
    private RotateAnimation ra;
    private ScaleAnimation sa;
    private static int who = 0;
    private AnimatorSet animationSet;
    private ImageView seach_iv_am;
    private int harf_width;
    private int harf_height;
    private int width;
    private RelativeLayout.LayoutParams lp;


    public Home_Fragment(){
        super();
        LOGUtils.Log("Home_Fragment");
    }

    /**加载首页的布局，是从父类种调用的*/
    @Override
    public View initView() {
        int count=0;
        home2List=ModalMgr.getInstance().getHome_modal().getIssueList().get(0).getItemList();
        LOGUtils.Log("加载子fragment布局");

        //加载homefragment的布局
        View view = View.inflate(context, R.layout.home_layout,null);
        //找到listview的控件
        home_listview = view.findViewById(R.id.home_lv);
        //常驻悬浮框控件
        susupend_tv = view.findViewById(R.id.suspend_tv);
        //seach图标
        seach_iv = view.findViewById(R.id.seach_iv);
        seach_iv_am = view.findViewById(R.id.seach_iv_am);
        //找到官方下拉刷新控件
        home_swip = view.findViewById(R.id.home_swip);
        //找到次父布局
        home_rl = view.findViewById(R.id.home_rl);
        //找到根布局
        home_fl = view.findViewById(R.id.home_fl);

        //设置官方下拉刷新
        home_swip.setColorSchemeColors(Color.RED);//设置箭头的颜色
        home_swip.setBackgroundColor(Color.TRANSPARENT);


        //给listview设置适配器
        home_fragment_adapter = new Home_fragment_adapter(handler);
        home_listview.setAdapter(home_fragment_adapter);

        //设置listview的点击事件
        home_listview.setOnItemClickListener(this);

        seach_iv.setOnClickListener(this);

        //设置listview的滚动监听
        home_listview.setOnScrollListener(this);
        //listview的刷新监听
        home_swip.setOnRefreshListener(this);
        home_listview.setOnRefreshListener(new ConsumeList.OnHandlerListener() {
            @Override
            public void loadMore() {
                LOGUtils.Log("home_fragment_adapter.getCount():"+home_fragment_adapter.getCount());
                size=home_fragment_adapter.getCount()-1;
                home_listview.post(new Runnable() {
                    @Override
                    public void run() {
                        //进行请求数据
                        String nextPageUrl = ModalMgr.getInstance().getmHome_listview_modal().getNextPageUrl();
                        LOGUtils.Log("nextPageUrl:"+nextPageUrl);

                        if (nextPageUrl==null || nextPageUrl.equals("")){
                            Toast.makeText(getContext(),"没有更多数据",Toast.LENGTH_LONG).show();
                            home_listview.refreshFinish(0);
                            return;
                        }

                        HttpUtils httpUtils = new HttpUtils();
                        httpUtils.send(HttpRequest.HttpMethod.GET, nextPageUrl, new RequestCallBack<String>() {
                            @Override
                            public void onSuccess(ResponseInfo<String> responseInfo) {
                                String nextData = responseInfo.result;

                                try {
                                    FileWriter fileWriter = new FileWriter(Mic.getmCtx().getFilesDir().getAbsolutePath() + "/s1.txt");
                                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                                    bufferedWriter.write(nextData,0,nextData.length());

                                    bufferedWriter.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                nextList = parseNextData(nextData);
                                LOGUtils.Log("nextList:"+ nextList);

                                home_fragment_adapter.setArrayList(nextList);
                                home_listview.setSelection(size);
//                                home_fragment_adapter.notifyDataSetChanged();
                                home_listview.refreshFinish(0);

                            }

                            @Override
                            public void onFailure(HttpException e, String s) {
                                Toast.makeText(getContext(),"没有更多数据",Toast.LENGTH_LONG).show();
                                home_listview.refreshFinish(0);
                            }
                        });
                    }
                });
            }
        });
        return view;
    }

    /**加载数据*/
    @Override
    public void loadData(Handler handler) {

        mHandler = handler;

        //加载数据
        new LoadData().loadData(handler,Mic.HOME_URL);

    }

    /**解析下一页的json数据*/
    public ArrayList<Home_listview_modal.home1_listview.home2_listview> parseNextData(String data){

        ArrayList<Home_listview_modal.home1_listview.home2_listview> nextList;
        ModalMgr.getInstance().setmHome_listview_modal(new Gson().fromJson(data, Home_listview_modal.class));
        nextList = ModalMgr.getInstance().getmHome_listview_modal().getIssueList().get(0).getItemList();
        return nextList;
    }

    /**解析json数据*/
    @Override
    protected void parseData(String data) {

        LOGUtils.Log("解析数据");

        //Gson解析数据
        Home_viewpagr_modal mHome_viewpager_modal = new Gson().fromJson(data, Home_viewpagr_modal.class);
        ModalMgr.getInstance().setHome_modal(mHome_viewpager_modal);

        //获取listview的数据,包括下一页的数据
        String nextPageUrl = mHome_viewpager_modal.getNextPageUrl();

        //请求网络，获取listview的内容
        new HttpUtils().send(HttpRequest.HttpMethod.GET, nextPageUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                try {
                    FileWriter fileWriter = new FileWriter(Mic.getmCtx().getFilesDir().getAbsolutePath() + "/s.txt");
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(result,0,result.length());
                    bufferedWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Home_listview_modal home_listview_modal = new Gson().fromJson(result, Home_listview_modal.class);
                ModalMgr.getInstance().setmHome_listview_modal(home_listview_modal);

                Message msg = Message.obtain();
                msg.what=UP_UI;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

/**=================================listview的滚动监听回调====================================*/

    /**listview滚动状态改变回调*/
    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {


    }

    /**listview滚动监听回调*/
    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        if (firstVisibleItem==1 || firstVisibleItem==0){
            LOGUtils.Log("listview的滚动监听设置search图片白色");
            seach_iv.setImageResource(R.mipmap.ic_search_white);
            susupend_tv.setVisibility(View.INVISIBLE);
        }else {
            LOGUtils.Log("当前可见条目:" + firstVisibleItem);
            seach_iv.setImageResource(R.mipmap.ic_search_black);
            susupend_tv.setVisibility(View.VISIBLE);
            if (home_fragment_adapter.getItemViewType(firstVisibleItem-1) == 1) {
                susupend_tv.setText(home_fragment_adapter.getItem(firstVisibleItem-1).getData().getText());
            }
        }

    }

/**================================listview的条目点击事件======================================*/

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        if (adapterView.getAdapter().getItemViewType(position)==1){
            return;
        }
        Intent intent = new Intent(getContext(), ItemActivity.class);
        intent.putExtra("position",position);
        Home_listview_modal.home1_listview.home2_listview object = (Home_listview_modal.home1_listview.home2_listview) adapterView.getAdapter().getItem(position);
        intent.putExtra("video_url",object.getData().getPlayUrl());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        HistoryModal historyModal = new HistoryModal();

        String forWeibo = object.getData().getPlayUrl();
        if (forWeibo==null || forWeibo.equals("")){
            forWeibo = object.getData().getWebUrl().getRaw();
        }

        String homepage = (String) object.getData().getCover().getHomepage();
        if (homepage == null || homepage.equals("")){
            homepage = object.getData().getCover().getFeed();
        }

        int duration = object.getData().getDuration();
        int min = duration/60;
        int second = duration % 60;
        String category = "#" + object.getData().getCategory() + "/" + min + "'" + second + "'";
        HashMap<String, HistoryModal> viewHistory_map = DbOperation.getInstance().dbQuery_viewHistory();
        for (String key:viewHistory_map.keySet()){
            if (key.equals(object.getData().getTitle())){
                DbOperation.getInstance().dbDelect_viewHistory(key);
                DbOperation.getInstance().dbInsert_viewHistory("",forWeibo,object.getData().getTitle(),category,homepage);
                return;
            }else{
                continue;
            }
        }
        DbOperation.getInstance().dbInsert_viewHistory("",forWeibo,object.getData().getTitle(),category,homepage);

        //记录本次点击的视频链接,如果本地有缓存过的话，就删除原来的数据重新添加
        /*for (int i=0;i<HistoryViewActivity.historyList.size();i++){
            if (HistoryViewActivity.historyList!=null &&
                    HistoryViewActivity.historyList.get(i).getTitle().equals(historyModal.getTitle())){
                LOGUtils.Log("存在相同条目");
                HistoryViewActivity.historyList.remove(i);
            }
        }*/

        LOGUtils.Log("不存在相同条目"+historyModal);
//        HistoryViewActivity.historyList.add(0,historyModal);
    }

/**================================官方下拉刷新回调方法======================================*/

    @Override
    public void onRefresh() {

        home_swip.postDelayed(new Runnable() {
            @Override
            public void run() {
                home_swip.setRefreshing(false);
                Toast.makeText(getContext(),"没有新数据刷新",Toast.LENGTH_LONG).show();
            }
        },2000);
    }

/**================================搜索图标的点击事件======================================*/

    @Override
    public void onClick(View view) {
        super.onClick(view);

        /**动画的实现过程
         * 1、将图标切换成白色圆形图片，改变布局在背景为白色
         * 2、执行图标在旋转动画
         * 3、当旋转动画结束后，将图标切换成灰色，并执行放大动画
         * 4、当放大动画结束后，开启搜索页面，显示搜索布局*/
        if (view.getId()==R.id.seach_iv){
            //1、将图标切换成白色圆形图片，改变布局在背景为白色
            seach_iv_am.setImageResource(R.drawable.seach_white_bg);

            seach_iv_am.clearAnimation();

            //2、执行图标在旋转动画
            startSeachAnimation();
        }
    }

    /**================================搜索图标的动画实现======================================*/

    private void startSeachAnimation() {

        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(100);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(this);
        home_rl.setAnimation(alphaAnimation);

        who=0;

    }

    /**================================旋转动画监听回调方法======================================*/

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        switch (who){
            case 0:
                seach_iv_am.setImageResource(R.drawable.seach_gray_bg);

                RotateAnimation rotateAnimation = new RotateAnimation(15, 50, Animation.RELATIVE_TO_PARENT, -0.7f,
                        Animation.RELATIVE_TO_PARENT, 0f);
                rotateAnimation.setDuration(500);
                rotateAnimation.setAnimationListener(this);
                rotateAnimation.setFillAfter(true);
                seach_iv_am.setAnimation(rotateAnimation);
                who=1;
                break;
            case 1:

                //将seach图标移至屏幕中间
                harf_width = context.getResources().getDisplayMetrics().widthPixels / 2;
                harf_height = context.getResources().getDisplayMetrics().heightPixels / 2;
                width = seach_iv_am.getMeasuredWidth();


                lp = (RelativeLayout.LayoutParams) seach_iv_am.getLayoutParams();
                lp.setMargins(harf_width - width /2, harf_height - width /2, harf_width - width /2, harf_height - width /2);
                seach_iv_am.setLayoutParams(lp);

                ScaleAnimation scaleAnimation = new ScaleAnimation(1, 15, 1, 15,
                        Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                scaleAnimation.setFillAfter(true);
                scaleAnimation.setDuration(500);
                scaleAnimation.setAnimationListener(this);
                seach_iv_am.startAnimation(scaleAnimation);

                who=2;
                break;

            case 2:
                Intent intent = new Intent(context, SeachActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                who = 3;
                AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
                alphaAnimation.setDuration(100);
                alphaAnimation.setFillAfter(true);
                alphaAnimation.setAnimationListener(this);
                home_rl.setAnimation(alphaAnimation);
                break;

            case 3:
                who=4;
                ScaleAnimation sa = new ScaleAnimation(15, 1, 15, 1,
                        Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                sa.setFillAfter(true);
                sa.setDuration(100);
                sa.setAnimationListener(this);
                seach_iv_am.startAnimation(sa);

                break;
            case 4:
                int left = seach_iv.getLeft();
                int top = seach_iv.getTop();
                LOGUtils.Log("left:"+left +":top:"+top+":width:"+width);
                lp = (RelativeLayout.LayoutParams) seach_iv_am.getLayoutParams();
                lp.setMargins(left,top,left+width,top+width);
                seach_iv_am.setLayoutParams(lp);
                seach_iv.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
