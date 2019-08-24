package com.jay.appdemo1.adapter.hot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.jay.appdemo1.ui.fragment.Hot_drop_Fragment;
import com.jay.appdemo1.ui.fragment.hot_fragment.Rank_Fragment;
import com.jay.appdemo1.utils.LOGUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by liuzhi on 2019/2/16.
 */

public class Hot_Adapter extends FragmentPagerAdapter {


    private HashMap<Integer, Rank_Fragment> rankFragmentHashMap;
    private int size;

    @Override
    public CharSequence getPageTitle(int position) {
        return Hot_drop_Fragment.tabList.get(position).getName();
    }

    public Hot_Adapter(FragmentManager fm, HashMap<Integer, Rank_Fragment> rankFragmentHashMap) {
        super(fm);
        this.rankFragmentHashMap = rankFragmentHashMap;
        LOGUtils.Log("rankFragmentHashMap:"+rankFragmentHashMap.size());
    }

    @Override
    public Fragment getItem(int position) {
        return rankFragmentHashMap.get(position);
    }

    @Override
    public int getCount() {
        return rankFragmentHashMap.size();
    }
}
