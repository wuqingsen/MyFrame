package com.example.qd.cloud.service;

import com.example.qd.cloud.bean.BaseResultBean;
import com.example.qd.cloud.bean.UserInfoBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by DIY on 2017/04/17
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("webapi/api.ashx")
    Observable<BaseResultBean<String>> commonApiString(
            @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("webapi/api.ashx")
    Observable<BaseResultBean<UserInfoBean>> login(
            @FieldMap Map<String, Object> map);
}

