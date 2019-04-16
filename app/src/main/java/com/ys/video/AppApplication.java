package com.ys.video;

import android.app.Application;

public class AppApplication extends Application {


    public static AppApplication instances;



    public static AppApplication getInstance() {
        synchronized (AppApplication.class) {
            if (instances == null) {
                instances = new AppApplication();
            }
        }
        return instances;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;

    }



}
