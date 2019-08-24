package com.jay.appdemo1.adapter.find;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jay.appdemo1.R;
import com.jay.appdemo1.utils.LOGUtils;

import java.util.Map;

/**
 * Created by liuzhi on 2019/1/3.
 */

public class Find_viewpager extends FragmentPagerAdapter {

    private FragmentManager fm;
    private Map<Integer, Fragment> find_fragment_map;
    private Context mCtx;

    public Find_viewpager(FragmentManager fm, Map<Integer, Fragment> find_fragment_map, Context context) {
        super(fm);
        this.fm = fm;
        this.find_fragment_map = find_fragment_map;
        mCtx = context;
        LOGUtils.Log("find_viewpager");

    }

    @Override
    public Fragment getItem(int position) {
        return find_fragment_map.get(position);
    }

    @Override
    public int getCount() {
        return find_fragment_map.size();
    }

}
