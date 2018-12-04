package com.zwc.sellsys.android.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsUtil {
    public static final String SHARED_PREFS = "order_info_shared_prefs";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
    }

    public static void putString(Context context, String key, String values) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, values);
        editor.commit();
    }

    public static void putString(String key, String value) {
        putString(AppUtil.getAppContext(), key, value);
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getString(key, defaultValue);
    }

    public static String getString(String key, String defaultValue) {
        return getString(AppUtil.getAppContext(), key, defaultValue);
    }


}