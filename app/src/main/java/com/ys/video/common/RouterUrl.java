package com.ys.video.common;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author:liujinrui
 * @Date:2019/2/13
 * @Description: 路由表
 */
public class RouterUrl {


    public static void openActivity(String routerUrl) {
        ARouter.getInstance()
                .build(routerUrl)
                .navigation();
    }

    public static final String HomeActivity = "/map/HomeActivity";

    public static final String MineSettingActivity = "/map/MineSettingActivity";

    public static final String ServerWebActivity = "/map/ServerWebActivity";

    public static final String QQLoginActivity = "/map/QQLoginActivity";

    public static final String LoginActivity = "/map/LoginActivity";

    public static final String PayListAct = "/map/PayListAct";

    public static final String BindPhoneAct = "/map/BindPhoneAct";

    public static final String WebViewAtc = "/map/WebViewAtc";

    public static final String PayDemoActivity = "/map/PayDemoActivity";


}
