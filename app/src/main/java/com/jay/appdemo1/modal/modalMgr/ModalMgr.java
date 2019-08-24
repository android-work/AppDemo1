package com.jay.appdemo1.modal.modalMgr;

import com.jay.appdemo1.modal.HistoryModal;
import com.jay.appdemo1.modal.Hot_model;
import com.jay.appdemo1.modal.find.Find_class_model;
import com.jay.appdemo1.modal.find.Find_focus_model;
import com.jay.appdemo1.modal.find.Find_getClass_model;
import com.jay.appdemo1.modal.home.Home_listview_modal;
import com.jay.appdemo1.modal.home.Home_viewpagr_modal;
import com.jay.appdemo1.modal.home.Rank_model;
import com.jay.appdemo1.ui.activity.HistoryViewActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/12/9.
 */

public class ModalMgr {

    /**管理一些modal对象*/
    private Home_viewpagr_modal mHome_viewpager_modal;
    private Home_listview_modal mHome_listview_modal;
    private Find_focus_model mFind_focus_model;
    private Hot_model mHot_model;
    private ArrayList<Find_class_model> class_model_list = new ArrayList<>();
    private HashMap<Integer,String> getHashMap_title = new HashMap<>();
    private HashMap<String,Find_getClass_model.ItemListBeanX> getClass_modelHashMap = new HashMap<>();
    private HashMap<String,Rank_model> rank_modelHashMap = new HashMap<>();
    private static ModalMgr modalMgr;
    private ModalMgr(){}

    public static ModalMgr getInstance(){
        if (modalMgr==null){
            synchronized (ModalMgr.class){
                if (modalMgr==null){
                    modalMgr=new ModalMgr();
                }
            }
        }
        return modalMgr;
    }

    public HashMap<String, Rank_model> getRank_modelHashMap() {
        return rank_modelHashMap;
    }

    public Hot_model getmHot_model() {
        return mHot_model;
    }

    public void setmHot_model(Hot_model mHot_model) {
        this.mHot_model = mHot_model;
    }

    public HashMap<Integer, String> getGetHashMap_title() {
        return getHashMap_title;
    }

    public HashMap<String, Find_getClass_model.ItemListBeanX> getGetClass_modelHashMap() {
        return getClass_modelHashMap;
    }

    public ArrayList<Find_class_model> getClass_model_list() {
        return class_model_list;
    }

    public void setClass_model_list(ArrayList<Find_class_model> class_model_list) {
        this.class_model_list = class_model_list;
    }

    public Home_viewpagr_modal getHome_modal() {
        return mHome_viewpager_modal;
    }

    public void setHome_modal(Home_viewpagr_modal home_modal) {
        this.mHome_viewpager_modal = home_modal;
    }

    public Home_listview_modal getmHome_listview_modal() {
        return mHome_listview_modal;
    }

    public void setmHome_listview_modal(Home_listview_modal mHome_listview_modal) {
        this.mHome_listview_modal = mHome_listview_modal;
    }

    public Find_focus_model getmFind_focus_model() {
        return mFind_focus_model;
    }

    public void setmFind_focus_model(Find_focus_model mFind_focus_model) {
        this.mFind_focus_model = mFind_focus_model;
    }
}
