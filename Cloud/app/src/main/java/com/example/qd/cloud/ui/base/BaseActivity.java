package com.example.qd.cloud.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.qd.cloud.R;
import com.example.qd.cloud.bean.UserInfoBean;
import com.example.qd.cloud.ui.communal.AppManager;
import com.example.qd.cloud.utils.StatusBarUtils;

import java.util.Map;

import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * author: wu
 * date: on 2019/2/11.
 * describe:BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected Intent mIntent;
    protected Map<String, Object> requestParams;

    /**
     * Store user login information.
     */
    protected Realm mRealm = null;

    /**
     * page
     */
    protected int PAGE_INDEX = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        realmInit();
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        init();
        setContentView(provideContentViewId());//布局
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
//        StatusBarUtils.setWindowStatusBarColor(this, ContextCompat.getColor(mContext,R.color.white));
//        StatusBarUtils.setStatusTextColor(true,this);
        //设置状态栏背景色
        StatusBarUtils.setStatusBar(this,R.color.white);
    }

    protected abstract int provideContentViewId();//用于引入布局文件

    /***
     * 在初始化布局之前操作
     */
    protected abstract void init();

    /***
     * 布局初始化完成后控件初始化
     */
    protected abstract void initView();

    /***
     * 在初始化布局之前操作
     */
    protected abstract void initData();

    /***
     * 事件
     */
    protected abstract void initListener();

    /**
     * Store user login information.
     */
    protected void realmInit() {
        mRealm = Realm.getDefaultInstance();
    }

    protected UserInfoBean getUserInfo(int userId) {
        return mRealm.where(UserInfoBean.class).equalTo("uid", userId).findFirst();
    }

    protected UserInfoBean getUserInfo() {
        return mRealm.where(UserInfoBean.class).findFirst();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
