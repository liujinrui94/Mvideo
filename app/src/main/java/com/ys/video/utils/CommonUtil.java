package com.ys.video.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonUtil {
    /**
     * 获取本地资源文件
     *
     * @param fileName
     * @param context
     * @return
     */
    public static String getFromAssets(String fileName, Context context) {

        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }


    }


    /**
     * 缓存Fragment
     */

    public static Map<String, Fragment> mCache = new HashMap<String, Fragment>();

    public static int getTotalHeightofListView(ListView listView) {
        ListAdapter mAdapter = listView.getAdapter();
        if (mAdapter == null) {
            return 0;
        }
        int totalHeight = 0;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View mView = mAdapter.getView(i, null, listView);
            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            //mView.measure(0, 0);
            totalHeight += mView.getMeasuredHeight();

        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (mAdapter.getCount() - 1));

        listView.setLayoutParams(params);
        listView.requestLayout();
        return totalHeight;
    }

    /**
     * 得到程序VersionName
     */
    // 获取版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    // 获取版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    /**
     * @param packageContext from,一般传XXXActivity.this
     * @param cls            to,一般传XXXActivity.class
     * @Description: 跳转
     */


    /**
     * 获取手机IMEI号
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();

        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String wifiMac = info.getMacAddress();
        if (wifiMac == null) {
            return null;
        }
//        return  wifiMac;
        return wifiMac.replace(":", "").toUpperCase();
    }

    /**
     * 获取SN号
     *
     * @param context
     * @return
     */
    public static String readSN(Context context) {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + "/SN.txt");
            if (!file.isDirectory()) {
                Intent intent = new Intent("android.intent.action.READSN");
                context.sendBroadcast(intent);   //发送广播
            }
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String result = br.readLine();
            br.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "read sn fail！", Toast.LENGTH_SHORT).show();
            return null;
        }

    }

    /**
     * 获取屏幕分辨率
     */
    public static String getDisplayMetrics(Activity context) {

        DisplayMetrics metrics = new DisplayMetrics
                /**
                 * getRealMetrics - 屏幕的原始尺寸，即包含状态栏。
                 * version >= 4.2.2
                 */();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            context.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        }
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        return height + "x" + width;

    }

    /**
     * 获取本机时间
     *
     * @return
     */
    public static String getYearMonthDate() {
        Calendar calendar = Calendar.getInstance();
        long unixTime = calendar.getTimeInMillis();//这是时间戳
        return "" + calendar.get(Calendar.YEAR) + calendar.get(Calendar.MONTH) + calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取本机时间，年
     *
     * @return
     */
    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取本机时间，月
     *
     * @return
     */
    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }

    /**
     * 获取本机时间，日
     *
     * @return
     */
    public static int getDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public static void setBackgroundAlpha(Context mContext, float bgAlpha) {

        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

    /**
     * 将集合转化为数组
     *
     * @param list
     * @return
     */
    public String[] convertCollectionToArray(List list) {
        Object[] objectArray1 = list.toArray();
        String[] array1 = (String[]) list.toArray(new String[list.size()]);
        Set set = new HashSet();
        Object[] objectArray2 = set.toArray();
        String[] array2 = (String[]) set.toArray(new String[set.size()]);
        return array2;
    }

    /**
     * 安装apk
     */
    public static void installApk(File file) {
        Intent intent = new Intent();
        Uri uri;
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //执行的数据类型

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//24
//            uri = FileProvider.getUriForFile(AppApplication.getInstance(), BuildConfig.APPLICATION_ID + ".fileprovider", file);

            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(file);
        }
//        intent.setDataAndType(uri, "application/vnd.android.package-archive");
//        AppApplication.getInstance().startActivity(intent);
//        AppApplication.AppExit();
    }


}