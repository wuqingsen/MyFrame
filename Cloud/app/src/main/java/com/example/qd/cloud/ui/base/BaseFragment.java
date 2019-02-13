package com.example.qd.cloud.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qd.cloud.bean.MessageEvent;
import com.example.qd.cloud.bean.UserInfoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by DIY on 2017/5/3. 17:23
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    protected Intent mIntent;
    protected View rootView;
    protected Map<String, Object> requestParams;
    protected Boolean isRefresh = true;//是否刷新
    protected int PAGE_INDEX = 1;//当前页码只有在列表中用到
    protected Realm mRealm = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        RealmInit();
        init();
        if (rootView == null) {
            rootView = inflater.inflate(createViewLayoutId(), container, false);
        }
        ButterKnife.bind(this, rootView);
        initView(rootView);
        initData();
        initListener();
        return rootView;
    }


    protected abstract int createViewLayoutId();

    protected abstract void onCreate();

    protected abstract void init();

    protected abstract void initView(View rootView);

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void messageHandler(MessageEvent event);


    public Boolean isSetRefresh() {
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    protected void RealmInit() {
        mRealm = Realm.getDefaultInstance();
    }

    protected UserInfoBean getUserInfo(int userId) {
        return mRealm.where(UserInfoBean.class).equalTo("uid", userId).findFirst();
    }

    protected UserInfoBean getUserInfo() {
        return mRealm.where(UserInfoBean.class).findFirst();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        messageHandler(event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
