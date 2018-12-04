package com.zwc.sellsys.android.utils;

public class CounterUtil {

    public enum Type {
        MINUS,
        PLUS
    }

    public static int count(Type type, int currSum) {
        int sum = currSum;
        if (type.equals(Type.MINUS)) {
            sum = sum <= 0 ? 0 : sum - 1;
        } else if (type.equals(Type.PLUS)) {
            sum += 1;
        }
        return sum;
    }
}
