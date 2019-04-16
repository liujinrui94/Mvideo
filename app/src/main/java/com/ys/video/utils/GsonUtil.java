package com.ys.video.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class GsonUtil {
    //GsonUtil.getInstance().fromJson(data.toString(),new TypeToken<List<Object>>(){}.getType());

    private static Gson gson;

    public GsonUtil() {

    }

    public static Gson getInstance() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }
//    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
//        @SuppressWarnings("unchecked")
//        List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
//        return ts;
//    }
    public static <T> T getObject(String jsonString, Type listType) {
        T t = gson.fromJson(jsonString, listType);
        return t;
    }

    public static <T> String BeanToencode(T bean) {


        return GsonUtil.encodeNoUrl(GsonUtil.beanToMap(bean));
    }


    public static String encodeNoUrl(Map<String, Object> param) {
        if (param == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        Set<String> keys = param.keySet();

        for (String key : keys) {
            String value = param.get(key).toString();
            // pain...EditMyProfileDao params' values can be empty
            if (!TextUtils.isEmpty(value) && !key.equals("serialVersionUID")) {
                sb.append("&");
                sb.append(key).append("=").append(param.get(key));
            }

        }

        return sb.toString().substring(1);
    }


    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //判断是否为空bean，如果是直接返回
        if (bean == null) {
            return resultMap;
        }
        //获取类的class
        Class cls = bean.getClass();

        Field[] fields = cls.getDeclaredFields();//获取所有字段
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();//获取所有字段名称
            Object filedValue = null;
            try {
                int typeInt = fields[i].getModifiers();//获取字段的类型
                //获取字段的类型申明表，8静态，2私有，16final  =26，类型26为静态常量，不做处理如最终serialVersionUID
                if (typeInt != 26) {
                    fields[i].setAccessible(true);//设置访问权限
                    filedValue = fields[i].get(bean);//获取所有字段的值
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (filedValue != null) {
                resultMap.put(fieldName, filedValue);
            }

        }

        return resultMap;

    }

    public static <T> Map beanToParams(T bean) {
        Map<String, String> httpParams = new HashMap();
        //判断是否为空bean，如果是直接返回
        if (bean == null) {
            return httpParams;
        }
        //获取类的class
        Class cls = bean.getClass();

        Field[] fields = cls.getDeclaredFields();//获取所有字段
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();//获取所有字段名称
            Object filedValue = null;
            try {
                int typeInt = fields[i].getModifiers();//获取字段的类型

                //获取字段的类型申明表，8静态，2私有，16final  =26，类型26为静态常量，不做处理如最终serialVersionUID
                if (typeInt != 26) {
                    fields[i].setAccessible(true);//设置访问权限
                    filedValue = fields[i].get(bean);//获取所有字段的值

                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (filedValue != null && !TextUtils.equals(fieldName, "serialVersionUID")) {
                httpParams.put(fieldName, filedValue.toString());
            }

        }
        AppLogger.e("请求_内容", httpParams.toString());
        return httpParams;

    }
}
