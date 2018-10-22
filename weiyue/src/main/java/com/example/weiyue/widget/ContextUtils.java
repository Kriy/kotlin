package com.example.weiyue.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

public class ContextUtils {

    public static int px2sp(Context context, float pxValue) {
        float fontSize = context.getResources().getDisplayMetrics().scaledDensity;
        return ((int) (pxValue / fontSize + 0.5f));
    }

    public static int dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return ((int) (dp * scale + 0.5f));
    }

    public static float px2dp(Context context, float pxVal) {
        float scale = context.getResources().getDisplayMetrics().density;
        return pxVal / scale;
    }

    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return ((int) (spValue * fontScale + 0.5f));
    }

    private static LayoutInflater mInflater;

    public static View inflate(Context context, int res) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(context);
        }
        return mInflater.inflate(res, null);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
