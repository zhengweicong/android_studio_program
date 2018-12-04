package com.zwc.sellsys.android.utils;

import android.text.TextUtils;

/**
 * 字符串工具
 * @author 郑维聪
 */
public class StringUtil {


    /**
     * <pre>
     *    判断字符串是否为空，
     *    是,返回true，
     *    不是,返回false
     * </pre>
     * @param str 传入的字符串
     * @return 是否为空字符串
     */
    public static boolean isEmpty(String str){
        if(TextUtils.isEmpty(str) || str.equals("")){
            return true;
        }
        return false;
    }
}
