package com.example.qd.cloud.api;

import android.content.Context;

import com.example.qd.cloud.bean.BaseResultBean;
import com.example.qd.cloud.bean.UserInfoBean;
import com.example.qd.cloud.interfaces.ICallBack;
import com.example.qd.cloud.service.BuildService;
import com.example.qd.cloud.service.RequestCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by DIY on 2018-10-19. 09:58
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class RequestApi {

    public static void login(Context mContext, Map<String, Object> params
            , ICallBack<UserInfoBean> callBack, Boolean boolShow, String... title) {
        Observable<BaseResultBean<UserInfoBean>> mObservable = BuildService.getCloud().login(params);
        new RequestCallBack<UserInfoBean>().RXReqeust(mContext, mObservable, callBack, boolShow, title);
    }

    /***
     * 第三方登录
     * @param mContext
     * @param params
     * @param callBack
     * @param boolShow
     * @param title
     */
    public static void thirdLogin(Context mContext, Map<String, Object> params
            , ICallBack<UserInfoBean> callBack, Boolean boolShow, String... title) {
        Observable<BaseResultBean<UserInfoBean>> mObservable = BuildService.getCloud().login(params);
        new RequestCallBack<UserInfoBean>().RXReqeust(mContext, mObservable, callBack, boolShow, title);
    }

    private static MultipartBody.Part uploadFileBody(String name, File file) {
        MultipartBody.Part part = null;
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        part = MultipartBody.Part.createFormData(name, file.getName(), requestBody);
        return part;
    }


    private static List<MultipartBody.Part> uploadFileMap(Map<String, File> params) {
        List<MultipartBody.Part> parts = new ArrayList<>(params.size());
        for (Map.Entry<String, File> stringObjectEntry : params.entrySet()) {//没有判空
            String key = stringObjectEntry.getKey();
            Object value = stringObjectEntry.getValue();
            if (value instanceof File) {//如果请求的值是文件
                File file = (File) value;
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                parts.add(part);
            }
        }
        return parts;
    }
}
