package com.ethan.ethanutils.ui;

import android.app.Activity;
import android.graphics.Color;
import android.view.Window;
import android.view.WindowManager;

/**
 * 在布局中设置android:fitsSystemWindows="true"
 *
 * @author Ethan 2021/11/22
 */
public class EStatusBar {

    /**
     * 实现沉浸式状态栏（不确定能不能用）
     */
    public static void toImmersion(Activity activity) {
        Window window = activity.getWindow();
        // 添加flag：可以改变状态栏背景
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // 清除这个flag(因为5.0+已经弃用)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 设置状态栏颜色：透明
        window.setStatusBarColor(Color.TRANSPARENT);
    }
}
