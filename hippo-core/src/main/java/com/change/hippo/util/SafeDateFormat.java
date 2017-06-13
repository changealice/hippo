package com.change.hippo.util;

import java.text.SimpleDateFormat;

/**
 * User: change.long@99bill.com
 * Date: 2017/5/31
 * Time: 上午11:17
 */
public class SafeDateFormat {
    private static final ThreadLocal<SimpleDateFormat> THREAD_LOCAL = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        }
    };


    public static SimpleDateFormat get() {
        return THREAD_LOCAL.get();
    }


    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
