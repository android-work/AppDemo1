package com.jay.appdemo1.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.cheak.CheakLoading;
import com.jay.appdemo1.db.DbOperation;
import com.jay.appdemo1.interface_.CallBack_nameLisence;
import com.jay.appdemo1.photo.DraftActivity;
import com.jay.appdemo1.photo.PreViewActivity;
import com.jay.appdemo1.ui.activity.AppDetail;
import com.jay.appdemo1.ui.activity.HistoryViewActivity;
import com.jay.appdemo1.ui.activity.LoadingActivity;
import com.jay.appdemo1.ui.activity.MineFocusActivity;
import com.jay.appdemo1.ui.activity.UserDetailActivity;
import com.jay.appdemo1.ui.view.CricleImageView;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.ToastUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

/**
 * Created by liuzhi on 2018/12/6.
 */

public class Mine_Fragment extends BaseFragment implements View.OnClickListener {

    private static final int RESULT_SUCCESS_PHOTO=0;//获取图片成功结果码
    private static final int RESULT_CROP_CODE = 1;//获取裁剪之后的结果
    private static final int RESULT_PERMISSION_CODE=100;//定义权限申请请求码
    private static final int LOADING = 2 ;//登录成功的状态返回码
    private Handler mHandler;
    private View view;
    private ImageView help;
    private CricleImageView mine_head;
    private TextView collection;
    private TextView comment;
    private TextView mime_handler;
    private TextView mine_cache;
    private TextView mine_focus;
    private TextView view_record;
    private TextView suggestion;
    private RelativeLayout camera_rl;
    private LinearLayout camera_ll;
    private TextView from_photo;
    private TextView from_camera;
    private TextView cancel;
    private int screenHeight;
    private boolean tag=false;
    //要申请的权限
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
    //存放未申请的权限
    private ArrayList<String> noPermissions = new ArrayList<>();
    private AlertDialog.Builder builder;
    private int user_state;
    private TextView mine_name;
    private String name;
    private HashMap<String, String> user_map;
    private Intent work;
    public static String user_name;

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.mine_layout, null);

        help = view.findViewById(R.id.help);

