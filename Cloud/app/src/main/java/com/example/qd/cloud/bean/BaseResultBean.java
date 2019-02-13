package com.example.qd.cloud.bean;

/**
 * Created by DIY on 2017/5/3. 17:26
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:接口返回结果基类
 */

public class BaseResultBean<T> {
    private int code;
    private String msg;
    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
