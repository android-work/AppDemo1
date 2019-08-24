package com.jay.appdemo1.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.interface_.CallBack_nameLisence;
import com.jay.appdemo1.utils.LOGUtils;

import java.util.HashMap;

public class UserDetailActivity extends Activity implements View.OnClickListener, DialogInterface.OnDismissListener {

    private static final int NAME_CODE = 1;
    private static final int SIGN_CORD = 2;
    private static CallBack_nameLisence callBack_nameLitener;
    private RelativeLayout detail_name;
    private RelativeLayout detail_head;
    private RelativeLayout detail_sex;
    private RelativeLayout detail_sign;
    private RelativeLayout detail_adress;
    private ImageView detail_back;
    private ImageView head;
    private TextView name;
    private TextView sex;
    private TextView sign;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setContentView(R.layout.user_detail_layout);

            initView();
            setViewData();

        }
    };
    public String address_;
    public static String uid;
    private int state;
    public static String name_;

    private void setViewData() {
        String head_ = user_infoMap.get("head");
        String name_ = user_infoMap.get("name");
        String sex_ = user_infoMap.get("sex");
        String sign_ = user_infoMap.get("sign");
        address_ = user_infoMap.get("address");
        if (sex_!=null && sex_.equals("男")){
            state = 0;
        }else if (sex_!=null && sex_.equals("女")){
            state = 1;
        }else{
            state = 0;
        }

        UserDetailActivity.name_ = name_;
        LOGUtils.Log("name:"+name_);
        if (name_==null || name_.equals("")){
            name.setText("未设置");
        }else{
            name.setText(name_);
        }

        LOGUtils.Log("sex:"+sex_);
        if (sex_==null || sex_.equals("")){
            sex.setText("未设置");
        }else{
            sex.setText(sex_);
        }

        LOGUtils.Log("sign:"+sign_);
        if (sign_==null || sign_.equals("")){
            sign.setText("未设置");
        }else{
            sign.setText(sign_);
        }

        if (head_==null || head_.equals("")){
            head.setImageResource(R.mipmap.ic_launcher);
        }else{
            Bitmap bitmap = BitmapFactory.decodeFile(head_);
            head.setImageBitmap(bitmap);
        }


    }

    private HashMap<String, String> user_infoMap;

    private void initView() {
        findViewById(R.id.user_detail_name).setOnClickListener(this);
        findViewById(R.id.user_detail_head).setOnClickListener(this);
        findViewById(R.id.user_detail_sex).setOnClickListener(this);
        findViewById(R.id.user_detail_sign).setOnClickListener(this);
        findViewById(R.id.user_detail_address).setOnClickListener(this);

        detail_back = findViewById(R.id.user_detail_back);
        head = findViewById(R.id.head);
        name = findViewById(R.id.name);
        sex = findViewById(R.id.sex);
        sign = findViewById(R.id.sign);

        detail_back.setOnClickListener(this);
        head.setOnClickListener(this);
        /*name.setOnClickListener(this);
        sex.setOnClickListener(this);
        sign.setOnClickListener(this);*/
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    /**请求数据库进行展示数据*/
    public void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                user_infoMap = DbOperation.getInstance().dbQuery_user_info();
                uid = user_infoMap.get("uid");
                String name = DbOperation.getInstance().db_query_user_name(uid);
                user_infoMap.put("name",name);
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.user_detail_back:
                finish();
                break;
            case R.id.user_detail_head:
                break;
            case R.id.head:
                break;

            case R.id.user_detail_name:
                Intent intent = new Intent(Mic.getmCtx(), EditorDetailActivity.class);
                intent.putExtra("title","更改名称");
                intent.putExtra("des","好的名字可以让你感觉更得劲");
                startActivityForResult(intent,NAME_CODE);
                break;
            case R.id.user_detail_sex:
                //有个单选框

                AlertDialog dialog = new AlertDialog.Builder(UserDetailActivity.this).setTitle("性别").setOnDismissListener(this)
                        .setSingleChoiceItems(new String[]{"男", "女"}, state, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                state = which;
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();

                break;
           case R.id.user_detail_sign:
               Intent sign = new Intent(Mic.getmCtx(), EditorDetailActivity.class);
               sign.putExtra("title","个性签名");
               sign.putExtra("des","更好的展示自己");
                startActivityForResult(sign,SIGN_CORD);
                break;
           case R.id.user_detail_address:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data!=null){
            switch (requestCode){
                case NAME_CODE:
                    String name_ = data.getStringExtra("name");
                    UserDetailActivity.name_ = name_;
                    name.setText(name_);
                    break;
                case SIGN_CORD:
                    String sign_ = data.getStringExtra("sign");
                    sign.setText(sign_);
                    break;
            }
        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        LOGUtils.Log("对话框消失");

        //将数据保存到本地，更新ui
        if (state==0) {
            sex.setText("男");
            DbOperation.getInstance().dbUpdate_user_info(uid,"sex","男","user_info");
        }
        else {
            sex.setText("女");
            DbOperation.getInstance().dbUpdate_user_info(uid,"sex","女","user_info");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        callBack_nameLitener.getName(name_);
        finish();
    }

    public static void setCallBack_nameLitener(CallBack_nameLisence callBack_nameLitener){

        UserDetailActivity.callBack_nameLitener = callBack_nameLitener;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
