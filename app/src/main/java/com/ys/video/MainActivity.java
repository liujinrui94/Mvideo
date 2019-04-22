package com.ys.video;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ys.video.adapter.TabFragmentAdapter;
import com.ys.video.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView navigation;


    private List<Fragment> fragments = new ArrayList<>();
    RadioGroup radioGroup;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Log.e("AAAAA", item.getItemId() + " " + item.getOrder() + " " + navigation.getMaxItemCount());
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_notifications1:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_notifications2:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments.add(HomeFragment.newInstance("1"));
        fragments.add(HomeFragment.newInstance("2"));
        fragments.add(HomeFragment.newInstance("3"));
        fragments.add(HomeFragment.newInstance("4"));
        fragments.add(HomeFragment.newInstance("5"));
        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        TabFragmentAdapter  tabFragmentAdapter=new TabFragmentAdapter(this,fragments,R.id.frameLayout,navigation);
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
