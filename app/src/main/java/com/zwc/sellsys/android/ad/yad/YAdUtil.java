package com.zwc.sellsys.android.ad.yad;

import android.content.Context;

import com.zwc.sellsys.android.utils.DebugUtil;

import cdc.sed.yff.AdManager;

public class YAdUtil {

    public static void init(Context context){
        AdManager.getInstance(context).init(YConstants.APP_ID, YConstants.SECRET_ID, DebugUtil.DEBUG);
    }

}
