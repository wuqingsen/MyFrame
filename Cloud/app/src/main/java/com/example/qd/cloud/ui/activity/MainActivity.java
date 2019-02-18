package com.example.qd.cloud.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.qd.cloud.R;
import com.example.qd.cloud.bean.UserInfoBean;
import com.example.qd.cloud.ui.adapter.MainAdapter;
import com.example.qd.cloud.ui.base.BaseActivity;
import com.example.qd.cloud.utils.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * realm
 */

public class MainActivity extends BaseActivity {
    private UserInfoBean mUserInfoBeans;
    private RealmHelper mRealmHelper;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
    }

    @Override
    protected void initView() {
        mUserInfoBeans = new UserInfoBean();
        mUserInfoBeans.setUid("001");
        mUserInfoBeans.setName("Bob");
        mUserInfoBeans.setAge(20);
        mUserInfoBeans.setLike("吃喝玩乐");
        mUserInfoBeans.setSex("男");
        mRealmHelper = new RealmHelper(mContext);
    }

    @Override
    protected void initData() {
        /**
         * 增
         */
        mRealmHelper.addUserInfo(mUserInfoBeans);

        /**
         * 改
         */
        mRealmHelper.updateUserInfo("001","娜娜");

        /**
         * 查
         */
        mUserInfoBeans = mRealmHelper.queryUserInfoById("001");
        Log.e("=====uid", mUserInfoBeans.getUid());
        Log.e("=====name", mUserInfoBeans.getName());
        Log.e("=====sex", mUserInfoBeans.getSex());
        Log.e("=====age", mUserInfoBeans.getAge()+"");
        Log.e("=====like", mUserInfoBeans.getLike());
    }

    @Override
    protected void initListener() {

    }
}
