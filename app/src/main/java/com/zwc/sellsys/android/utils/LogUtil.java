package com.zwc.sellsys.android.utils;

import android.util.Log;

public class LogUtil {

    private static final String TAG = LogUtil.class.getSimpleName();
    private static boolean debug = true;

    public static void i(String tag, String msg) {
        if (debug) {
            if (StringUtil.isEmpty(tag)) {
                Log.i(TAG, msg);
            } else {
                Log.i(tag, msg);
            }
        }
    }

    public static void d(String tag, String msg) {
        if (debug) {
            if (StringUtil.isEmpty(tag)) {
                Log.i(TAG, msg);
            } else {
                Log.d(tag, msg);
            }
        }
    }

    public static void e(String tag, String msg) {
        if (debug) {
            if (StringUtil.isEmpty(tag)) {
                Log.i(TAG, msg);
            } else {
                Log.e(tag, msg);
            }
        }
    }

    public static void w(String tag, String msg) {
        if (debug) {
            if (StringUtil.isEmpty(tag)) {
                Log.i(TAG, msg);
            } else {
                Log.w(tag, msg);
            }
        }
    }

    public static void v(String tag, String msg) {
        if (debug) {
            if (StringUtil.isEmpty(tag)) {
                Log.i(TAG, msg);
            } else {
                Log.v(tag, msg);
            }
        }
    }


}
