package com.jay.appdemo1.photo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jay.appdemo1.R;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.thread.SaveVideoThread;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.ToastUtils;

import org.w3c.dom.Text;

import java.io.IOException;

public class IssueActivity extends Activity implements View.OnClickListener {

    private ImageView issue_back;
    private TextView tv_back;
    private EditText preview_et;
    private ImageView preview_iv;
    private Button draft;
    private Button perview;
    private CheckBox save_local;
    private LinearLayout operation_btn;
    private MediaPlayer mediaPlayer;
    private String url;
    private SurfaceHolder holder;
    private String photo;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        photo = intent.getStringExtra("photo");
        title = intent.getStringExtra("title");
        LOGUtils.Log(photo +":issue url:"+url);
        LOGUtils.Log("title:"+title);
        setContentView(R.layout.issue_layout);

        issue_back = findViewById(R.id.issue_back);
        tv_back = findViewById(R.id.tv_back);
        preview_et = findViewById(R.id.preview_et);
        preview_iv = findViewById(R.id.preview_iv);
        draft = findViewById(R.id.draft);
        perview = findViewById(R.id.perview);
        save_local = findViewById(R.id.save_local);
        operation_btn = findViewById(R.id.operation_btn);

        issue_back.setOnClickListener(this);
        tv_back.setOnClickListener(this);
        draft.setOnClickListener(this);
        perview.setOnClickListener(this);
        save_local.setOnClickListener(this);
        preview_iv.setOnClickListener(this);

        if (title!=null && !title.equals("")){
            preview_et.setText(title);
        }

        Bitmap bitmap = BitmapFactory.decodeFile(photo);
        preview_iv.setImageBitmap(bitmap);


    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //返回到视频浏览页
            case R.id.issue_back:
            case R.id.tv_back:
                finish();
                break;
            case R.id.preview_iv:
                Intent intent = new Intent(this, PhotoActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
                finish();
                break;
            case R.id.draft:
                /**草稿，其实存在本地，然后保存到数据库，在我的页面中通过访问数据库获取草稿数据展示在新的页面中，并用listview展示，
                点击条目，跳转到发布页面，并销毁当前页面，展示标题，视频缩略图，跳转到发布页面后，输入框为原先数据，其他都不变*/
                String text = preview_et.getText().toString().trim();
                //将草稿相关的信息保存到数据库中
                DbOperation.getInstance().dbUpData_draft(url,text);

                ToastUtils.show("保存到草稿箱成功");

                Intent draft = new Intent(this, DraftActivity.class);
                draft.putExtra("table","draft");
                startActivity(draft);
                finish();

                break;
            case R.id.perview:
                boolean checked = save_local.isChecked();
                String trim = preview_et.getText().toString().trim();
                if (checked) {

                    //提交，模拟上传视频，将视频保存在手机本地，需要判断checkbox是否为选中，选中需要保存视频在本地
                    LOGUtils.Log("trim:"+trim+"::photo:"+photo+"::url:"+url);
                    DbOperation.getInstance().dbInsert_preview("save_local","", trim, photo, url);
                    DbOperation.getInstance().dbInsert_preview("preview","", trim, photo, url);
                }else{
                    DbOperation.getInstance().dbInsert_preview("preview","", trim, photo, url);
                }

                ToastUtils.show("发布成功");

                Intent preview = new Intent(this, PreViewActivity.class);
                preview.putExtra("table","preview");
                startActivity(preview);

                DbOperation.getInstance().dbDelect_draft(url);

                finish();
                break;
        }
    }
}
