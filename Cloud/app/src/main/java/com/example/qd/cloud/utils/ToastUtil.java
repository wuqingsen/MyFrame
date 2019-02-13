package com.example.qd.cloud.utils;

import android.content.Context;
import android.widget.Toast;


public class ToastUtil {
    public static void show(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, int info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, String info,int duraction) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }
    public static void show(Context context,int info,int duraction){
        Toast.makeText(context, info, duraction).show();
    }
}
