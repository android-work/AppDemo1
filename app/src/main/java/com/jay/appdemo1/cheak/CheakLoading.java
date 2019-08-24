package com.jay.appdemo1.cheak;

import android.text.TextUtils;
import android.widget.Toast;

import com.jay.appdemo1.Mic;
import com.jay.appdemo1.db.DBHelp;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.interface_.CallBack_nameLisence;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.ToastUtils;

import java.util.HashMap;

public class CheakLoading {

    private static HashMap<String, String> userMap;
    private static CallBack_nameLisence callBack_nameLisence;

    /**
     * 检查用户是否注册账号:
     * 判断集合的数据
     * 检查用户是否登录:
     * 当用户注册成功只是往库里写用户名和密码，没有uid
     * 如果用户登录成功则往库里写如uid，用户名密码不变*/
    public static int cheakIsLoading(){
        userMap = DbOperation.getInstance().dbQuery_user();
        LOGUtils.Log("用户集合："+userMap);
        if (userMap ==null || userMap.size()==0){
            //表示没有注册
            return -1;
        }else if (userMap.get("user_name")!=null && !userMap.get("user_name").equals("") &&
                userMap.get("password")!=null && !userMap.get("password").equals("") &&
                ( userMap.get("uid")==null || userMap.get("uid").equals(""))){
            //存在用户名密码，没有uid，表示注册成功未登录
            return 1;
        }else if (userMap.get("user_name")!=null && !userMap.get("user_name").equals("") &&
                userMap.get("password")!=null && !userMap.get("password").equals("") &&
                ( userMap.get("uid")!=null || !userMap.get("uid").equals(""))){
            //存在用户名密码并且存在uid，表示登录成功
            return 0;
        }
        return -1;
    }

    /**登录功能*/
    public static void loading(String name , String password){
        switch (cheakIsLoading()){
            case 1:
                //匹配用户名密码并添加uid
                String user_name = userMap.get("user_name");
                String user_password = userMap.get("password");
                if (name.equals(user_name) && password.equals(user_password)){
                    //匹配一致，进行设置uid
                    DbOperation.getInstance().dbUpdate_user("loading suc "+user_name,user_name);
                    ToastUtils.show("登录成功");
                    DbOperation.getInstance().dbInsert_info("loading suc "+user_name);
                    callBack_nameLisence.getName(name);
                }
                break;
            case -1:
                ToastUtils.show("该用户未注册，请先注册");
                break;
        }
    }

    //设置回调昵称的接口对象
    public static void setCallBack_nameLisence(CallBack_nameLisence callBack_nameLisence){

        CheakLoading.callBack_nameLisence = callBack_nameLisence;
    }
}
