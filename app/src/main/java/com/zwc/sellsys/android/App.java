package com.zwc.sellsys.android;

import android.app.Application;
import android.content.Context;

import com.zwc.sellsys.android.crash.BuglyUtil;
import com.zwc.sellsys.android.umeng.statistic.UmengStatisticUtil;

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        UmengStatisticUtil.init(mContext);
        BuglyUtil.init(mContext);

    }

    public static Context getAppContext(){
        return mContext;
    }


}
