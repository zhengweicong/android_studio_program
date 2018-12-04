package com.zwc.sellsys.android.utils;

/**
 * 资源文件工具类
 */
public class ResourseUtil {

    /**
     * 获取字符串资源
     *
     * @param id
     * @return
     */
    public static String getString(int id) {
        return AppUtil.getAppContext().getResources().getString(id);
    }

    /**
     * 获取带有参数的字符串资源
     *
     * @param id
     * @param args
     * @return
     */
    public static String getString(int id, Object... args) {
        return String.format(getString(id), args);
    }

    /**
     * 获取颜色资源
     * @param id 颜色id
     * @return
     */
    public static int getColor(int id) {
        return AppUtil.getAppContext().getResources().getColor(id);
    }


}
