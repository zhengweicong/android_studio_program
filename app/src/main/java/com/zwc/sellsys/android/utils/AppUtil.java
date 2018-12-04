package com.zwc.sellsys.android.utils;

import android.app.Activity;
import android.content.Context;

import com.zwc.sellsys.android.App;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AppUtil {
    public static final String TAG = "AppUtil";

    /**
     * 凡是打开的activity都装入集合
     */
    private static List<Activity> activities = new ArrayList<Activity>();

    /**
     *是否退出应用
     */
    private static boolean isExitApp = false;

    /**
     * 获取应用程序上下文
     *
     * @return
     */
    public static Context getAppContext() {
        return App.getAppContext();
    }

    /**
     * 添加Activity到集合中统一管理
     *
     * @param activity 要添加的Activity
     */
    public static void addActivity(Activity activity) {
        LogUtil.i(TAG, "add activity to list");
        if (!activities.contains(activity)) {
            activities.add(activity);
        }
    }

    /**
     * 删除集合中的Activity
     *
     * @param activity 要删除的Activity
     */
    public static void removeActivity(Activity activity) {
        LogUtil.i(TAG, "remove activity from list");
        if (null != activity && activities.contains(activity)) {
            activities.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }

    }

    /**
     * 关闭所有Activity
     */
    public static void finishActivities() {
        LogUtil.i(TAG, "activities size is " + activities.size());
        for (Activity activity : activities) {
            removeActivity(activity);
        }
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    /**
     * 退出应用
     */
    public static void exitApp() {
        if (!isExitApp) {
            isExitApp = true;
            ToastUtil.show("再按一次退出程序");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExitApp = false;
                }
            }, 2000);
        } else {
            finishActivities();
        }
    }

}
