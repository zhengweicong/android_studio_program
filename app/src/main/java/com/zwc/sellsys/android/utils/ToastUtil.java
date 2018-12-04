package com.zwc.sellsys.android.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

public class ToastUtil {
    private static Toast mToast;
    private static Snackbar mSnackBar;

    /**
     * 显示Toast,默认为Toast.LENGTH_SHORT
     *
     * @param str
     */
    public static void show(String str) {
        show(AppUtil.getAppContext(), str);
    }

    /**
     * 显示Toast，默认Toast.LENGTH_SHORT
     *
     * @param context
     * @param str
     */
    public static void show(Context context, String str) {
        if (null == mToast) {
            mToast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(str);
        }
        mToast.show();
    }

    /**
     * 显示Toast，
     *
     * @param id 资源文件中的字符串id
     */
    public static void show(int id) {
        show(AppUtil.getAppContext(), ResourseUtil.getString(id));
    }

    /**
     * 显示Snackbar
     *
     * @param view
     * @param text
     * @param duration
     */
    public static void showSnackbar(View view, CharSequence text, int duration) {
        if (null == mSnackBar) {
            mSnackBar = Snackbar.make(view, text, duration);
        } else {
            mSnackBar.setText(text);
        }
        mSnackBar.show();
    }
}
