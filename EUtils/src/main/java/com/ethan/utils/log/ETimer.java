package com.ethan.utils.log;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Ethan 2023/3/14
 */
public class ETimer {

    private static final String START_PREFIX = "--- %s START ---> %d";
    private static final String END_PREFIX = "--- %s END ---> %d\n--- %s GAP ---> %d";
    private static final Map<String, Timer> times = new HashMap<>();

    public static void start() {
        String key = assemble(new Exception().getStackTrace()[1].getClassName(), new Exception().getStackTrace()[1].getMethodName());
        long now = System.currentTimeMillis();
        Timer timer = new Timer(now);
        times.put(key, timer);
        ELog.d(String.format(Locale.CHINESE, START_PREFIX, key, now));
    }

    public static void start(String key) {
        long now = System.currentTimeMillis();
        Timer timer = new Timer(now);
        times.put(key, timer);
        ELog.d(String.format(Locale.CHINESE, START_PREFIX, key, now));
    }

    public static void end() {
        String key = assemble(new Exception().getStackTrace()[1].getClassName(), new Exception().getStackTrace()[1].getMethodName());
        long now = System.currentTimeMillis();
        Timer timer = times.get(key);
        if (timer != null) {
            timer.end = now;
            timer.gap = timer.end - timer.start;
            times.replace(key, timer);
            ELog.d(String.format(Locale.CHINESE, END_PREFIX, key, now, key, timer.gap));
        }
    }

    public static void end(String key) {
        long now = System.currentTimeMillis();
        Timer timer = times.get(key);
        if (timer != null) {
            timer.end = now;
            timer.gap = timer.end - timer.start;
            times.replace(key, timer);
            ELog.d(String.format(Locale.CHINESE, END_PREFIX, key, now, key, timer.gap));
        }
    }

    private static String assemble(String classFullName, String methodName) {
        String className = classFullName.substring(classFullName.lastIndexOf(".") + 1);
        return className + "#" + methodName;
    }

    static class Timer {
        long start;
        long end;
        long gap;

        public Timer(long start) {
            this.start = start;
        }

        public Timer(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }
}
