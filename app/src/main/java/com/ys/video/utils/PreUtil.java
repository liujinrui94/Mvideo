package com.ys.video.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PreUtil {
    private static final String APPLICATION_PREFERENCES = "app_prefs";
    private static SharedPreferences.Editor editor;
    private static SharedPreferences pref;

    /**
     * 保存String类类型的数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveStringPref(Context context, String key, String value) {
        initEditor(context);
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 私有工具类
     *
     * @param context
     */

    private static void initEditor(Context context) {
        initPref(context);
        editor = pref.edit();
    }

    private static void initPref(Context context) {
        // 空判断，防止空指针，导致程序崩溃。
        if (null == pref)
            pref = context.getSharedPreferences(APPLICATION_PREFERENCES,
                    Context.MODE_PRIVATE);
    }

    public static void clear(Context context) {
        initEditor(context);
        editor.clear();
        editor.commit();
    }

    /**
     * 保存布尔值类型的数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveBooleanPref(Context context, String key, boolean value) {
        initEditor(context);
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 保存整形数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveIntPref(Context context, String key, int value) {
        initEditor(context);
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 获取字符串类型的数据
     *
     * @param context
     * @param key
     * @return
     */
    public static String getStringPref(Context context, String key) {
        return getStringPref(context, key, "");
    }

    public static String getStringPref(Context context, String key,
                                       String defaultValue) {//保存默认值
        initPref(context);
        return pref.getString(key, defaultValue);
    }

    /**
     * 获取布尔值类型的数据
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBooleanPref(Context context, String key,
                                         boolean defaultValue) {
        initPref(context);
        return pref.getBoolean(key, defaultValue);
    }

    /**
     * 获取整形类型的数据
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getIntPref(Context context, String key, int defaultValue) {
        initPref(context);
        return pref.getInt(key, defaultValue);
    }

    /**
     * 使用SharedPreference保存对象
     *
     * @param fileKey    储存文件的key
     * @param key        储存对象的key
     * @param saveObject 储存的对象
     */
    public static void save(Context context, String fileKey, String key, Object saveObject) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileKey, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String string = Object2String(saveObject);
        editor.putString(key, string);
        editor.commit();
    }

    /**
     * writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
     * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
     *
     * @param object 待加密的转换为String的对象
     * @return String   加密后的String
     */
    private static String Object2String(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            String string = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            objectOutputStream.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取SharedPreference保存的对象
     *
     * @param fileKey 储存文件的key
     * @param key     储存对象的key
     * @return object 返回根据key得到的对象
     */
    public static Object get(Context context, String fileKey, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileKey, Activity.MODE_PRIVATE);
        String string = sharedPreferences.getString(key, null);
        if (string != null) {
            Object object = String2Object(string);
            return object;
        } else {
            return null;
        }
    }

    /**
     * 使用Base64解密String，返回Object对象
     *
     * @param objectString 待解密的String
     * @return object      解密后的object
     */
    private static Object String2Object(String objectString) {
        byte[] mobileBytes = Base64.decode(objectString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
