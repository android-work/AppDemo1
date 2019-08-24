package com.jay.appdemo1.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jay.appdemo1.R;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.utils.LOGUtils;

import org.w3c.dom.Text;

public class EditorDetailActivity extends Activity implements View.OnClickListener, View.OnFocusChangeListener, TextWatcher {

    private ImageView edit_back;
    private TextView edit_title;
    private EditText editor_edit;
    private Button edit_save;
    private TextView edit_des;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        String des = intent.getStringExtra("des");

        setContentView(R.layout.editr_detail_layout);

        edit_back = findViewById(R.id.edit_back);
        edit_title = findViewById(R.id.edit_title);
        editor_edit = findViewById(R.id.editor_edit);
        edit_save = findViewById(R.id.edit_save);
        edit_des = findViewById(R.id.edit_des);

        edit_title.setText(title);
        edit_des.setText(des);

        edit_back.setOnClickListener(this);
        editor_edit.setOnFocusChangeListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit_back:
                finish();
                break;
            case R.id.edit_save:
                LOGUtils.Log("点击了保存按钮");
                String trim = editor_edit.getText().toString().trim();
                //将当前的信息保存到数据库，并且更新ui
                if (title!=null && title.equals("更改名称")){
                    //将昵称保存到name字段中,更新ui
                    DbOperation.getInstance().dbUpdate_user_info(UserDetailActivity.uid,"user_name",trim,"user");
                    //更新userDetailActivity
                    Intent intent = new Intent();
                    intent.putExtra("name",trim);
                    setResult(RESULT_OK,intent);
                    finish();
                    //更新Mine_fragment
                }else if (title!=null && title.equals("个性签名")){
                    //将个性签名保存到sign字段中，更新ui
                    DbOperation.getInstance().dbUpdate_user_info(UserDetailActivity.uid,"sign",trim,"user_info");
                    //更新UserDetailActivity
                    Intent intent = new Intent();
                    intent.putExtra("sign",trim);
                    setResult(RESULT_OK,intent);
                    finish();
                }
                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        String trim = editor_edit.getText().toString().trim();
        if (b){
            LOGUtils.Log("获取焦点");
            //当输入框获取焦点时并且输入框的内容与获取焦点前的内容不一致，将保存按钮至于选中状态，此时具有点击事件
            editor_edit.addTextChangedListener(this);
        }else{
            LOGUtils.Log("未获取焦点");
            edit_save.setBackgroundColor(getResources().getColor(R.color.normal_editor));

        }
    }

    /**=========================================文本编辑框输入监听回调=======================================*/
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        edit_save.setBackgroundColor(getResources().getColor(R.color.focus_editor));
        edit_save.setOnClickListener(this);
    }
}
