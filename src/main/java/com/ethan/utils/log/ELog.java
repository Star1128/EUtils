package com.ethan.utils.log;

import android.text.TextUtils;
import android.util.Log;

/**
 * 日志过滤器
 * 在 Application 的 onCreate() 中可以初始化
 * 不初始化则 TAG 默认为调用者类名+方法名
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
        if (level <= VERBOSE) {
            String tag = "";
            if (TextUtils.isEmpty(TAG)) {
                String classFullName = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
                String methodName = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
                tag = assemble(classFullName, methodName);
            } else {
                tag = TAG;
            }
            Log.v(tag, msg);
        }
    }

    public static void d(String msg) {
        if (level <= DEBUG) {
            String tag = "";
            if (TextUtils.isEmpty(TAG)) {
                String classFullName = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
                String methodName = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
                tag = assemble(classFullName, methodName);
            } else {
                tag = TAG;
            }
            Log.d(tag, msg);
        }
    }

    public static void i(String msg) {
        if (level <= INFO) {
            String tag = "";
            if (TextUtils.isEmpty(TAG)) {
                String classFullName = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
                String methodName = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
                tag = assemble(classFullName, methodName);
            } else {
                tag = TAG;
            }
            Log.i(tag, msg);
        }
    }

    public static void w(String msg) {
        if (level <= WARN) {
            String tag = "";
            if (TextUtils.isEmpty(TAG)) {
                String classFullName = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
                String methodName = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
                tag = assemble(classFullName, methodName);
            } else {
                tag = TAG;
            }
            Log.w(tag, msg);
        }
    }

    public static void e(String msg) {
        if (level <= ERROR) {
            String tag = "";
            if (TextUtils.isEmpty(TAG)) {
                String classFullName = new Exception().getStackTrace()[1].getClassName(); // 获取调用者的类名
                String methodName = new Exception().getStackTrace()[1].getMethodName(); // 获取调用者的方法名
                TAG = assemble(classFullName, methodName);
            } else {
                tag = TAG;
            }
            Log.e(tag, msg);
        }
    }

    private static String assemble(String classFullName, String methodName) {
        String className = classFullName.substring(classFullName.lastIndexOf(".") + 1);
        return className + "#" + methodName;
    }
}
