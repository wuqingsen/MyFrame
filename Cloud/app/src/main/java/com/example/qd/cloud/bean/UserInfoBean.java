package com.example.qd.cloud.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * author: wu
 * date: on 2019/2/11.
 * describe:Store user login information.
 */
public class UserInfoBean extends RealmObject {
    @PrimaryKey
    private int uid;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
