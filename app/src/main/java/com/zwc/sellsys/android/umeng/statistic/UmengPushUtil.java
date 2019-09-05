package com.zwc.sellsys.android.umeng.statistic;

import android.content.Context;
import android.util.Log;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

public class UmengPushUtil {
    private static final String TAG = UmengPushUtil.class.getSimpleName();

    public static void init(Context context) {
        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(context);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Log.i(TAG, "deviceToken：-------->  " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e(TAG, "-------->  " + "s:" + s + ",s1:" + s1);
            }
        });
    }

    public static void onAppStart(Context context){
        PushAgent.getInstance(context).onAppStart();
    }

}
