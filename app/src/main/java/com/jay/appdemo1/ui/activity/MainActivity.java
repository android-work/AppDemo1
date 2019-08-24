package com.jay.appdemo1.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.photo.PhotoActivity;
import com.jay.appdemo1.reciver.NetWorkReciver;
import com.jay.appdemo1.ui.fragment.BaseFragment;
import com.jay.appdemo1.ui.fragment.Find_Fragment;
import com.jay.appdemo1.ui.fragment.FragmentFactory;
import com.jay.appdemo1.ui.fragment.Home_Fragment;
import com.jay.appdemo1.ui.fragment.Hot_drop_Fragment;
import com.jay.appdemo1.ui.fragment.Mine_Fragment;
import com.jay.appdemo1.utils.LOGUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRg;
    private RadioButton mDay_check;
    private RadioButton mHot_drop;
    private RadioButton mFind;
    private RadioButton mMine;
    private FrameLayout mFl;
    private BaseFragment currentFragment;
    //    private ViewPager mMain_vp;
    private FragmentTransaction mFTransaction;
    private long mExitTime;
    public static final int RECORD_SYSTEM_VIDEO = 1;
    private BaseFragment homeFragment;
    private BaseFragment hotFragment;
    private BaseFragment findFragment;
    private BaseFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mRg.setOnCheckedChangeListener(this);

       /* //注册网络状态监听广播
        NetWorkReciver netWorkReciver= new NetWorkReciver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkReciver, filter);*/

    }

    /**
     * 初始化控件
     */
    private void init() {

        //找到FrameLayout
        mFl = findViewById(R.id.fl);

        //找到ViewPager
//        mMain_vp = findViewById(R.id.main_vp);

        //找到radiogroup
        mRg = findViewById(R.id.rg);

        //找到radiobutton
        mDay_check = findViewById(R.id.day_check);
        mHot_drop = findViewById(R.id.hot_drop);
        mFind = findViewById(R.id.find);
        mMine = findViewById(R.id.mine);

        //默认home被选中
        //获取一个fragment事务，用于添加fragment
        mFTransaction = getSupportFragmentManager().beginTransaction();
        BaseFragment homeFragment = FragmentFactory.createFragment(0);
        LOGUtils.Log("创建了home_fragment页面");
//        mFTransaction.replace(R.id.fl,homeFragment);
        //判断当前fragment是否被加载过
        addFragment(homeFragment);
        currentFragment = homeFragment;
    }

    /**
     * 设置radiobutton的点击事件
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int radiobuttonId) {

        mFTransaction = getSupportFragmentManager().beginTransaction();

        //当点击一个radiobutton时开启一个fragment(第一次则创建，之后则开启)
        switch (radiobuttonId) {
            case R.id.day_check:
                Log.e("tag", "home_fragment");
                homeFragment = FragmentFactory.createFragment(0);
                LOGUtils.Log("创建了home_fragment页面");
//                mFTransaction.replace(R.id.fl,homeFragment);
                addFragment(homeFragment);
                currentFragment = homeFragment;
//                mFTransaction.commit();
                break;
            case R.id.find:
                hotFragment = FragmentFactory.createFragment(1);
//                mFTransaction.replace(R.id.fl,hotFragment);
//                mFTransaction.commit();
                addFragment(hotFragment);
                currentFragment = hotFragment;
                break;
            case R.id.photo:
                reconverIntent();
                break;
            case R.id.hot_drop:
                findFragment = FragmentFactory.createFragment(2);
//                mFTransaction.replace(R.id.fl,findFragment);
//                mFTransaction.commit();
                addFragment(findFragment);
                currentFragment = findFragment;
                break;
            case R.id.mine:
                mineFragment = FragmentFactory.createFragment(3);
//                mFTransaction.replace(R.id.fl,mineFragment);
//                mFTransaction.commit();
                addFragment(mineFragment);
                currentFragment = mineFragment;
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 1500) {
            Toast.makeText(MainActivity.this, "再按一次退出AppDemo", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    public void addFragment(Fragment fragment) {
        if (!fragment.isAdded()) {
            if (currentFragment != null) {
                mFTransaction.hide(currentFragment).add(R.id.fl, fragment).commit();
            } else {
                mFTransaction.add(R.id.fl, fragment);
                mFTransaction.commit();
            }
        } else {
            if (currentFragment != null) {
                mFTransaction.hide(currentFragment).show(fragment).commit();
            } else {
                mFTransaction.show(fragment).commit();
            }
        }
    }

    /**
     * 启用系统相机录制
     */
    public void reconverIntent() {
        //开启动态权限
        //获取读写拍照的动态权限
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        } else {
            try {
                //有权限,去打开摄像头
                takePhoto();
//                Intent intent = new Intent(this, CustomRecordActivity.class);
//                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*Uri fileUri = Uri.fromFile(getOutputMediaFile());
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);     //限制的录制时长 以秒为单位
//        intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 1024);        //限制视频文件大小 以字节为单位
//        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);      //设置拍摄的质量0~1
//        intent.putExtra(MediaStore.EXTRA_FULL_SCREEN, false);        // 全屏设置
        startActivityForResult(intent, RECORD_SYSTEM_VIDEO);*/

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    //有权限,去调取摄像头
                    takePhoto();
//                    Intent intent = new Intent(this, CustomRecordActivity.class);
//                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //"权限拒绝");
                // TODO: 2018/12/4 这里可以给用户一个提示,请求权限被拒绝了
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void takePhoto() {
//        Uri fileUri = Uri.fromFile(getOutputMediaFile());
        Uri fileUri = FileProvider.getUriForFile(Mic.getmCtx(), "com.jay.appdemo1.fileProvider", getOutputMediaFile());
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);     //限制的录制时长 以秒为单位
//        intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 1024);        //限制视频文件大小 以字节为单位
//        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);      //设置拍摄的质量0~1
//        intent.putExtra(MediaStore.EXTRA_FULL_SCREEN, false);        // 全屏设置
        startActivityForResult(intent, RECORD_SYSTEM_VIDEO);
    }

    /**
     * Create a File for saving an video
     */
    private File getOutputMediaFile() {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Toast.makeText(this, "请检查SDCard！", Toast.LENGTH_SHORT).show();
            return null;
        }

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "MyCameraApp");
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case RECORD_SYSTEM_VIDEO:
                //获取视频存储路径
                if (data != null) {
                    Uri url = data.getData();
                    LOGUtils.Log("url:" + url);
                    //开启拍摄视频浏览界面
                    Intent intent = new Intent(this, PhotoActivity.class);
                    intent.putExtra("url", url.toString());
                    startActivity(intent);
                    //将视频路径保存到本地数据库中


                }
                break;

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LOGUtils.Log("重新启动页面");
        if (currentFragment instanceof Home_Fragment) {
            mRg.check(R.id.day_check);
        }else if(currentFragment instanceof Hot_drop_Fragment){
            mRg.check(R.id.find);
        }else if (currentFragment instanceof Find_Fragment){
            mRg.check(R.id.hot_drop);
        }else if (currentFragment instanceof Mine_Fragment){
            mRg.check(R.id.mine);
        }
    }
}
