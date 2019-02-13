package com.example.qd.cloud.ui.communal;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * author: wu
 * date: on 2019/2/11.
 * describe:
 */
public class MyApplication extends Application {
    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("daxuetong.realm")//文件名
                .schemaVersion(1) //版本号
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public static Context getAppContext() {
        return appContext;
    }
}
