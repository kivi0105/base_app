package com.kivi.base_app_lib.base;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;

import com.kivi.base_app_lib.receiver.NetworkChangedReceiver;

/**
 * @description: Created by kivi on 2017/9/14.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initListener();
    }

    protected void initListener(){
        //7.0后，移除CONNECTIVITY_ACTION隐式广播，需动态注册
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NetworkChangedReceiver.register(this);
        }

    }


}


