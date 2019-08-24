package com.jay.appdemo1.ui.fragment;

import com.jay.appdemo1.utils.LOGUtils;

import java.util.HashMap;

/**
 * Created by liuzhi on 2018/12/6.
 */

public class FragmentFactory {

   //创建一个Map集合存放创建好的Fragment
    private static HashMap<Integer,BaseFragment> mFragmentMap = new HashMap<Integer,BaseFragment>();

    /**创建fragment*/
    public static BaseFragment createFragment(int position){

        BaseFragment mBaseFragment=null;

        if (mFragmentMap.get(position)!=null){

            LOGUtils.Log("从集合种获取");
            //集合中存在对应的Fragment，则从集合中获取
            mBaseFragment=mFragmentMap.get(position);

        }else{
            LOGUtils.Log("直接new获取");
            //集合中没有则创建一个新的，并存放到集合中
            switch (position){

                case 0:
                    mBaseFragment = new Home_Fragment();
                    break;
                case 1:
                    mBaseFragment = new Find_Fragment();
                    break;
                case 2:
                    mBaseFragment = new Hot_drop_Fragment();
                    break;
                case 3:
                    mBaseFragment = new Mine_Fragment();
                    break;
            }

            LOGUtils.Log("create home_fragment"+mBaseFragment);

            mFragmentMap.put(position,mBaseFragment);

            LOGUtils.Log(mFragmentMap+"");
        }
        return mBaseFragment;
    }
}
