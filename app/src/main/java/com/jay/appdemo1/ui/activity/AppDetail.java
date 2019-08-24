package com.jay.appdemo1.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jay.appdemo1.R;

public class AppDetail extends Activity implements View.OnClickListener {

    private ImageView back;
    private RelativeLayout github;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.appdetail_layout);
        findViewById(R.id.appdetail_back).setOnClickListener(this);

//        back = findViewById(R.id.appdetail_back);
//        github = findViewById(R.id.appdetail_github);

//        back.setOnClickListener(this);
//        github.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.appdetail_back:
                finish();
                break;
            /*case R.id.appdetail_github:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/git-xuhao/KotlinMvp"));
                startActivity(intent);
                break;*/
        }
    }
}