//        view.findViewById(R.id.collection).setOnClickListener(this);
//        view.findViewById(R.id.comment).setOnClickListener(this);
        view.findViewById(R.id.mime_works).setOnClickListener(this);
        view.findViewById(R.id.mime_cache).setOnClickListener(this);
        view.findViewById(R.id.mime_focus).setOnClickListener(this);
        view.findViewById(R.id.suggestion).setOnClickListener(this);
        view.findViewById(R.id.user_detail).setOnClickListener(this);

        mine_name = view.findViewById(R.id.mine_name);
        mine_head = view.findViewById(R.id.mine_head);
        view_record = view.findViewById(R.id.view_record);
        camera_rl = view.findViewById(R.id.camera_rl);
        camera_ll = view.findViewById(R.id.camera_ll);//相册或者拍照选择布局
        from_photo = view.findViewById(R.id.from_photo);//从相册中选择
        from_camera = view.findViewById(R.id.from_camera);//选择拍照
        cancel = view.findViewById(R.id.cancel);//取消

        switch (user_state){
            case 1:
            case -1:
                mine_name.setText("未登录");
                mine_head.setImageResource(R.mipmap.ic_launcher);
                break;
            case 0:
                //登录，读取用户信息，显示
                HashMap<String, String> userMap = DbOperation.getInstance().dbQuery_user();
                if (userMap!=null && userMap.size()>0){
                    user_name = userMap.get("user_name");
                    mine_name.setText(user_name);
                }
                break;
        }

        view_record.setOnClickListener(this);
        help.setOnClickListener(this);
        mine_head.setOnClickListener(this);
        from_camera.setOnClickListener(this);
        from_photo.setOnClickListener(this);
        cancel.setOnClickListener(this);
        mine_name.setOnClickListener(this);

        if (!tag)
            camera_ll.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void loadData(Handler handler) {
        mHandler = handler;
        Message msg = Message.obtain();
        msg.what=0;
        msg.obj="Aric";
        handler.sendMessage(msg);
    }

    @Override
    protected void parseData(String data) {
        //检查是否登录账号
        user_state = CheakLoading.cheakIsLoading();

        mHandler.sendEmptyMessage(2);
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.help:
                //开启一个新的activity介绍一些关于该应用的一些东西
                Intent detail = new Intent(Mic.getmCtx(), AppDetail.class);
                detail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Mic.getmCtx().startActivity(detail);
                break;
            case R.id.mine_head:
                LOGUtils.Log("点击了头像");
                user_map = DbOperation.getInstance().dbQuery_user();
                if (user_map==null || user_map.size()==0 ||user_map.get("uid")==null || user_map.get("uid").equals("")) {
                    ToastUtils.show("请用户登录");
                    return;
                }
                // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 检查该权限是否已经获取
                    noPermissions.clear();
                    for (int i=0;i<permissions.length;i++){
                        if (ContextCompat.checkSelfPermission(Mic.getmCtx(),permissions[i])!=PackageManager.PERMISSION_GRANTED){
                            //检查到有没有申请的权限
                            noPermissions.add(permissions[i]);
                            LOGUtils.Log("添加未申请的权限"+permissions[i]);
                        }
                    }
                    //申请未申请的权限
                    if (noPermissions.size()>0){
                        LOGUtils.Log("申请未申请的权限");
                        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},RESULT_PERMISSION_CODE);
                    }else{
                        //从相册中获取图片更换头像
                        tag = !tag;
                        initAnimation(tag);
                    }

                }else{
                    //从相册中获取图片更换头像
                    tag = !tag;
                    initAnimation(tag);
                }
                break;
            case R.id.from_camera://从相册中选择
                ToastUtils.show("选择拍照");

                break;
            case R.id.from_photo://选择拍照
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,RESULT_SUCCESS_PHOTO);
                break;
            case R.id.cancel://取消更换头像
                ToastUtils.show("取消");
                break;
            case R.id.view_record:
                user_map = DbOperation.getInstance().dbQuery_user();
                if (user_map==null || user_map.size()==0 ||user_map.get("uid")==null || user_map.get("uid").equals("")) {
                    ToastUtils.show("请用户登录");
                    return;
                }
                //开启一个新的activity用来记录观看过的视频
                Intent intents = new Intent(context, HistoryViewActivity.class);
                intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intents);
                break;
            case R.id.mime_cache:
                //本地草稿箱
                LOGUtils.Log("点击了本地草稿箱");
                user_map = DbOperation.getInstance().dbQuery_user();
                if (user_map==null || user_map.size()==0 ||user_map.get("uid")==null || user_map.get("uid").equals("")) {
                    ToastUtils.show("请用户登录");
                    return;
                }
                work = new Intent(Mic.getmCtx(), DraftActivity.class);
                work.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(work);
