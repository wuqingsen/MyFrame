package com.example.qd.cloud.ui.communal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.qd.cloud.ui.base.BaseActivity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Name:Wu.
 * Date:2018/11/14.
 * Describe：跳转工具类
 */

public class JumpActivity {

    public static void Jump(Context context, Class<? extends BaseActivity> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void Jump(Context context, Class<? extends BaseActivity> cls,
                            HashMap<String, ? extends Object> hashMap) {
        Intent intent = new Intent(context, cls);
        Iterator<?> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator
                    .next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                intent.putExtra(key, (String) value);
            }
            if (value instanceof Boolean) {
                intent.putExtra(key, (boolean) value);
            }
            if (value instanceof Integer) {
                intent.putExtra(key, (int) value);
            }
            if (value instanceof Float) {
                intent.putExtra(key, (float) value);
            }
            if (value instanceof Double) {
                intent.putExtra(key, (double) value);
            }
        }
        context.startActivity(intent);
    }


    public static void JumpBundle(Context context, Class<? extends BaseActivity> cls,
                            Bundle bundle) {
        Intent mIntent = new Intent(context, cls);
        mIntent.putExtras(bundle);
        context.startActivity(mIntent);
    }

    public static void JumpResult(Activity act, Class<? extends BaseActivity> cls,
                                  int result) {
        Intent mIntent = new Intent(act, cls);
        act.startActivityForResult(mIntent,result);
    }

}
