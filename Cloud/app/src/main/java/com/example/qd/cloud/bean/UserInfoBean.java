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
    private String uid;
    private String name;
    private int age;
    private String sex;
    private String like;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
