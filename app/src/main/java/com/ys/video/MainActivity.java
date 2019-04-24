package com.ys.video;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ys.video.adapter.TabFragmentAdapter;
import com.ys.video.adapter.TabRGFragmentAdapter;
import com.ys.video.base.BaseActivity;
import com.ys.video.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private BottomNavigationView navigation;


    private List<Fragment> fragments = new ArrayList<>();
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments.add(HomeFragment.newInstance("1"));
        fragments.add(HomeFragment.newInstance("2"));
        fragments.add(HomeFragment.newInstance("3"));
        fragments.add(HomeFragment.newInstance("4"));
        fragments.add(HomeFragment.newInstance("5"));
        radioGroup=findViewById(R.id.radioGroup);
        TabRGFragmentAdapter tabFragmentAdapter = new TabRGFragmentAdapter(this, fragments, R.id.frameLayout, radioGroup, 0);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
