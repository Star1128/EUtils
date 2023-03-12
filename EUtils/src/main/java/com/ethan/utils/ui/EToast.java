package com.ethan.utils.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.ethan.utils.log.ELog;

/**
 * @author Ethan 2022/9/27
 */
public class EToast {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static void showToast(CharSequence text) {
        if (mContext == null) {
            ELog.e("EToast not init");
            return;
        }
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }
}
