package com.zwc.sellsys.android.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 */
public class RegularExpressionUtil {

    /**
     * 验证是否是手机号码
     *
     * @param mobile 传入的手机号码
     * @return 如果为手机号码则返回true，否则为false
     */
    public static boolean isMobile(String mobile) {
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
}
