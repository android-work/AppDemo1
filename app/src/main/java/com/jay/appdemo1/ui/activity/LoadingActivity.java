package com.jay.appdemo1.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jay.appdemo1.R;
import com.jay.appdemo1.cheak.CheakLoading;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.ToastUtils;
import com.lidroid.xutils.util.LogUtils;

import org.w3c.dom.Text;

public class LoadingActivity extends Activity implements View.OnFocusChangeListener, View.OnClickListener {

    private Button user_load;
    private EditText user_name;
    private Button user_reg;
    private EditText user_password;
//    private TextView user_name_tv;
//    private TextView user_password_tv;
    private String name;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout);

        user_load = findViewById(R.id.user_load);
        user_name = findViewById(R.id.user_name);
        user_reg = findViewById(R.id.user_reg);
        user_password = findViewById(R.id.user_password);

        user_name.setOnFocusChangeListener(this);
        user_password.setOnFocusChangeListener(this);
        user_load.setOnClickListener(this);
        user_reg.setOnClickListener(this);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()){
            case R.id.user_name:
                if (b){
                    //获取焦点
                    user_name.setHint("");
                }else{
                    //失去焦点
                    user_name.setHint("用户名");
                }
                break;
            case R.id.user_password:
                if (b){
                    //获取焦点
                    user_password.setHint("");
                }else{
                    //失去焦点
                    user_password.setHint("密码");
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_reg:
                LOGUtils.Log("点击了注册按钮");
                name = user_name.getText().toString().trim();
                password = user_password.getText().toString().trim();
                if (name.equals("")||name==null||password.equals("")||password==null){
                    ToastUtils.show("用户名密码不能为空");
                    return;
                }
                if (CheakLoading.cheakIsLoading()==1){
                    ToastUtils.show("用户已存在");
                    return;
                }
                long user = DbOperation.getInstance().dbInsert_user(name, password);
                LOGUtils.Log("user:"+user);
                if (user!=0){
                    ToastUtils.show("注册成功");
                }
                break;
            case R.id.user_load:

                name = user_name.getText().toString().trim();
                password = user_password.getText().toString().trim();
                if (name.equals("")||name==null||password.equals("")||password==null){
                    ToastUtils.show("用户名密码不能为空");
                    return;
                }
                switch (CheakLoading.cheakIsLoading()){
                    case -1:
                        ToastUtils.show("用户未，请先注册");
                        break;
                    case 1:
                        CheakLoading.loading(name, password);
                        finish();
                        break;
                }

        }
    }
}
