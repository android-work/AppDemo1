package com.jay.appdemo1.utils;

import android.os.Handler;

/**
 * Created by Administrator on 2018/12/9.
 */

public class LoadData {

    /**请求网络*/
    public void loadData(final Handler mHandler,final String url){

        //开启子线程请求网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil.httpGet(mHandler,url);
            }
        }).start();
    }
}
