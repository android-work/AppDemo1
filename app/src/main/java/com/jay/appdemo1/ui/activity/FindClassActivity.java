package com.jay.appdemo1.ui.activity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.adapter.Detail_lv_adapter;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.interface_.ListViewScrollListener;
import com.jay.appdemo1.modal.HistoryModal;
import com.jay.appdemo1.modal.find.Find_getClass_model;
import com.jay.appdemo1.modal.home.Rank_model;
import com.jay.appdemo1.ui.fragment.Hot_drop_Fragment;
import com.jay.appdemo1.ui.fragment.listener.Click_Gridview_Listener;
import com.jay.appdemo1.utils.LOGUtils;
import com.lidroid.xutils.util.LogUtils;

import java.util.HashMap;

/**
 * Created by liuzhi on 2019/1/28.
 */

public class FindClassActivity extends Activity implements AbsListView.OnScrollListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private static Click_Gridview_Listener click_gridview_listener;
    /**
     * 本页面主要是通过listview展现广告的，再listview上面覆盖一个悬浮窗，
     * 当listview静止的时候悬浮窗的表现为：左下角一个TV，右下角一个IV
     * 当向上滑动listview的时候，TV字体大小会变小，字体颜色会由白-->灰，位置会逐渐的往右上角方向移动，IV位置会向上移动，直到悬浮窗高度的某个零界点
     * 当listview上滑到某个灵界点时，悬浮窗背景会有个动画渐变成白色半透明，并且TV字体大小任在变小，颜色由灰-->黑，直到悬浮窗尺寸为某个固定值
     * */

    private Find_getClass_model.ItemListBeanX itemListBeanX;
    private ImageView detail_iv_back;
    private ImageView rl_iv_share;
    private TextView rl_tv_title;
    private RelativeLayout tv_iv_rl;
    private ListView detail_lv;
    private int slipY;
    private float down;
    private AlphaAnimation alphaAnimation;
    private int iv_back_height;
    private int iv_back_width;
    private boolean tagHeight = true;
    private boolean tagLocal = true;
    private boolean tagSize = true;
    private boolean tag = true;
    private int getLeft;
    private int textSize;
    private int height;
    private float scaledDensity;
    private int currentPostiton;
    private float slipX;
    private float move;
    private ListViewScrollListener listViewScrollListener;
    private boolean isScroll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findclassactivity_layout);
        itemListBeanX = click_gridview_listener.getObj();
        LOGUtils.Log("findClassActivity:"+itemListBeanX.getData().getHeader().getTitle());

        detail_lv = findViewById(R.id.detail_lv);
        tv_iv_rl = findViewById(R.id.tv_iv_rl);//需要展现悬浮框的时候动态的设置布局背景
        rl_tv_title = findViewById(R.id.rl_tv_title);
        rl_iv_share = findViewById(R.id.rl_iv_share);
        detail_iv_back = findViewById(R.id.detail_iv_back);

        initUI();
    }

    /**当布局画好后，自动会执行该方法*/
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    private void initUI() {
        int heightPixels = getResources().getDisplayMetrics().heightPixels;
        height = (int) (heightPixels / 2.5);

        scaledDensity = getResources().getDisplayMetrics().scaledDensity;
        float textPXSize = rl_tv_title.getTextSize();
        textSize = (int) ((textPXSize / scaledDensity) + 0.5f);

        detail_iv_back.measure(0,0);
        iv_back_height = (int) (detail_iv_back.getMeasuredHeight()*1.5);
        iv_back_width = detail_iv_back.getMeasuredWidth();

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        tv_iv_rl.setLayoutParams(layoutParams);
        detail_lv.setAdapter(new Detail_lv_adapter(itemListBeanX,getApplicationContext()));

        detail_lv.setOnScrollListener(this);

        rl_tv_title.setText(itemListBeanX.getData().getHeader().getTitle());



        detail_iv_back.setOnClickListener(this);

        detail_lv.setOnItemClickListener(this);

    }

 /**================================================================listview滑动监听========================================================================*/
     @SuppressLint("ResourceAsColor")
     @Override
     public void onScrollStateChanged(AbsListView view, int scrollState) {
         /**
          * 当listview条目滚动状态切换为静止状态时，走极限逻辑
          * */
     }
     @Override
     public void onScroll(AbsListView view, final int firstVisibleItem, int visibleItemCount, int totalItemCount) {

         /**按照可见条目0来分类
          * 上滑：
          *     根据条目0的gettop的值区分上滑的三个极限
          *         如果gettop的绝对值<1/3的条目高度，缩放字体且上移，透明的悬浮窗高度降低，分享图片上移
          *         如果gettop的绝对值=1/3的条目高度，悬浮窗背景为白色半透明，分享图片消失，文字大小固定，颜色由白边灰
          *         如果2/3的条目高度>gettop的绝对值>1/3的条目高度，半透明悬浮窗高度降低，字体右上方向移动至返回按钮一水平线为止，
          *         如果gettop的绝对值>=2/3的条目高度，半透明悬浮窗高度固定为1/3条目高度，字体颜色变黑，位置与返回按钮一水平线
          * 上下滑动：
          * 下滑：
          *     根据条目0的gettop值与整个条目高度做差就是滑动距离Y
          *         如果Y<2/3的条目高度，字体左下方移动，半透明悬浮窗高度增加
          *         如果Y=2/3的条目高度，字体变灰，显示分享图标
          *         如果条目高度>Y>2/3的条目高度，字体变大，位置下移，悬浮窗背景变透明
          *         如果Y=条目高度，字体变白，悬浮窗高度固定条目高度
          * */
         LOGUtils.Log("firstVisibleItem:"+firstVisibleItem);
         //获取0条目的gettop值
//         int top = detail_lv.getChildAt(0).getTop();
         //设置listview的滑动监听
         detail_lv.setOnTouchListener(new View.OnTouchListener() {

             @Override
             public boolean onTouch(View view, MotionEvent motionEvent) {

                 //设置监听回调

                 switch (motionEvent.getAction()){
                     case MotionEvent.ACTION_DOWN:
                         down = motionEvent.getY();//获取触摸屏幕的y坐标
                         isScroll = true;
                         break;
                     case MotionEvent.ACTION_MOVE:
                         float move_down = motionEvent.getY();
                         move = move_down - down;//手指移动的位移

                         //获取0条目的gettop值
                         int top = detail_lv.getChildAt(0).getTop();
                         LOGUtils.Log(height/3+":top:"+Math.abs(top));
                         LOGUtils.Log("move:"+move);

                         /**上滑:
                          *     对于上滑来说，top值就是上滑的总位移
                          *         根据这top值来设定不同极限的不同效果
                          *  */
                         if (move<0 ){
                             if (firstVisibleItem==0) {
                                 /**如果gettop的绝对值<1/3的条目高度，缩放字体且上移，透明的悬浮窗高度降低，分享图片上移*/
                                 if (Math.abs(top) < height / 3) {
                                     //字体缩放，透明悬浮窗高度降低，字体颜色由白变灰
                                     double rate = ((double) (20 * top / (height / 3)));//字体变化率
                                     LOGUtils.Log(textSize + ":字体变化率:" + (double) (20 / (height / 3)) + ">>" + (20 * top / (height / 3)) + ">>" + rate);
                                     rl_tv_title.setTextSize((float) (textSize + rate));

                                     RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height + top);
                                     tv_iv_rl.setLayoutParams(layoutParams);
                                 }/**如果gettop的绝对值=1/3的条目高度，悬浮窗背景为白色半透明，分享图片消失，文字大小固定，颜色由白边灰*/
                                 else if (Math.abs(top) == height / 3) {
//                                     createAlphaAnimation(0,0.7f);
                                     //极限操作，将字体变最小，悬浮窗高度减小滑动位移
                                     rl_tv_title.setTextSize(20);

                                     RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height - height / 3);
                                     tv_iv_rl.setLayoutParams(layoutParams);

                                     tv_iv_rl.setBackgroundColor(getResources().getColor(R.color.find_class_bg));

                                     rl_iv_share.setVisibility(View.GONE);

                                     rl_tv_title.setTextColor(Color.GRAY);
                                 }/**如果2/3的条目高度>gettop的绝对值>1/3的条目高度，半透明悬浮窗高度降低，字体右上方向移动至返回按钮一水平线为止*/
                                 else if (Math.abs(top) < 2 * height / 3 && Math.abs(top) > 1 / 3 * height) {
                                     //极限操作，设背景、图片消失、字体大小固定
                                     tv_iv_rl.setBackgroundColor(getResources().getColor(R.color.find_class_bg));

                                     rl_iv_share.setVisibility(View.GONE);

                                     rl_tv_title.setTextSize(20);

                                     RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height + top);
                                     tv_iv_rl.setLayoutParams(layoutParams);
//                                     -(100 / (height / 3)) * (height/3+top)
                                     LOGUtils.Log("文字位移："+(-(100 * (height/3+top)/ (height / 3)) ));
                                     createTranslateAnimation(0, -(100 * (height/3+top)/ (height / 3)) );



                                 }/**如果gettop的绝对值>=2/3的条目高度，半透明悬浮窗高度固定为1/3条目高度，字体颜色变黑，位置与返回按钮一水平线*/
                                 else if (Math.abs(top) >= 2 * height / 3) {
                                     tv_iv_rl.setBackgroundColor(getResources().getColor(R.color.find_class_bg));

//                                     rl_tv_title.setGravity(Gravity.CENTER_VERTICAL);
//                                     RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                                     textParams.addRule(RelativeLayout.ALIGN_END,R.id.detail_iv_back);
//                                     rl_tv_title.setLayoutParams(textParams);

                                     rl_iv_share.setVisibility(View.GONE);

                                     RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height/3);
                                     tv_iv_rl.setLayoutParams(layoutParams);
                                 }
                             }else{
                                 /**表示第0个条目已经看不见时，走极限*/
                                 /*LOGUtils.Log("第0个条目看不见，走极限");
                                 tv_iv_rl.setBackgroundColor(getResources().getColor(R.color.find_class_bg));

                                 rl_tv_title.setGravity(Gravity.CENTER_VERTICAL);

                                 rl_tv_title.setTextSize(20);

                                 RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height / 3);
                                 tv_iv_rl.setLayoutParams(layoutParams);

                                 rl_iv_share.setVisibility(View.GONE);*/
                             }
                         }else{
                             if (firstVisibleItem==0){
                                 LOGUtils.Log("下滑0条目可见："+(height + top));
                                /**下滑的位移=height - getTop值
                                 *  如果下滑位移<height/3,不做任何效果处理*/
                                if (height + top < height/3){

                                }/**如果 2height/3>下滑位移>height/3 ,半透明悬浮窗高度变大，字体位移朝向左下方*/
                                else if (height/3 < height + top && height + top < 2*height/3){
                                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (height + top));
                                    tv_iv_rl.setLayoutParams(layoutParams);

                                    LOGUtils.Log("");

                                }/**如果 下滑位移 >= 2height/3 ,半透明悬浮窗消失，字体变大，分享图片显示*/
                                else if (height + top >= 2*height/3){
                                    rl_iv_share.setVisibility(View.VISIBLE);

                                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height + top);
                                    tv_iv_rl.setLayoutParams(layoutParams);

                                    tv_iv_rl.setBackgroundColor(Color.TRANSPARENT);

                                    rl_tv_title.setTextSize(20 + 20*(height + top)/(height));
                                }

                             }
                             //下滑极限设置
                             /*if (firstVisibleItem==0 && top==0){
                                *//* *走极限操作*//*
                                 LOGUtils.Log("下滑走极限");
                                 rl_tv_title.setTextSize(40);
                                 rl_iv_share.setVisibility(View.VISIBLE);
                                 tv_iv_rl.setBackgroundColor(getResources().getColor(R.color.find_class_bg_cancel));
                                 rl_tv_title.setTextColor(Color.WHITE);

                             }*/
                         }
                         break;
                     case MotionEvent.ACTION_UP:


                         break;
                 }

                 return false;
             }
         });
         LOGUtils.Log("---------------------------------------");
         /*if (firstVisibleItem == 0){
             if (0<=-top && -top<height/3){
                 *//**缩放字体且上移，透明的悬浮窗高度降低，分享图片上移*//*
             }else if (-top>height/3 && -top<=2*height/3){
                 *//**半透明悬浮窗高度降低，字体右上方向移动至返回按钮一水平线为止*//*
             }else if (-top>2*height/3){
                 *//**半透明悬浮窗高度固定为1/3条目高度，字体颜色变黑，位置与返回按钮一水平线*//*
             }
         }*/
         //对listview实时滚动监听的极限操作
         if (firstVisibleItem != 0) {
             LOGUtils.Log("走监听s拉极限");
             tv_iv_rl.setBackgroundColor(getResources().getColor(R.color.find_class_bg));

//             rl_tv_title.setGravity(Gravity.CENTER_VERTICAL);
//             RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//             textParams.addRule(RelativeLayout.ALIGN_END,R.id.detail_iv_back);
//             rl_tv_title.setLayoutParams(textParams);

             rl_tv_title.setTextSize(20);

             RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height / 3);
             tv_iv_rl.setLayoutParams(layoutParams);

             rl_iv_share.setVisibility(View.GONE);
         }else if (firstVisibleItem == 0 && detail_lv.getChildAt(0)!=null && detail_lv.getChildAt(0).getTop()==0 && isScroll){
             LOGUtils.Log("走监听x滑极限");
             rl_tv_title.setTextSize(40);
             rl_iv_share.setVisibility(View.VISIBLE);
             tv_iv_rl.setBackgroundColor(getResources().getColor(android.R.color.transparent));
             rl_tv_title.setTextColor(Color.WHITE);
             RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
             tv_iv_rl.setLayoutParams(layoutParams);

             LOGUtils.Log(rl_tv_title.getVisibility()+":图片的显示状态:"+rl_iv_share.getVisibility());
         }
     }

     /**=============================================================自定义一个滚动状态切换的监听=========================================================*/

     public void setListViewScrollListener(ListViewScrollListener listViewScrollListener){

         this.listViewScrollListener = listViewScrollListener;
     }

    /**==============================================================对象传递的回调接口方法===================================================================*/

    public static void setClick_Gridview_Listener(Click_Gridview_Listener click_gridview_listener){

        FindClassActivity.click_gridview_listener = click_gridview_listener;
    }
