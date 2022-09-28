package com.ethan.ethanutils.system;

/**
 * @author Ethan 2021/11/22
 */
public class EMemory {

    /**
     * 获取应用占用内存大小
     *
     * @return 当前应用的占用内存状态
     */
    public static String getRunTimeMemory() {
        return "系统给当前应用分配的运存大小: " + Runtime.getRuntime().maxMemory() + "\n当前应用占用的运存大小: " + Runtime.getRuntime().totalMemory();
    }
}
