package com.example.qd.cloud.api;

import com.example.qd.cloud.utils.AppConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DIY on 2018-10-19. 10:06
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class RequestParams {

    public static Map<String, Object> getRequestParamsMap(String action) {
        Map<String, Object> map = new HashMap<>();
        map.put("ver", AppConfig.VER);
        map.put("action",action);
        return map;
    }

}