/**==============================================================创建动画效果===================================================================*/

    public void createAlphaAnimation(float s,float e){
        LOGUtils.Log("实现透明度动画");
        tv_iv_rl.setBackgroundColor(Color.WHITE);
        alphaAnimation = new AlphaAnimation(s, e);
        alphaAnimation.setDuration(50);
        alphaAnimation.setFillAfter(true);
        tv_iv_rl.startAnimation(alphaAnimation);
    }

    public void createTranslateAnimation(float s,float e){
        LOGUtils.Log("textview的位移操作");
        ObjectAnimator translatorX = ObjectAnimator.ofFloat(rl_tv_title, "translationX", s, e);
        translatorX.setDuration(50);
        translatorX.start();
        /*TranslateAnimation translateAnimation = new TranslateAnimation(s, e, 0, 0);
        translateAnimation.setDuration(50);
        translateAnimation.setFillAfter(true);
        rl_tv_title.startAnimation(translateAnimation);*/
    }
    /**==============================================================响应图片的点击事件===================================================================*/
    @Override
    public void onClick(View v) {
        finish();
    }

    /**==============================================================创建bean对象记录每个条目的getTop===================================================================*/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String urls = null;
        Find_getClass_model.ItemListBeanX.DataBeanX.ItemListBean object = itemListBeanX.getData().getItemList().get(position);
        urls = itemListBeanX.getData().getItemList().get(position).getData().getPlayUrl();
        Intent intent = new Intent(Mic.getmCtx(), ItemActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("video_url", urls);
        Mic.getmCtx().startActivity(intent);

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
      /*  for (int i=0;i<HistoryViewActivity.historyList.size();i++){
            if (HistoryViewActivity.historyList!=null &&
                    HistoryViewActivity.historyList.get(i).getTitle().equals(historyModal.getTitle())){
                LOGUtils.Log("存在相同条目");
                HistoryViewActivity.historyList.remove(i);
            }
        }
        HistoryViewActivity.historyList.add(0,historyModal);*/
    }
}
