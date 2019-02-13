package com.example.qd.cloud.bean;

/**
 * Created by DIY on 2017/5/4. 14:25
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class MessageEvent {
    public final static int EVENT_REFRESH_DISCOVER=0x001;

    private int type;
    private Object message;

    public MessageEvent(int type, Object message){
        this.type=type;
        this.message=message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getMessage() {
        return message;
    }
    public void setMessage(Object message) {
        this.message = message;
    }
}
