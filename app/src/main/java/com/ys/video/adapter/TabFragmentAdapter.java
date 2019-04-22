package com.ys.video.adapter;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;

import com.ys.video.R;

import java.util.List;

public class TabFragmentAdapter implements BottomNavigationView.OnNavigationItemSelectedListener {
    private List<Fragment> fragments;
    private BottomNavigationView bottomNavigationView;
    private FragmentActivity fragmentActivity;
    private int fragmentContentId;
    private int currentTab=0;
    private FragmentTransaction fragmentTransaction;


    public TabFragmentAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments, int fragmentContentId,
                              BottomNavigationView bottomNavigationView) {
        this.fragments = fragments;
        this.bottomNavigationView = bottomNavigationView;
        this.fragmentActivity = fragmentActivity;
        this.fragmentContentId = fragmentContentId;
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId, fragments.get(currentTab));
        ft.commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        for (int i = 0; i < bottomNavigationView.getMaxItemCount(); i++) {
            if (bottomNavigationView.getMenu().getItem(i).getItemId() == menuItem.getItemId()) {
                Fragment fragment = fragments.get(i);
                FragmentTransaction ft = obtainFragmentTransaction(i);
                getCurrentFragment().onPause();
                if (fragment.isAdded()) {
                    fragment.onResume();
                } else {
                    ft.add(fragmentContentId, fragment);
                }
                showTab(i);
                ft.commit();
            }
        }

        return true;
    }


    private void showTab(int idx) {
        FragmentTransaction ft = obtainFragmentTransaction(idx);
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            if (idx == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
        }
        ft.commit();
        currentTab = idx;

    }

    private FragmentTransaction obtainFragmentTransaction() {
        return fragmentActivity.getSupportFragmentManager().beginTransaction();
    }

    private FragmentTransaction obtainFragmentTransaction(int index) {
        fragmentTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        // 设置切换动画
        if (index > currentTab) {
            fragmentTransaction.setCustomAnimations(R.anim.activity_into_from_right, R.anim.activity_out_to_left);
        } else {
            fragmentTransaction.setCustomAnimations(R.anim.activity_into_from_left, R.anim.activity_out_to_right);
        }
        return fragmentTransaction;
    }


    private Fragment getCurrentFragment() {
        return fragments.get(currentTab);
    }


}