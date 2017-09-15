package com.kivi.base_app_lib.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * @description:
 */

public class NetworkUtil {

    public enum State {
        MOBILE, WIFI, UNCONNECTED, PUBLISHED
    }

    private static State tempState;

    public static State getNetworkState(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        State state = State.UNCONNECTED;
        if (networkInfo != null && networkInfo.isAvailable()) {
            if (isMobileData(context)) {
                state = State.MOBILE;
            } else if (isWifi(context)) {
                state = State.WIFI;
            }
        }
        if (state == tempState) {
            return State.PUBLISHED;
        }
        tempState = state;
        return state;
    }


    /**
     * 是否是移动数据
     *
     * @param context
     * @return
     */
    private static boolean isMobileData(Context context) {
        return isConnected(context, ConnectivityManager.TYPE_MOBILE);
    }

    /**
     * 是否是wifi
     *
     * @param context
     * @return
     */
    private static boolean isWifi(Context context) {
        return isConnected(context, ConnectivityManager.TYPE_WIFI);
    }


    private static boolean isConnected(Context context, int type) {
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] allNetwork = manager.getAllNetworks();
            for (Network network : allNetwork) {
                if (manager.getNetworkInfo(network).isConnected() &&
                        type == manager.getNetworkInfo(network).getType()) {
                    return true;
                }
            }
        } else {
            NetworkInfo[] allNetworkInfo = manager.getAllNetworkInfo();
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.isConnected() && type == networkInfo.getType()) {
                    return true;
                }
            }
        }
        return false;
    }


}
