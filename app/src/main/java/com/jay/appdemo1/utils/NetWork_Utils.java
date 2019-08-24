package com.jay.appdemo1.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by liuzhi on 2018/12/7.
 */

public class NetWork_Utils {

    public static boolean getNetWork(Context context){
        boolean state=false;

        if (context==null){
            return false;
        }

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        //有网络并可用
        if (netInfo!=null&&netInfo.isConnected() && netInfo.isAvailable()){
            String typeName = netInfo.getTypeName();
            if (typeName.equals("WIFI")||typeName.equals("MOBILE")){

                LOGUtils.Log("当前状态："+1);
                state=true;
            }else{

                Toast.makeText(context,"请检查网络",Toast.LENGTH_LONG).show();
                state=false;
            }
        }
        return state;
    }
}
