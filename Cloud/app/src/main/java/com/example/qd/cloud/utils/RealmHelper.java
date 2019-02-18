package com.example.qd.cloud.utils;

import android.content.Context;

import com.example.qd.cloud.bean.UserInfoBean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * author: wu
 * date: on 2019/2/11.
 * describe: RealmHelper
 */

public class RealmHelper {
    public static final String DB_NAME = "myRealm.realm";
    private Realm mRealm;

    public RealmHelper(Context context) {
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * add （增）
     */
    public void addUserInfo(final UserInfoBean userInfoBean) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(userInfoBean);
        mRealm.commitTransaction();

    }

    /**
     * delete （删）
     */
    public void deleteUserInfo(String id) {
        UserInfoBean userInfoBean = mRealm.where(UserInfoBean.class).equalTo("uid", id).findFirst();
        mRealm.beginTransaction();
        userInfoBean.deleteFromRealm();
        mRealm.commitTransaction();

    }

    /**
     * update （改）
     */
    public void updateUserInfo(String id, String newName) {
        UserInfoBean userInfoBean = mRealm.where(UserInfoBean.class).equalTo("uid", id).findFirst();
        mRealm.beginTransaction();
        userInfoBean.setName(newName);
        mRealm.commitTransaction();
    }

    /**
     * query （查询所有）
     */
    public List<UserInfoBean> queryAllUserInfo() {
        RealmResults<UserInfoBean> userInfoBeans = mRealm.where(UserInfoBean.class).findAll();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
        //增序排列
        userInfoBeans = userInfoBeans.sort("id");
//        //降序排列
//        dogs=dogs.sort("id", Sort.DESCENDING);
        return mRealm.copyFromRealm(userInfoBeans);
    }

    /**
     * query （根据Id（主键）查）
     */
    public UserInfoBean queryUserInfoById(String id) {
        UserInfoBean userInfoBean = mRealm.where(UserInfoBean.class).equalTo("uid", id).findFirst();
        return userInfoBean;
    }


    /**
     * query （根据age查）
     */
    public List<UserInfoBean> queryUserInfoByAge(int age) {
        RealmResults<UserInfoBean> userInfoBeans = mRealm.where(UserInfoBean.class).equalTo("age", age).findAll();
        return mRealm.copyFromRealm(userInfoBeans);
    }

    public boolean isUserInfoExist(String id) {
        UserInfoBean userInfoBean = mRealm.where(UserInfoBean.class).equalTo("uid", id).findFirst();
        if (userInfoBean == null) {
            return false;
        } else {
            return true;
        }
    }

    public Realm getRealm() {
        return mRealm;
    }

    public void close() {
        if (mRealm != null) {
            mRealm.close();
        }
    }
}
