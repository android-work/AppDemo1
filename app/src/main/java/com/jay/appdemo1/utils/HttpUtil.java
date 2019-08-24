package com.jay.appdemo1.utils;

import android.os.Handler;
import android.os.Message;

import com.jay.appdemo1.Mic;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



/**
 * Created by liuzhi on 2018/12/7.
 */

public class HttpUtil {

    private static int count=0;
    public static void httpGet(Handler handler , String httpUrl){

        HttpURLConnection urlConnection=null;
        try {

            URL url = new URL(httpUrl);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(30000);
            urlConnection.setReadTimeout(15000);

            if(urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK){

                InputStream inputStream = urlConnection.getInputStream();
                String data = Stream2Str.getStream2Str(inputStream);

                LOGUtils.Log("请求数据："+data);

                Message msg = Message.obtain();
                msg.what=0;
                msg.obj=data;
                handler.sendMessage(msg);

            }

        } catch (Exception e) {
            Message msg = Message.obtain();
            msg.what=0;
            msg.obj="";
            handler.sendMessage(msg);
            e.printStackTrace();
        }finally {
            if (urlConnection!=null)
                urlConnection.disconnect();
        }

    }

    public static void getXUtils(String url, final Handler handler){

        HttpUtils http = new HttpUtils();

        http.send(HttpRequest.HttpMethod.GET,
                url,
                new RequestCallBack<String>(){
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {

                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //请求数据成功
                        String result = responseInfo.result;
                        Message msg = Message.obtain();
                        msg.what=0;
                        msg.obj=result;
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        //请求数据失败
                        Message msg1 = Message.obtain();
                        msg1.what=-1;
                        msg1.obj="";
                        handler.sendMessage(msg1);
                    }
                });

    }
}
