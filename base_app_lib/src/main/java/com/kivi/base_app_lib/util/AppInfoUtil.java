package com.kivi.base_app_lib.util;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * @description:
 */

public class AppInfoUtil {

    /**
     * get application version code
     *
     * @param context
     * @return
     */
    public static int getVerCode(Context context) {
        int verCode = 1;
        String packageName = context.getPackageName();
        try {
            verCode = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verCode;
    }

    /**
     * get application version name
     *
     * @param context
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "0.0.1";
        String packageName = context.getPackageName();
        try {
            verName = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    /**
     * get application root path from SDCard
     *
     * @param context
     * @return
     */
    public static String getAppPathFromSDCard(Context context) {
        String path = "";
        path = context.getExternalFilesDir(null).getAbsolutePath();
        return path;
    }





}

