package com.ys.video;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ys.video.adapter.TabFragmentAdapter;
import com.ys.video.adapter.TabRGFragmentAdapter;
import com.ys.video.base.BaseActivity;
import com.ys.video.common.RouterUrl;
import com.ys.video.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Route(path = RouterUrl.MainActivity)
public class MainActivity extends BaseActivity {


    private BottomNavigationView navigation;


    private List<Fragment> fragments = new ArrayList<>();
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewPadding();
        Log.e("AAAA1", System.currentTimeMillis() + "");
        Log.e("AAAA2", System.currentTimeMillis() + "");
        Log.e("AAAA", getAPPNames());
        Log.e("AAAA3", System.currentTimeMillis() + "");
//        queryFilterAppInfo();
//        queryFilterAppInfo();
//        fragments.add(HomeFragment.newInstance("1"));
//        fragments.add(HomeFragment.newInstance("2"));
//        fragments.add(HomeFragment.newInstance("3"));
//        fragments.add(HomeFragment.newInstance("4"));
//        fragments.add(HomeFragment.newInstance("5"));
//        radioGroup = findViewById(R.id.radioGroup);
//        radioGroup.getChildAt(0).performClick();
//        TabRGFragmentAdapter tabFragmentAdapter = new TabRGFragmentAdapter(this, fragments, R.id.frameLayout, radioGroup, 0);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public String getAPPNames() {
        PackageManager pckMan = getPackageManager();
        List<PackageInfo> packs = pckMan.getInstalledPackages(0);
        int count = packs.size();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < count; ++i) {
            PackageInfo p = (PackageInfo) packs.get(i);
            if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0)//通过flag排除系统应用，会将电话、短信也排除掉
            {

                if (p.versionName != null) {
                    stringBuilder.append(p.applicationInfo.loadLabel(pckMan).toString()).append(",");
                }
            }
        }

        return stringBuilder.toString();
    }

    private void queryFilterAppInfo() {
        PackageManager pm = this.getPackageManager();
        // 查询所有已经安装的应用程序
        List<ApplicationInfo> appInfos = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);// GET_UNINSTALLED_PACKAGES代表已删除，但还有安装目录的
        List<ApplicationInfo> applicationInfos = new ArrayList<>();

        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        // 通过getPackageManager()的queryIntentActivities方法遍历,得到所有能打开的app的packageName
        List<ResolveInfo> resolveinfoList = getPackageManager()
                .queryIntentActivities(resolveIntent, 0);
        Set<String> allowPackages = new HashSet();
        for (ResolveInfo resolveInfo : resolveinfoList) {
            allowPackages.add(resolveInfo.activityInfo.packageName);
        }

        for (ApplicationInfo app : appInfos) {
            if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0)//通过flag排除系统应用，会将电话、短信也排除掉
            {
                applicationInfos.add(app);
            }
            if (app.uid > 10000) {//通过uid排除系统应用，在一些手机上效果不好
                applicationInfos.add(app);
            }
//            if (allowPackages.contains(app.packageName)) {
//                applicationInfos.add(app);
//            }
        }
        Log.e("AAAAAAAA", applicationInfos.toString());
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main1);
//        radioGroup = findViewById(R.id.tab_main_radio_group);
//        fragments.add(HomeFragment.newInstance("1"));
//        fragments.add(HomeFragment.newInstance("2"));
//        fragments.add(HomeFragment.newInstance("3"));
//        TabFragmentAdapter1 tabFragmentAdapter = new TabFragmentAdapter1(this, fragments, R.id.tab_main_container, radioGroup, 0);
//
//
//
//
//    }

}
