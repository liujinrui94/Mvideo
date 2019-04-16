package com.ys.video.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.util.DisplayMetrics;

import java.util.List;

public class SysUtils {

    public static int Dp2Px(Context context, float dp) {
           /* android context.getResources().getDisplayMetrics()这是获取手机屏幕参数，
       后面的density就是屏幕的密度，类似分辨率，但不是
       float scale = getResources().getDisplayMetrics().density;
       density值表示每英寸有多少个显示点，与分辨率是两个不同的概念。
       这个得到的不应该叫做密度，应该是密度的一个比例。不是真实的屏幕密度，而是相对于某个值的屏幕密度。
       也可以说是相对密度*/
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @SuppressWarnings("deprecation")
    public static int getScreenWidth(Activity activity) {
        int SCREEN_WIDTH = 0;

        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        SCREEN_WIDTH = dm.widthPixels;


        return SCREEN_WIDTH;
    }

    public static int getScreenHeight(Activity activity) {
        int SCREEN_HEIGHT = 0;
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        SCREEN_HEIGHT = dm.heightPixels;

        return SCREEN_HEIGHT;
    }



    public static Point getScreenPoint(Activity activity){
        Point point=new Point();
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        point.x = dm.widthPixels;
        point.y=dm.heightPixels;
        return point;
    }
    public static boolean isWxInstall(Context context, String page) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(page)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void getAppMemory(Activity context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        //最大分配内存
        int memory = activityManager.getMemoryClass();
        //最大分配内存获取方法2
        float maxMemory = (float) (Runtime.getRuntime().maxMemory() * 1.0 / (1024 * 1024));
        //当前分配的总内存
        float totalMemory = (float) (Runtime.getRuntime().totalMemory() * 1.0 / (1024 * 1024));
        //剩余内存
        float freeMemory = (float) (Runtime.getRuntime().freeMemory() * 1.0 / (1024 * 1024));
    }
}