//                ToastUtils.show("我的缓存");
                break;
            case R.id.mine_name:
                /**点击用户名登录,
                 * 如果未登录/未注册跳转到登录页面
                 * 如果登录了显示用户的昵称和头像
                 * 登录成功，回调返回昵称方法*/
                if (CheakLoading.cheakIsLoading()!=0){
                    CheakLoading.setCallBack_nameLisence(new CallBack_nameLisence() {

                        @Override
                        public void getName(String name) {
                            Mine_Fragment.this.name = name;
                            //登录成功后，将用户昵称设置给mine_name
                            mine_name.setText(name);
                        }
                    });
                    Intent load = new Intent(Mic.getmCtx(), LoadingActivity.class);
                    load.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(load);
                }

                break;
            case R.id.user_detail:

                //进入个人详情页,当重新设置用户名时，更改ui界面
                UserDetailActivity.setCallBack_nameLitener(new CallBack_nameLisence() {
                    @Override
                    public void getName(String name) {
                        mine_name.setText(name);
                    }
                });
                if (CheakLoading.cheakIsLoading()==0){
                    Intent userDetail = new Intent(Mic.getmCtx(), UserDetailActivity.class);
                    userDetail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(userDetail);
                }
                break;
            case R.id.mime_works:
                //我的作品
                user_map = DbOperation.getInstance().dbQuery_user();
                if (user_map==null || user_map.size()==0 ||user_map.get("uid")==null || user_map.get("uid").equals("")) {
                    ToastUtils.show("请用户登录");
                    return;
                }
                work = new Intent(Mic.getmCtx(),PreViewActivity.class);
                work.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(work);

                break;
            /*case R.id.collection:
                ToastUtils.show("收藏");
                break;
            case R.id.comment:
                ToastUtils.show("评论");
                break;*/
            case R.id.mime_focus:
                //启动一个新的页面通过listview来展示我关注的作者
                user_map = DbOperation.getInstance().dbQuery_user();
                if (user_map ==null || user_map.size()==0 || user_map.get("uid")==null || user_map.get("uid").equals("")) {
                    ToastUtils.show("请用户登录");
                    return;
                }
                Intent focus = new Intent(Mic.getmCtx(), MineFocusActivity.class);
                focus.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(focus);
                break;
            case R.id.suggestion:

                user_map = DbOperation.getInstance().dbQuery_user();
                if (user_map ==null || user_map.size()==0 || user_map.get("uid")==null || user_map.get("uid").equals("")) {
                    ToastUtils.show("请用户登录");
                    return;
                }
                //弹出一个对话框供用户输入意见反馈
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View inflate = View.inflate(Mic.getmCtx(), R.layout.give_miss_layout, null);
                final EditText miss_edit = inflate.findViewById(R.id.miss_edit);
                Button miss_btn = inflate.findViewById(R.id.miss_btn);
                miss_edit.setHint("输入您的意见反馈");
                builder.setView(inflate);

                final AlertDialog show = builder.show();

                miss_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (miss_edit.getText().toString()!=null && !miss_edit.getText().toString().equals("")){
                            show.dismiss();
                            ToastUtils.show("提交成功");
                        }else{
                            ToastUtils.show("评论区不能提交空数据");
                        }

                        LOGUtils.Log("==================");
                    }
                });

                break;
        }
    }




    /**申请权限的回掉*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasNoPermission = false;//是否有权限没有通过申请
        LOGUtils.Log("进入权限申请回掉");
        if (requestCode == RESULT_PERMISSION_CODE){
            for (int i=0;i<grantResults.length;i++){
                if (grantResults[i]==PackageManager.PERMISSION_DENIED){
                    hasNoPermission=true;
                    LOGUtils.Log("有未申请的权限");
                }
            }
            //如果有权限没有通过申请
            if (hasNoPermission){
                //弹对话框使之开启，否则无法使用
                builder = new AlertDialog.Builder(Mic.getmCtx());
                builder.setTitle("应用所需权限未全部开启，功能无法正常使用");
                /*builder.setPositiveButton("去开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //跳转到设置页面进行开启权限
                        dialogInterface.dismiss();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setCancelable(false);*/
                builder.create();
                builder.show();
            }else{
                //从相册中获取图片更换头像
                LOGUtils.Log("没有未申请的权限");
                tag = !tag;
                initAnimation(tag);
            }
        }
    }

    /**执行创建头像的布局动画效果
     * @param tag*/
    private void initAnimation(boolean tag) {
        TranslateAnimation translateAnimation;
        if (tag) {
            translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0,
                    TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 1, TranslateAnimation.RELATIVE_TO_SELF, 0);
            translateAnimation.setDuration(500);
            translateAnimation.setFillAfter(true);
            camera_ll.setVisibility(View.VISIBLE);
        }else{
            translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0,
                    TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 1);
            translateAnimation.setDuration(500);
            translateAnimation.setFillAfter(true);
            camera_ll.setVisibility(View.VISIBLE);
        }
        camera_ll.startAnimation(translateAnimation);
    }

    /**开启activity并返回一个之*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_OK){
            switch (resultCode){
                case RESULT_CROP_CODE:

                    // 拿到剪切数据
                    Bitmap bmap = data.getParcelableExtra("data");// 裁剪过后的bitmap
                    // 压缩
//                    Bitmap newBmap = compressImage(bmap);
                    // 展示到了图片上
                    mine_head.setImageBitmap(bmap);
                    //如果要上传 bitmap转为二进制流上传，也可以直接保存成为一个file进行上传。
                    break;
                case RESULT_SUCCESS_PHOTO:
                    if (data!=null) {
                        Uri uri = data.getData();
                        cropPic(uri);
                    }
                    break;
            }
        }
    }

    /**调用手机原生的裁剪工具*/
    protected void cropPic(Uri uri) {
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");// mUri是已经选择的图片Uri
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);// 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);// 输出图片大小
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_CROP_CODE);
    }

    /**压缩图片*/
    public Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100 && options > 10) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }


}
