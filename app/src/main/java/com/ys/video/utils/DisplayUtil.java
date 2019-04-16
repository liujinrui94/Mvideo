package com.ys.video.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.ys.video.AppApplication;


public class DisplayUtil {
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) AppApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) AppApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static float getScreenDensity() {
        WindowManager wm = (WindowManager) AppApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.density;
    }



}