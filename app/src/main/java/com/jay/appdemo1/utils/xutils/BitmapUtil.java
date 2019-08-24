package com.jay.appdemo1.utils.xutils;

import com.jay.appdemo1.Mic;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by Administrator on 2018/12/12.
 */

public class BitmapUtil {

    private static BitmapUtils mBitmapUtils=null;
    private BitmapUtil(){}

    public static BitmapUtils getInstance(){

        if (mBitmapUtils==null){
            synchronized (BitmapUtil.class){
                if (mBitmapUtils==null){
                    mBitmapUtils=new BitmapUtils(Mic.getmCtx());
                }
            }
        }
        return mBitmapUtils;
    }

}
