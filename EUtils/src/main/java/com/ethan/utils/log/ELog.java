package com.ethan.utils.log;

import android.text.TextUtils;
import android.util.Log;

/**
 * 日志过滤器
 * 在 Application 的 onCreate() 中可以初始化
 * 不初始化则 TAG 默认为调用者类名
 *
 * @author Ethan 2022/6/10
 */
public class ELog {

    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    private static final int SHUTDOWN = Integer.MAX_VALUE;
    private static int level;
    private static String TAG = "";

    public static void init(String tag) {
        TAG = tag;
        level = VERBOSE;
    }

    public static void init(int lev) {
        level = lev;
    }

    public static void init(String tag, int lev) {
        TAG = tag;
        level = lev;
    }

    public static void setTag(String tag) {
        ELog.TAG = tag;
    }

    public static void setLevel(int lev) {
        level = lev;
    }

    public static void v(String msg) {
        if (TextUtils.isEmpty(TAG)) {
            TAG = new Exception().getStackTrace()[1].getClassName();
        }
        if (level <= VERBOSE) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg) {
        TAG = new Exception().getStackTrace()[1].getClassName();
        if (level <= DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        TAG = new Exception().getStackTrace()[1].getClassName();
        if (level <= INFO) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        TAG = new Exception().getStackTrace()[1].getClassName();
        if (level <= WARN) {
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg) {
        TAG = new Exception().getStackTrace()[1].getClassName();
        if (level <= ERROR) {
            Log.e(TAG, msg);
        }
    }
}
