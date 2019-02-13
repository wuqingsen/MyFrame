package com.example.qd.cloud.service;

import com.example.qd.cloud.bean.BaseResultBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by DIY on 2018-07-06. 11:39
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody,
        T> {
    private final Gson gson;
    private final Type type;
    private final static int SUCCESS_CODE=200;//接口返回功能标识
    private final static int PARAMETER_EMPTY=101;//参数不能为空
    private final static int PARAMETER_FORMAT=102;//参数格式不正确

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    /**
     * 针对数据返回成功、错误不同类型字段处理
     */
    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            BaseResultBean<T> result = gson.fromJson(response, BaseResultBean.class);
            int status = result.getCode();
            if (status == SUCCESS_CODE) {
                return gson.fromJson(response, type);
            } else {
                BaseResultBean<T> errResponse = gson.fromJson(response, BaseResultBean.class);
                throw new ResultException(errResponse.getMsg(), status);
            }
        } finally {
            value.close();
        }
    }
}
