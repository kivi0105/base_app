package com.kivi.base_app_lib.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.kivi.base_app_lib.base.BaseConfig;


/**
 * @description:
 */

public class SharedPreUtil {
    public static SharedPreUtil sharedPreUtil;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public static SharedPreUtil getInstance(Context context) {
        if (sharedPreUtil == null) {
            synchronized (SharedPreUtil.class) {
                if (sharedPreUtil == null) {
                    sharedPreUtil = new SharedPreUtil(context);
                }
            }
        }
        return sharedPreUtil;

    }

    public SharedPreUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(BaseConfig.SPF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void put(String key, int value) {
        editor.putInt(key, value)
                .apply();
    }

    public void put(String key, boolean value) {
        editor.putBoolean(key, value)
                .apply();
    }

    public void put(String key, float value) {
        editor.putFloat(key, value)
                .apply();
    }

    public void put(String key, long value) {
        editor.putLong(key, value)
                .apply();
    }

    public void put(String key, String value) {
        editor.putString(key, value)
                .apply();
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0);
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

}
