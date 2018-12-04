package com.zwc.sellsys.android.utils;

import android.content.Context;
import android.content.Intent;

public class IntentUtil {

    public static void startActivity(Context context,Class<?> clazz){
        Intent intent = new Intent(context,clazz);
        context.startActivity(intent);
    }

}
