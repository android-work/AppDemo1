package com.jay.appdemo1.adapter.home;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.LruCache;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jay.appdemo1.Mic;
import com.jay.appdemo1.R;
import com.jay.appdemo1.modal.home.Home_viewpagr_modal;
import com.jay.appdemo1.modal.home.Home_listview_modal;
import com.jay.appdemo1.modal.modalMgr.ModalMgr;
import com.jay.appdemo1.ui.view.CricleImageView;
import com.jay.appdemo1.utils.LOGUtils;
import com.jay.appdemo1.utils.xutils.BitmapUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/12/9.
 */

public class Home_fragment_adapter extends BaseAdapter implements ViewPager.OnPageChangeListener{

    private ArrayList<Home_listview_modal.home1_listview.home2_listview> arrayList = new ArrayList<>();
    private ArrayList<ImageView> indicator = new ArrayList<>();
    private ArrayList<String> vp_tv_des=new ArrayList<>();
    private Handler handler;
    private ViewHolderVp viewHolderVp;


    public Home_fragment_adapter(Handler handler){
        this.handler = handler;
        arrayList=ModalMgr.getInstance().getmHome_listview_modal().getIssueList().get(0).getItemList();
    }

    /**获取当前条目的view类型(2->表示正常条目,1->表示常驻悬浮框,0->表示viewPager)*/
    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getType().equals("textHeader"))
            return 1;
        else if (getItem(position).getType().equals("video"))
            return 2;
        else
            return 0;
    }

    /**获取整个listview中包含多少个view类型（纯文本/图片+文本）*/
    @Override
    public int getViewTypeCount() {
//        LOGUtils.Log("view的类型个数:"+(super.getViewTypeCount()+2));
        return super.getViewTypeCount()+2;
    }

    @Override
    public int getCount() {

        return arrayList.size();
    }

    @Override
    public Home_listview_modal.home1_listview.home2_listview getItem(int i) {
//        LOGUtils.Log("当前位置:"+i+"::"+arrayList);
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        String des = "";
        View itemView=null;

        //当条目的位置为0的时候，展示viewpager
        if (getItemViewType(i)==0){

            LOGUtils.Log("当前位置展现viewpager："+i);
            LOGUtils.Log(getItem(i).getData().getTitle());
            viewHolderVp = null;

            if (view==null){
                view=View.inflate(Mic.getmCtx(),R.layout.home_listview_viewpager,null);
                viewHolderVp=new ViewHolderVp();
                viewHolderVp.home_listview_vp = view.findViewById(R.id.home_listview_vp);
                viewHolderVp.vp_tv_iv = view.findViewById(R.id.vp_tv_iv);
                viewHolderVp.vp_des = view.findViewById(R.id.vp_des);
                view.setTag(viewHolderVp);

                //准备textview，圆点指示器的数据
                prepareData();

            }
            itemView = view;
            //循环轮播
            handler.postDelayed(new r(),5000);
            viewHolderVp = (ViewHolderVp) view.getTag();
            viewHolderVp.home_listview_vp.setAdapter(new Home_listview_vp_adapter(Mic.getmCtx()));

            //设置viewpager的滚动监听
            viewHolderVp.home_listview_vp.setOnPageChangeListener(this);
            LOGUtils.Log("当前位置："+(Integer.MAX_VALUE/2-((Integer.MAX_VALUE/2)%vp_tv_des.size())));
            //设置当前的页面位置
            viewHolderVp.home_listview_vp.setCurrentItem(Integer.MAX_VALUE/2-((Integer.MAX_VALUE/2)%vp_tv_des.size()));
        }

        //纯文本条目
        else if (getItemViewType(i)==1 ){
            LOGUtils.Log("当前位置展现纯文本："+i);
            LOGUtils.Log(getItem(i).getData().getText());
            ViewHolderTv viewHolderTv = null;
            if (view==null){

                view=View.inflate(Mic.getmCtx(),R.layout.home_item_tv_layout,null);
                viewHolderTv=new ViewHolderTv();
                viewHolderTv.home_item_tv=view.findViewById(R.id.item_tv);
                view.setTag(viewHolderTv);
            }

            itemView=view;
            viewHolderTv= (ViewHolderTv) view.getTag();
            viewHolderTv.home_item_tv.setText(getItem(i).getData().getText());
        }//图片+文字条目
        else {
            LOGUtils.Log("当前位置正常条目："+i);
            LOGUtils.Log("=============="+getItem(i).getData().getText());
            ViewHolder viewHolder = null;
            if (view == null) {
                viewHolder=new ViewHolder();
                view = View.inflate(Mic.getmCtx(), R.layout.home_item_layout, null);

                viewHolder.home_item_icon = view.findViewById(R.id.home_item_icon);
                viewHolder.home_item_iv = view.findViewById(R.id.home_item_iv);
                viewHolder.home_item_tv_des = view.findViewById(R.id.home_item_tv_des);
                viewHolder.home_item_tv_title = view.findViewById(R.id.home_item_tv_title);
                viewHolder.home_item_tv_sign = view.findViewById(R.id.home_item_tv_sign);

                view.setTag(viewHolder);
                viewHolder.home_item_iv.setTag(getItem(i).getData().getCover().getFeed());
                viewHolder.home_item_icon.setTag(getItem(i).getData().getAuthor().getIcon());
            }
            itemView = view;
            viewHolder = (ViewHolder) view.getTag();

            //展示title
            if (getItem(i).getType().equals("video")) {
                viewHolder.home_item_tv_title.setText(getItem(i).getData().getTitle());

                //拼接item的des字段
                for (int j = 0; j < (getItem(i).getData().getTags().size()) && (j < 4); j++) {
                    des += getItem(i).getData().getTags().get(j).getName() + "/";
                }

                //拼接item的des字段后面的时间
                String min = "";
                String second = "";
                if (getItem(i).getData().getDuration() / 60 < 10) {
                    min = "0" + getItem(i).getData().getDuration() / 60 + "\'";
                } else
                    min = getItem(i).getData().getDuration() / 60 + "\'";
                if (getItem(i).getData().getDuration() % 60 < 10) {
                    second = "0" + getItem(i).getData().getDuration() % 60 + "\"";
                } else
                    second = getItem(i).getData().getDuration() % 60 + "\"";

                //设置des字段的值给textview
                viewHolder.home_item_tv_des.setText("#" + des + min + second);

                viewHolder.home_item_tv_sign.setText("#" + getItem(i).getData().getCategory());

                BitmapUtil.getInstance().display(viewHolder.home_item_iv, getItem(i).getData().getCover().getFeed());
                BitmapUtil.getInstance().display(viewHolder.home_item_icon, getItem(i).getData().getAuthor().getIcon());

            }
        }
        return itemView;
    }

    /**===========================================准备viewpager的textview描述，小圆点指示器的数据===========================================================*/
    private void prepareData() {
        int count=0;
        ArrayList<Home_viewpagr_modal.home1.home2> vp_list = ModalMgr.getInstance().getHome_modal().getIssueList().get(0).getItemList();
        //准备指示器数据，以及textview数据
        for (int i=0;i<vp_list.size();i++){

            if (vp_list.get(i).getType().equals("video")){

                vp_tv_des.add(vp_list.get(i).getData().getTitle());

                LOGUtils.Log("count:"+count);

                ImageView imageView = new ImageView(Mic.getmCtx());
                LinearLayout.LayoutParams indicatorParams = new LinearLayout.LayoutParams
                        (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                if (count==0){
//                    LOGUtils.Log("当前位置："+count+":title:"+vp_tv_des.get(0));
                    imageView.setImageResource(R.drawable.select_indicator);
                    viewHolderVp.vp_des.setText(vp_tv_des.get(0));
                }else {
//                    LOGUtils.Log("当前位置："+i);
                    imageView.setImageResource(R.drawable.normal_indicator);
                }

                //给小圆点设置左右边距
                indicatorParams.leftMargin=10;
                indicatorParams.rightMargin=10;

                imageView.setLayoutParams(indicatorParams);

                viewHolderVp.vp_tv_iv.addView(imageView);
                indicator.add(imageView);

                count++;

            }
        }
    }

/**================================viewpager的页面监听回调=================================*/

   /**页面滚动回调*/
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**页面切换回调*/
    @Override
    public void onPageSelected(int position) {

        position%=vp_tv_des.size();

        LOGUtils.Log(indicator.size()+"滚动监听："+position);

        for (int i=0;i<indicator.size();i++){
            if (position==i){
                indicator.get(i).setImageResource(R.drawable.select_indicator);
            }else
                indicator.get(i).setImageResource(R.drawable.normal_indicator);
            viewHolderVp.vp_des.setText(vp_tv_des.get(position));
        }
    }

    /**页面状态改变回调*/
    @Override
    public void onPageScrollStateChanged(int state) {

    }

/**===================================listview的条目的ViewHolder=============================*/

    /**（图片+文字的item的viewholder）*/
    public static class ViewHolder{
        public ImageView home_item_iv;
        public CricleImageView home_item_icon;
        public TextView home_item_tv_title;
        public TextView home_item_tv_des;
        public TextView home_item_tv_sign;
    }

    /**（纯文字的item的viewholder）*/
    public static class ViewHolderTv{
        public TextView home_item_tv;
    }

    /**（viewpager的item的viewholder）*/
    public static class ViewHolderVp{
        public ViewPager home_listview_vp;
        public LinearLayout vp_tv_iv;
        public TextView vp_des;
    }

    /**================================自动轮播图片的任务======================================*/

    public class r implements Runnable{
        @Override
        public void run() {
            viewHolderVp.home_listview_vp.setCurrentItem(viewHolderVp.home_listview_vp.getCurrentItem()+1);
            handler.postDelayed(this,5000);

            viewHolderVp.home_listview_vp.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    switch (motionEvent.getAction()){
                        case MotionEvent.ACTION_UP:
                            handler.postDelayed(new r(),5000);
                            break;
                        case MotionEvent.ACTION_DOWN:
                            handler.removeCallbacksAndMessages(null);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            handler.removeCallbacksAndMessages(null);
                            break;
                    }
                    return false;
                }
            });
        }
    }

    /**======================================================处理listview上拉加载更多数据处理====================================================*/

    public void setArrayList(ArrayList<Home_listview_modal.home1_listview.home2_listview> array){

        for (int i=0;i<array.size();i++){
            LOGUtils.Log("新数据类型:"+array.get(i).getType());
            if ((array.get(i).getType().equals("video") ||array.get(i).getType().equals("textHeader"))) {
//                LOGUtils.Log("----------video||textHeader----------------");
                arrayList.add(array.get(i));
            }
        }
        LOGUtils.Log("上拉arrayList:"+arrayList);
        LOGUtils.Log("arrayList"+arrayList.size());
    }

    /**======================================================图片的异步请求====================================================*/

}
