package com.example.qd.cloud.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by DIY on 2017/6/21. 15:41
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class FragmentMessage {
    static FragmentManager manager;
    static FragmentTransaction transaction;
    static Fragment fragment;

    /**
     * 切换Fragment
     * 切换Fragment
     * <? extends Fragment> c
     *
     * @return 无
     */
    public static void toggleFragment(AppCompatActivity act, Class<? extends Fragment> currentFragment, int fragmentId) {
        manager = act.getSupportFragmentManager();
        String strCurrentFragmentName = currentFragment.getName();
        transaction = manager.beginTransaction();
        fragment = manager.findFragmentByTag(strCurrentFragmentName);
        if (fragment == null) {
            try {
                fragment = currentFragment.newInstance();
                // 替换时保留Fragment,以便复用
                transaction.add(fragmentId, fragment,
                        strCurrentFragmentName);
            } catch (Exception e) {
                // ignore
            }
        }
        // 遍历存在的Fragment,隐藏其他Fragment
        List<Fragment> fragments = manager.getFragments();
        if (fragments != null)
            for (Fragment fm : fragments)
                if (!fm.equals(fragment))
                    transaction.hide(fm);
        transaction.show(fragment);
        transaction.commit();
    }
}
