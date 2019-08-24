package com.jay.appdemo1.ui.fragment;



import android.annotation.SuppressLint;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.NetWork_Utils;


/**
 * Created by liuzhi on 2018/12/6.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private static final int SUCCESS_STATE=0;
    private static final int ERROR_STATE=-1;
    private static final int NO_NETWORK_STATE=1;
    private static final int ENPTY_STATE=2;
    private static final int LOADING_STATE=3;
    public static int cur_state=3;
//    public static boolean tag=false;
    private FrameLayout mfragment;
    private View mLoading_layout;
    private View mError_layout;
    private View mEnpty_layout;
    private View mNo_net_layout;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String data = (String) msg.obj;
            switch (msg.what){
                case 0:
                    if (TextUtils.isEmpty(data)){
                        BaseFragment.cur_state=2;
                    }else {
                        BaseFragment.cur_state=0;
                        //解析数据
                        parseData(data);
                    }

                    break;
                case -1:
                    break;
                case 2:

                    //更新ui
                    mfragment.removeAllViews();
                    //加载子布局
                    View view = initView();
                    mfragment.addView(view);
                    break;
            }
        }
    };
    private Button no_network_btn;
    private Button error_btn;
    public static Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        context = getContext();
        LOGUtils.Log("baseFragment:准备去加载子布局");

        //加载默认布局
        mfragment = (FrameLayout) inflater.inflate(R.layout.base_nomal_layout, container, false);

        //根据当前的状态加载布局
        initLayout(inflater);

        //根据当前是否有网来展示界面
        reLoad();

        LOGUtils.Log("cur_state:"+cur_state);
        mLoading_layout.setVisibility(cur_state==LOADING_STATE || cur_state==SUCCESS_STATE ? View.VISIBLE : View.INVISIBLE);
        mError_layout.setVisibility(cur_state==ERROR_STATE ? View.VISIBLE : View.INVISIBLE);
        mEnpty_layout.setVisibility(cur_state==ENPTY_STATE ? View.VISIBLE : View.INVISIBLE);
        mNo_net_layout.setVisibility(cur_state==NO_NETWORK_STATE ? View.VISIBLE : View.INVISIBLE);

        //如果当前的状态为loading/success，加载子布局
        if (cur_state==LOADING_STATE || cur_state==SUCCESS_STATE){

            //加载数据
            loadData(mHandler);

        }



        return mfragment;

    }

    /**根据当前状态加载默认布局*/
    private void initLayout(LayoutInflater inflater) {

        mLoading_layout = inflater.inflate(R.layout.loading_loading, null, false);
        mError_layout = inflater.inflate(R.layout.loading_error, null, false);
        mEnpty_layout = inflater.inflate(R.layout.loading_enpty, null, false);
        mNo_net_layout = inflater.inflate(R.layout.loading_no_network, null, false);

        if (mLoading_layout!=null){
            mfragment.addView(mLoading_layout);
        }if (mError_layout!=null){
            mfragment.addView(mError_layout);
        }if (mEnpty_layout!=null){
            mfragment.addView(mEnpty_layout);
        }if (mNo_net_layout!=null){
            mfragment.addView(mNo_net_layout);
        }

        no_network_btn = mNo_net_layout.findViewById(R.id.no_network_btn);
        error_btn = mError_layout.findViewById(R.id.error_btn);

        no_network_btn.setOnClickListener(this);
        error_btn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LOGUtils.Log("onActivityCreated");
    }

    /**加载子类布局*/
    public abstract View initView();

    /**加载子fragment的数据*/
    public abstract void loadData(Handler handler);

    /**解析从网络获取的子fragment数据*/
    protected abstract void parseData(String data);

    @Override
    public void onClick(View view) {
        LOGUtils.Log("点击了按钮" );
        reLoad();
    }

    /**重新加载页面*/
    public void reLoad(){
        if (!NetWork_Utils.getNetWork(getContext())){

            mLoading_layout.setVisibility(View.VISIBLE);
            mNo_net_layout.setVisibility(View.INVISIBLE);
            mError_layout.setVisibility(View.INVISIBLE);

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!NetWork_Utils.getNetWork(getContext())){
                        cur_state=NO_NETWORK_STATE;
                        mNo_net_layout.setVisibility(View.VISIBLE);
                        mLoading_layout.setVisibility(View.INVISIBLE);
                    }else{
                        //加载数据
                        loadData(mHandler);
                    }

                }
            },5000);
        }else{
            /*mLoading_layout.setVisibility(cur_state==LOADING_STATE || cur_state==SUCCESS_STATE ? View.VISIBLE : View.INVISIBLE);
            if (mLoading_layout!=null){
                mfragment.removeView(mLoading_layout);
                mfragment.addView(mLoading_layout);
            }*/

//            loadData(mHandler);
        }
    }
}
