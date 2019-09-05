package com.zwc.sellsys.android.umeng.statistic;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.utils.UMUtils;
import com.zwc.sellsys.android.utils.LogUtil;

/**
 * https://developer.umeng.com/docs/66632/detail/66889
 * <pre>
 * 如果页面是使用FragmentActivity + Fragment实现的，需要在 FragmentActivity 中统计时长：
 * public void onResume() {
 *     super.onResume();
 *     MobclickAgent.onResume(this); //统计时长
 * }
 * public void onPause() {
 *     super.onPause();
 *     MobclickAgent.onPause(this); //统计时长
 * }
 * 并在其包含的 Fragment 中统计页面：
 * public void onResume() {
 *     super.onResume();
 *     MobclickAgent.onPageStart("MainScreen"); //统计页面("MainScreen"为页面名称，可自定义)
 * }
 * public void onPause() {
 *     super.onPause();
 *     MobclickAgent.onPageEnd("MainScreen");
 * }
 * </pre>
 */
public class UmengStatisticUtil {
    public static final String TAG = UmengStatisticUtil.class.getSimpleName();

    /**
     * @param context
     */
    public static void init(Context context) {
        /*
         注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，
         也需要在App代码中调用初始化接口
         （如需要使用AndroidManifest.xml中配置好的appkey和channel值，
         UMConfigure.init调用中appkey和channel参数请置为null）。
        */
        UMConfigure.init(context, UMengConstants.APP_KEY, "Umeng", UMConfigure.DEVICE_TYPE_PHONE, UMengConstants.MESSAGE_SECRET);
        //设置组件化的Log开关，参数: boolean 默认为false，如需查看LOG设置为true
        UMConfigure.setLogEnabled(true);
        //设置日志加密,参数：boolean 默认为false（不加密）
        UMConfigure.setEncryptEnabled(true);
        MobclickAgent.setScenarioType(context, MobclickAgent.EScenarioType.E_UM_NORMAL);
        // 将默认Session间隔时长改为40秒。
        MobclickAgent.setSessionContinueMillis(1000 * 40);

        //umeng push
        UmengPushUtil.init(context);

        LogUtil.i(TAG, UMUtils.getDeviceToken(context));
    }

    /**
     * @param context 当前宿主进程的ApplicationContext上下文。
     * @param eventID 为当前统计的事件ID
     */
    public static void onEvent(Context context, String eventID) {
        MobclickAgent.onEvent(context, eventID);
    }

    /**
     * @param context 当前宿主进程的ApplicationContext上下文。
     * @param eventID 为当前统计的事件ID
     * @param label   事件的标签属性。
     */
    public static void onEvent(Context context, String eventID, String label) {
        MobclickAgent.onEvent(context, eventID, label);
    }

    /**
     * 包含Activity、Fragment或View的应用
     */
    public static void onResume(Context context, String tag) {
        MobclickAgent.onPageStart(tag); //手动统计页面("SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(context); //统计时长
    }

    /**
     * 包含Activity、Fragment或View的应用
     */
    public static void onPause(Context context, String tag) {
        /*手动统计页面("SplashScreen"为页面名称，可自定义)，
          必须保证 onPageEnd 在 onPause 之前调用，
         因为SDK会在 onPause 中保存onPageEnd统计到的页面数据。
        */
        MobclickAgent.onPageEnd(tag);
        MobclickAgent.onPause(context);
    }


    /**
     * 方法是用来统计应用时长的(也就是Session时长，当然还包括一些其他功能)
     *
     * @param context
     */
    public static void onResume(Context context) {
        MobclickAgent.onResume(context);
    }

    /**
     * 方法是用来统计应用时长的(也就是Session时长，当然还包括一些其他功能)
     *
     * @param context
     */
    public static void onPause(Context context) {
        MobclickAgent.onPause(context);
    }


    /**
     * 用来统计页面跳转的。
     *
     * @param tag
     */
    public static void onPageStart(String tag) {
        MobclickAgent.onPageStart(tag);
    }

    /**
     * 用来统计页面跳转的。
     *
     * @param tag
     */
    public static void onPageEnd(String tag) {
        MobclickAgent.onPageEnd(tag);
    }
}
