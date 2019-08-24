package com.jay.appdemo1.utils;

import android.widget.Toast;

import com.jay.appdemo1.Mic;

public class ToastUtils {

    private static Toast toast;

    public static void show(String content){
        if (toast == null){
            toast = Toast.makeText(Mic.getmCtx(),content,0);
        }else{
            toast.setText(content);
        }
        toast.show();
    }
}
