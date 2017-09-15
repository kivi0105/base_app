package com.kivi.base_app_lib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.util.Log;

import com.kivi.base_app_lib.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Created by kivi on 2017/9/14.
 */

public class NetworkChangedReceiver extends BroadcastReceiver {
    private static final String TAG = "NetworkChangedReceiver";

    private static List<NetworkChangedInterface> lists = new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: ");
        NetworkUtil.State state = NetworkUtil.getNetworkState(context);
        switch (state) {
            case MOBILE:
                Log.i(TAG, "onReceive: MOBILE");
                notifyMobileNet();
                break;
            case WIFI:
                Log.i(TAG, "onReceive: WIFI");
                notifyWifiNet();
                break;
            case UNCONNECTED:
                Log.i(TAG, "onReceive: UNCONNECTED");
                notifyUnconnectedNet();
                break;
        }

    }

    public static void register(Context context) {
        context.registerReceiver(new NetworkChangedReceiver(),
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public static void add(NetworkChangedInterface networkChangedInterface) {
        if (lists == null) {
            return;
        }
        if (!lists.contains(networkChangedInterface)) {
            lists.add(networkChangedInterface);
        }
    }

    public static void remove(NetworkChangedInterface networkChangedInterface) {
        if (lists == null) {
            return;
        }
        if (!lists.contains(networkChangedInterface)) {
            lists.remove(networkChangedInterface);
        }
    }


    private void notifyMobileNet() {
        for (NetworkChangedInterface list : lists) {
            list.mobileNet();
        }
    }

    private void notifyWifiNet() {
        for (NetworkChangedInterface list : lists) {
            list.wifiNet();
        }
    }

    private void notifyUnconnectedNet() {
        for (NetworkChangedInterface list : lists) {
            list.unconnectedNet();
        }
    }


    public interface NetworkChangedInterface {
        void mobileNet();

        void wifiNet();

        void unconnectedNet();

    }


}
