package com.zwc.sellsys.android.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 郑维聪
 * @Date 2018-12-01
 */
public class DateUtil {
    public static final String DATE_FORMATTER_DEFAULT = "yyyy-MM-dd HH:MM:SS";
    public static final String DATE_FORMATTER_CHINESE = "yyyy年MM月dd日HH时MM分SS秒";

    /**
     *
     * @return 获取一个默认的中文时间格式
     */
    public static String getFormatCurrentDate() {
        return getFormatCurrentDate(null);
    }

    /**
     * 获取一个可定制格式的时间
     * @param formatter
     * @return
     */
    public static String getFormatCurrentDate(String formatter) {
        SimpleDateFormat sdf ;
        if (StringUtil.isEmpty(formatter)) {
            sdf = new SimpleDateFormat(DATE_FORMATTER_CHINESE);
        } else {
            sdf = new SimpleDateFormat(formatter);
        }
        return sdf.format(new Date());
    }

}
