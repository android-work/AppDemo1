package com.jay.appdemo1.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jay.appdemo1.R;

import java.util.ArrayList;

/**
 * Created by liuzhi on 2018/12/21.
 */

public class ConsumeList extends ListView /*implements AbsListView.OnScrollListener*/ {

    private static final int PUSH_REFRESH=2;
    private static final int PULL_LOADMORE=1;
    private static final int REFRESHING=3;
    public static int cur_state;
    private float down;
    private float start;
    private int move;
    private OnHandlerListener onHandlerListener;
    private View footView;
    private ProgressBar foot_pb;
    private TextView foot_tv;
    private int footView_hight;
    private boolean tag=false;

    public ConsumeList(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFootView();
    }

    private void initFootView() {

        footView = View.inflate(getContext(), R.layout.home_listview_head_layout, null);
        foot_pb = footView.findViewById(R.id.pb);
        foot_tv = footView.findViewById(R.id.tv);

        footView.measure(0,0);
        footView_hight = footView.getMeasuredHeight();
        Log.e("tag","footView_hight:"+footView_hight);
        footView.setPadding(0,-footView_hight,0,0);//隐藏脚步局

        super.addFooterView(footView);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){

            case MotionEvent.ACTION_DOWN:
                down = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                /**上拉/下拉阻碍*/
                start = (float) (ev.getY());
                move = (int) ((start - down)/2);

                /**下拉距离>300不再下拉*/
                if (move>300||move<-300) {
                    return super.onTouchEvent(ev);
                }

                /**当前状态为正在刷新，子类不响应下拉事件*/
                if (cur_state==REFRESHING){
                    return super.onTouchEvent(ev);
                }
                /**上拉加载更多*/
                else if (move<0&&getLastVisiblePosition()==getCount()-1){

                    tag=false;
                    int paddingbottom = -move - footView_hight;
                    footView.setPadding(0,paddingbottom,0,0);

                    /**判断当前是否处于上拉刷新状态*/
                    if (paddingbottom<0&&cur_state!=PUSH_REFRESH){
                        cur_state=PUSH_REFRESH;
                        foot_pb.setVisibility(View.INVISIBLE);
                        foot_tv.setText("上拉加载更多");
                    }
                    /**判断当前是否处于释放加载状态*/
                    else if (paddingbottom>=0 && cur_state!=PULL_LOADMORE){
                        cur_state=PULL_LOADMORE;
                        foot_pb.setVisibility(View.INVISIBLE);
                        foot_tv.setText("释放加载更多");
                    }

                }
                break;
            case MotionEvent.ACTION_UP:

                /**当前状态为上拉/下拉刷新,松手隐藏布局*/
                if (cur_state==PUSH_REFRESH){
                    cur_state=0;
                    footView.setPadding(0,-footView_hight,0,0);
                }else if (cur_state==PULL_LOADMORE){

                    cur_state=REFRESHING;
                    foot_pb.setVisibility(View.VISIBLE);
                    foot_tv.setText("刷新中...");
                    footView.setPadding(0, 0, 0, 0);

                    //回调子类去刷新页面
                    if (onHandlerListener!=null){
                        onHandlerListener.loadMore();
                    }
                }

                break;
        }
        return super.onTouchEvent(ev);
    }

    /**设置上拉/下拉刷新监听*/
    public void setOnRefreshListener(OnHandlerListener onHandlerListener ){

        this.onHandlerListener = onHandlerListener;
    }

    /**上拉/下拉结束回调*/
    public void refreshFinish(int state){
        cur_state=state;
        Log.e("tag","tag"+tag);
        if (cur_state==0){
            footView.setPadding(0, -footView_hight, 0, 0);
        }

    }

    /**上拉/下拉的监听接口*/
    public interface OnHandlerListener{
        public void loadMore();
//        public void refresh();
    }
}
