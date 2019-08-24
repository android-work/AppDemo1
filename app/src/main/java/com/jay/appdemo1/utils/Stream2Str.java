package com.jay.appdemo1.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by liuzhi on 2018/12/7.
 */

public class Stream2Str {


    public static String getStream2Str(InputStream inputStream ){

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line=null;
        StringBuffer sb = new StringBuffer();

        try {
            while((line=br.readLine())!=null){
                sb.append(line);
            }
            line=sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return line;
    }
}
