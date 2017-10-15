package com.shaad.ignite.util;

public class NumberUtil {
    private NumberUtil() {
    }

    public static Long safeCastToLong(Object actualLong) {
        try {
            return (Long) actualLong;
        } catch (ClassCastException e) {
            //hack around converting classcast exception
            return Long.parseLong(actualLong.toString());
        }

    }
}
