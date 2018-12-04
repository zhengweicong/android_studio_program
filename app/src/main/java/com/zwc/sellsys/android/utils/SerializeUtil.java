package com.zwc.sellsys.android.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {

    public static String serialize(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        String str = baos.toString();

        CloseUtil.close(baos);
        CloseUtil.close(oos);
        return str;
    }

    public static Object deserialize(String str) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes());
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        CloseUtil.close(ois);
        return obj;
    }
}
