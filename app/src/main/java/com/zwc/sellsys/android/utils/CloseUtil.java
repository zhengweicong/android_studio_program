package com.zwc.sellsys.android.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 用于关闭数据库，数据流等操作
 */
public class CloseUtil {

    /**
     * 关闭对象
     * @param closeable
     */
    public static void close(Closeable closeable){
        try {
            if (null != closeable) {
                closeable.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
